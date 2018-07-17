package cn.galudisu.fp

import cn.galudisu.fp.Macros._
import org.slf4j._

object DebugTest {
  def main(args: Array[String]): Unit = {
    val log = LoggerFactory.getLogger(this.getClass)
    log.DEBUG("Hello World")
  }
}
