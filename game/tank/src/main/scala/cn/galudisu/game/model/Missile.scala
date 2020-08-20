package cn.galudisu.game.model

import cn.galudisu.game.ai.Moves.{AI, AIDone}
import cn.galudisu.game.maths.Vec
import cn.galudisu.game.maths.Dim
import cn.galudisu.game.maths.Percentage
import cn.galudisu.game.maths.Angle

object Missile {
  val Friction = Percentage.Zero
  val MaxSpeed = 100.0
  val Size = Dim(6,6)
  
  def fireToward(fromPos: Vec, angle: Angle, speed: Double, range: Double): Missile = {
    val physics = Physics(angle, fromPos, Vec.fromAngle(angle, speed), Vec.Zero, Missile.Size, Friction, MaxSpeed)
    Missile(EntityId.Auto, physics, range, true)
  }
}

case class Missile(id: EntityId, physics: Physics, range: Double, alive: Boolean) extends Entity {
  type This = Missile
  
  override def ai = AIDone
  override def kill = copy(alive = false)
  override def updatePhysics(f: Physics => Physics): Missile = copy(physics = f(physics))
  override def withAI(newAI: AI[Unit]): This = this
  override def run(allowMove: Vec => Boolean): This = {
    Missile(id, onlyIfAlive(physics)(_ run allowMove), range - vel.length, physics.nextFrameAllowed(allowMove))
  }
  override def replaceId(newId: EntityId): This = copy(id = newId)
}