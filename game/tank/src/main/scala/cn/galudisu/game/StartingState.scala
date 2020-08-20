package cn.galudisu.game

import java.awt.Color

import cn.galudisu.game.model.Tank
import cn.galudisu.game.model.World
import cn.galudisu.game.model.TankGame
import cn.galudisu.game.ai.EasyTankAI
import cn.galudisu.game.maths.Vec
import cn.galudisu.game.maths.Dim
import cn.galudisu.game.ai.Moves
import cn.galudisu.game.ai.HardTankAI
import cn.galudisu.game.ai.TruceTankAI

object StartingState {
  import Moves._

  def fireAndRush: AI[Unit] =
    for {
      t <- findNearestTank
      _ <- aimAtTank(t)
      _ <- fire
      _ <- accelerate * 4
    } yield ()

  val tanks = List(
    Tank("1", Vec(10, 10), Color.red) withAI loop(fireAndRush),
    Tank("2", Vec(200, 200), Color.green) withAI loop(for {
      t <- findNearestTank
      _ <- aimAwayFrom(t)
      _ <- accelerate * 20
    } yield ()),
    Tank("3", Vec(500, 400), Color.cyan) withAI loop(for {
      _ <- moveTo(Vec(200, 200))
      _ <- fire
      _ <- moveTo(Vec(400, 200))
      _ <- fire
      _ <- moveTo(Vec(400, 400))
      _ <- fire
      _ <- moveTo(Vec(200, 400))
      _ <- fire
    } yield ()),
    Tank("4", Vec(250, 400), Color.magenta) withAI loop(for {
      _ <- rotateLeft
      _ <- fire
      _ <- accelerate * 4
    } yield ())
  )

  val world = World(Dim(1000, 700), tanks)
  val game  = TankGame(world, TruceTankAI, 0)
}
