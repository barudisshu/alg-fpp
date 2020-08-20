package cn.galudisu.game.model

import java.awt.Color

import cn.galudisu.game.ai.Moves.{AI, AIDone}
import cn.galudisu.game.maths.{Angle, Dim, Percentage, Vec}

import scala.swing.Color

object Tank {
  val Acceleration = 3.0
  val Friction     = Percentage(0.4)
  val GunRange     = 10000.0
  val MissileSpeed = 10.0
  val MaxSpeed     = 50
  val RotationRate = Angle.degrees(5)
  val Size         = Dim(10, 10)

  def apply(id: String, pos: Vec, c: Color = Color.black): Tank = {
    val physics = Physics(Angle.Zero, pos, Vec.Zero, Vec.Zero, Tank.Size, Friction, MaxSpeed)
    new Tank(EntityId(id), AIDone, physics, Angle.Zero, true, c)
  }

  def unapply(e: Entity): Option[Tank] = if (e.isInstanceOf[Tank]) Some(e.asInstanceOf[Tank]) else None
}

case class Tank(id: EntityId, ai: AI[Unit], physics: Physics, gunAngle: Angle, alive: Boolean, color: Color)
    extends Entity {

  type This = Tank

  def replaceId(newId: EntityId): Tank = copy(id = newId)

  def gunFacing: Angle = physics.facing + gunAngle

  def accelerate: Tank = accelerateForward(Tank.Acceleration)

  def withAI(newAI: AI[Unit]): Tank = copy(ai = newAI)

  def kill = copy(alive = false, ai = AIDone)

  def fire = Missile.fireToward(pos + Vec.fromAngle(facing, 20.0), facing, Tank.MissileSpeed, Tank.GunRange)

  def updatePhysics(f: Physics => Physics): Tank = copy(physics = f(physics))
}
