package cn.galudisu.game.maths

object Angle {

  val Pi: Double      = math.Pi
  val TwoPi: Double   = 2 * Pi
  val PiOver2: Double = Pi / 2

  val Zero: Angle          = Angle(0)
  val Quarter: Angle       = Angle(Pi / 2)
  val Half: Angle          = Angle(Pi)
  val ThreeQuarters: Angle = Angle(3 * Pi / 2)
  val Full: Angle          = Angle(TwoPi)

  implicit class DoubleOps(d: Double) {
    def degrees: Angle = Angle.degrees(d)
    def radians: Angle = Angle(d)
  }

  def degrees(degs: Double): Angle = Angle(degs * Pi / 180.0)

  def normalize(radians: Double): Double = {
    val fullCycles       = (radians / TwoPi).asInstanceOf[Int]
    val possiblyNegative = radians - TwoPi * fullCycles

    if (possiblyNegative < 0) possiblyNegative + TwoPi
    else possiblyNegative
  }

  def apply(radians: Double) = new Angle(normalize(radians))
}

class Angle private (val radians: Double) extends AnyVal with Ordered[Angle] {
  import Angle.Pi

  def sin: Double              = math.sin(radians)
  def cos: Double              = math.cos(radians)
  def tan: Double              = math.tan(radians)
  def opposite: Angle          = Angle(radians + Pi)
  def degrees: Double          = radians * 180.0 / Pi
  def unary_- : Angle          = Angle(-radians)
  def +(other: Angle): Angle   = Angle(radians + other.radians)
  def -(other: Angle): Angle   = Angle(radians - other.radians)
  def *(factor: Double): Angle = Angle(radians * factor)
  def /(factor: Double): Angle = Angle(radians / factor)
  def compare(a: Angle): Int = {
    if (this == a) 0
    else if (this.radians < a.radians) -1
    else +1
  }

  private def shiftSin(x: Double) = math.sin(x - radians - Pi)

  def isLeftOf(a: Angle): Boolean =
    (a == opposite) || (a != this && shiftSin(a.radians) < 0)

  def isRightOf(a: Angle): Boolean =
    (a == opposite) || (a != this && shiftSin(a.radians) > 0)

  def distanceTo(a: Angle): Angle = {
    val diff = a - this
    if (diff < Angle.Half) diff else -diff
  }

  def addUpTo(add: Angle, upTo: Angle): Angle = {
    val added = this + add
    if (isLeftOf(upTo) != added.isLeftOf(upTo)) upTo else added
  }

  override def toString = s"Angle($radians)"
}
