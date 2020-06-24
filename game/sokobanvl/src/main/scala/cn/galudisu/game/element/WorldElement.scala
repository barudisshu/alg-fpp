package cn.galudisu.game.element

trait WorldElement

case class Box()                extends WorldElement
case class BoxOnGoalSquare()    extends WorldElement
case class Player()             extends WorldElement
case class PlayerOnGoalSquare() extends WorldElement
case class Floor()              extends WorldElement
case class GoalSquare()         extends WorldElement
case class Wall()               extends WorldElement
