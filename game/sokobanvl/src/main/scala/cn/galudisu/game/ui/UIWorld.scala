package cn.galudisu.game.ui

import java.awt.{Color, Graphics2D, RenderingHints}

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
    for (x <- level.indices) {
      for (y <- level(x).indices if level(x)(y) != null) {
        level(x)(y) match {
          case Box() =>
            g.setColor(new Color(165, 130, 90))
            g.fillRect((x + 1) * 20, (y + 1) * 20, 20, 20)
            g.fill3DRect((x + 1) * 20, (y + 1) * 20, 20 - 1, 20 - 1, true)
            g.setColor(new Color(165, 165, 165))
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g.drawLine((x + 1) * 20 + 4, (y + 1) * 20 + 4, (x + 1) * 20 + 20 - 4, (y + 1) * 20 + 20 - 4)
            g.drawLine((x + 1) * 20 + 4, (y + 1) * 20 + 20 - 4, (x + 1) * 20 + 20 - 4, (y + 1) * 20 + 4)
          case BoxOnGoalSquare() =>
            g.setColor(new Color(255, 20, 20))
            g.fillRect((x + 1) * 20, (y + 1) * 20, 20, 20)
            g.fill3DRect((x + 1) * 20, (y + 1) * 20, 20 - 1, 20 - 1, true)
          case Player() =>
            g.setColor(Color.WHITE)
            g.draw3DRect((x + 1) * 20, (y + 1) * 20, 20 - 1, 20 - 1, true)
            g.setColor(Color.GREEN)
            g.fill3DRect((x + 1) * 20 + 1, (y + 1) * 20 + 1, 20 - 2, 20 - 2, true)
          case PlayerOnGoalSquare() =>
            g.setColor(new Color(120, 160, 160))
            g.draw3DRect((x + 1) * 20, (y + 1) * 20, 20 - 1, 20 - 1, true)
            g.setColor(new Color(20, 250, 20))
            g.fill3DRect((x + 1) * 20 + 1, (y + 1) * 20 + 1, 20 - 2, 20 - 2, true)
          case GoalSquare() =>
            g.setColor(new Color(90, 160, 90))
            g.drawOval((x + 1) * 20 + 5, (y + 1) * 20 + 5, 10, 10)
            g.fillOval((x + 1) * 20 + 5, (y + 1) * 20 + 5, 10, 10)
          case Wall() =>
            g.setColor(new Color(150, 150, 150))
            g.fillRect((x + 1) * 20, (y + 1) * 20, 20, 20)
            g.fill3DRect((x + 1) * 20, (y + 1) * 20, 20 - 1, 20 - 1, true)
          case Floor() =>
            g.setColor(Color.WHITE)
        }
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
