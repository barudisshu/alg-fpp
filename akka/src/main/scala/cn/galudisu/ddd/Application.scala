package cn.galudisu.ddd

import java.util.UUID

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings}
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import cn.galudisu.ddd.PlayerActor.{InitializePlayer, PlayerInitialized}
import com.typesafe.config.ConfigFactory
import scaldi.Module
import scaldi.akka.AkkaInjectable

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object Application extends App with AkkaInjectable {

  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()
  implicit val timeout = Timeout(5 seconds)

  val config = ConfigFactory.load()

  val logger: LoggingAdapter = Logging(system, getClass)

  implicit val appModule = new PlayerModule
  val player = inject[ActorRef]('player)

  val routes:Route = {
    logRequestResult("server") {
      (post & path("register")) {
        complete {
          // create a new user and send it a message
          val playerId = PlayerId(UUID.randomUUID().toString)
          (player ? InitializePlayer(playerId, 0, 0)).mapTo[PlayerInitialized].map { ev: PlayerInitialized => ev.playerId.value }
        }
      }
    }
  }

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}

class PlayerModule(implicit system: ActorSystem) extends Module {

  val numberOfShards = 2

  val playerRegion: ActorRef = ClusterSharding(system).start(
    typeName = "Player",
    entityProps = Props(new PlayerActor),
    settings = ClusterShardingSettings(system),
    extractEntityId = PlayerActor.extractEntityId(),
    extractShardId = PlayerActor.extractShardId(numberOfShards)
  )

  bind[ActorRef] as 'player to playerRegion

}