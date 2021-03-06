package cn.galudisu.fp

import org.slf4j._

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

object Macros {

  implicit class LoggerEx(val logger: Logger) {

    def DEBUG(msg: String): Unit = macro LogMacros.DEBUG1
    def DEBUG(msg: String, exception: Exception): Unit = macro LogMacros.DEBUG2

  }

  object LogMacros {
    def DEBUG1(c: Context)(msg: c.Tree): c.Tree = {
      import c.universe._
      val pre = c.prefix
      q"""
         val x = $pre.logger
         println("=" * 60)
         if( x.isDebugEnabled ) x.debug($msg)
         println("=" * 60)
       """
    }

    def DEBUG2(c:Context)(msg: c.Tree, exception: c.Tree): c.Tree = {
      import c.universe._
      val pre = c.prefix
      q"""
         val x = $pre.logger
         if(x.isDebugEnabled) x.debug( $msg, $exception )
       """
    }
  }
}
