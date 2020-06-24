package cn.galudisu.game.ui

import java.awt.{Color, Graphics2D}

import cn.galudisu.game.World
import cn.galudisu.game.element._

import scala.swing.Panel
import scala.swing.Swing._
import scala.swing.event._

case class LevelFinish() extends Event

class UIWorld() extends Panel {
  var currentLevelNum: Int = _

  background = Color.white
  preferredSize = (500, 400)
  focusable = true
  listenTo(keys)
  reactions += {
    case KeyTyped(_, 't', _, _) =>
      publish(LevelFinish())
    case KeyTyped(_, 'r', _, _) =>
      restart()
    case KeyPressed(_, key, _, _) =>
      World.onKeyPress(key)
      repaint()
    case _: FocusLost => repaint()
  }
  var success = true

  def loadWorld(levelNum: Int) {
    currentLevelNum = levelNum
    World.loadLevel(levelNum)
    repaint()
    requestFocus()
  }

  def nextLevel() {
    loadWorld(currentLevelNum + 1)
  }

  def restart() {
    World.loadLevel(currentLevelNum)
    repaint()
  }

  override def paintComponent(g: Graphics2D) {
    g.clearRect(0, 0, size.width, size.height)
    val level = World.currentLevel
    val b: javax.swing.Box = new javax.swing.Box(2)
    for (x <- level.indices) {
      for (y <- level(x).indices if level(x)(y) != null) {
        val actor = WorldElement.representation(level(x)(y))
        g.drawString(actor, (x + 1) * 20, (y + 1) * 20)
        level(x)(y) match {
          case Box() =>
            success = false
          case _ =>
        }
      }
    }
    if (success) {
      publish(LevelFinish())
    } else {
      success = true
    }
  }
}
