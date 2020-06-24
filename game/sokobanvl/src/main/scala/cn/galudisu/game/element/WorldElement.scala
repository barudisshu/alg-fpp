package cn.galudisu.game.element

trait WorldElement

case class Box()                extends WorldElement
case class BoxOnGoalSquare()    extends WorldElement
case class Player()             extends WorldElement
case class PlayerOnGoalSquare() extends WorldElement
case class Floor()              extends WorldElement
case class GoalSquare()         extends WorldElement
case class Wall()               extends WorldElement

object WorldElement {
  def representation(we: WorldElement): String = we match {
    case Box()                => "$"
    case BoxOnGoalSquare()    => "*"
    case Player()             => "@"
    case PlayerOnGoalSquare() => "+"
    case Floor()              => ""
    case GoalSquare()         => "."
    case Wall()               => "#"
  }
}
