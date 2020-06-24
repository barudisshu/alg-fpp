package cn.galudisu.game

import cn.galudisu.game.element._

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.swing.Publisher
import scala.swing.event.Event
import scala.swing.event.Key._

case class PlayerMove() extends Event
case class PlayerHint() extends Event

object World extends Publisher {
  type Level = Array[Array[WorldElement]]
  val levels: ListBuffer[Level] = ListBuffer[Level]()

  val MAX_WIDTH  = 19
  val MAX_HEIGHT = 16

  var currentLevel: Level = _

  var manX = 0
  var manY = 0

  var toGoX = 0
  var toGoY = 0

  var nb_move = 0

  def loadLevel(num: Int) {
    currentLevel = copyMap(levels(num))
    nb_move = 0
    publish(PlayerMove())
  }

  def onKeyPress(keyCode: Value) {
    keyCode match {
      case Left  => move(-1, 0)
      case Right => move(1, 0)
      case Up    => move(0, 1)
      case Down  => move(0, -1)
      case _     =>
    }
  }

  def fromString(stringWorld: String): Level = {
    val level             = Array.ofDim[WorldElement](MAX_WIDTH, MAX_HEIGHT)
    val listStringMap     = stringWorld.toList
    var wEl: WorldElement = null
    var x, y              = 0
    for (i <- listStringMap.indices) {
      listStringMap(i) match {
        case '#' => wEl = Wall()
        case '.' => wEl = GoalSquare()
        case '$' => wEl = Box()
        case '@' => wEl = Player()
        case '+' => wEl = PlayerOnGoalSquare()
        case '*' => wEl = BoxOnGoalSquare()
        case ' ' => wEl = Floor()
        case '\n' =>
          y += 1
          x = 0
        case _ =>
      }
      if (wEl != null) {
        level(x)(y) = wEl
        x += 1
        wEl = null
      }
    }
    level
  }

  def fromFile(filePath: String) {
    var lines: ListBuffer[String] = ListBuffer()
    val stream                    = Source.fromResource(filePath)
    for (line <- stream.getLines()) {
      line match {
        case "" =>
          levels += fromString(lines.mkString("\n"))
          lines = ListBuffer()
        case _ => lines += line
      }
    }
  }

  private def locatePlayer() {
    for {
      i <- currentLevel.indices
      j <- currentLevel(i).indices
    } {
      if (currentLevel(i)(j).isInstanceOf[Player]) {
        manX = i
        manY = j
        return
      }
    }
  }

  private def move(x: Int, y: Int) {
    locatePlayer()
    toGoX = x
    toGoY = y
    nb_move += 1
    currentLevel(manX + toGoX)(manY - toGoY) match {
      case _: Box             => moveBox()
      case _: Floor           => movePlayerToFloor()
      case _: GoalSquare      => movePlayerToGoalSquare()
      case _: BoxOnGoalSquare => moveBox()
      case _                  => nb_move -= 1
    }
    publish(PlayerMove())
  }

  private def moveBox() {
    val (x, y)               = nextCase(manX, manY)
    val (bhCrateX, bhCrateY) = nextCase(x, y)
    currentLevel(bhCrateX)(bhCrateY) match {
      case _: Floor =>
        currentLevel(bhCrateX)(bhCrateY) = Box()
        movePlayToFloorOrGoalSquare(x, y)
      case _: GoalSquare =>
        currentLevel(bhCrateX)(bhCrateY) = BoxOnGoalSquare()
        movePlayToFloorOrGoalSquare(x, y)
      case _ =>
    }
  }

  private def movePlayerToFloor() {
    letFloorOrGoalSquare()
    manX = manX + toGoX
    manY = manY - toGoY
    currentLevel(manX)(manY) = Player()
  }

  private def movePlayerToGoalSquare() {
    letFloorOrGoalSquare()
    manX = manX + toGoX
    manY = manY - toGoY
    currentLevel(manX)(manY) = PlayerOnGoalSquare()

  }

  private def letFloorOrGoalSquare() {
    currentLevel(manX)(manY) match {
      case _: Player =>
        currentLevel(manX)(manY) = element.Floor()
      case _: PlayerOnGoalSquare =>
        currentLevel(manX)(manY) = GoalSquare()
    }
  }

  private def movePlayToFloorOrGoalSquare(x: Int, y: Int) {
    currentLevel(x)(y) match {
      case _: Box =>
        movePlayerToFloor()
      case _: BoxOnGoalSquare =>
        movePlayerToGoalSquare()
    }
  }

  private def nextCase(x: Int, y: Int) = {
    (x + toGoX, y - toGoY)
  }

  private def copyMap(originMap: Level): Level = {
    val copyMap = Array.ofDim[WorldElement](MAX_WIDTH, MAX_HEIGHT)
    for {
      x <- originMap.indices
      y <- originMap(x).indices
    } copyMap(x)(y) = originMap(x)(y)
    copyMap
  }
}
