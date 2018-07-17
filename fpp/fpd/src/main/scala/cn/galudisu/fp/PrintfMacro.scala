package cn.galudisu.fp

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object PrintfMacro {

  def printf(format: String, params: Any*) = macro printf_impl2

  /**
   * source code from http://docs.scala-lang.org/overviews/macros/overview.html#a-complete-example
   */
  def printf_impl1(c: blackbox.Context)(format: c.Expr[String], params: c.Expr[Any]*): c.Expr[Unit] = {
    import c.universe._

    val Literal(Constant(s_format: String)) = format.tree

    val evals = ListBuffer[ValDef]()

    def precompute(value: Tree, tpe: Type): Ident = {
      val freshName = newTermName(c.fresh("eval$"))
      evals += ValDef(Modifiers(), freshName, TypeTree(tpe), value)
      Ident(freshName)
    }

    val paramsStack = mutable.Stack[Tree](params map (_.tree): _*)

    val refs = s_format.split("(?<=%[\\w%])|(?=%[\\w%])") map {
      case "%d" => precompute(paramsStack.pop, typeOf[Int])
      case "%s" => precompute(paramsStack.pop, typeOf[String])
      case "%%" => Literal(Constant("%"))
      case part => Literal(Constant(part))
    }

    val stats: ListBuffer[Tree] = evals ++ refs.map(ref => reify(print(c.Expr[Any](ref).splice)).tree)

    c.Expr[Unit](Block(stats.toList, Literal(Constant(()))))
  }


  /**
   * refacted using Quasiquotes to make macro code simple
   */
  def printf_impl2(c: blackbox.Context)(format: c.Expr[String], params: c.Expr[Any]*): c.Expr[Unit] = {
    import c.universe._

    val q"${s_format:String}" = format.tree

    val evals = ListBuffer[Tree]()

    def precompute(value: Tree, tpe: Type): Tree = {
      val freshName = TermName(c.freshName("eval$"))
      val valdef = q"val $freshName: $tpe = $value"
      evals += valdef
      q"$freshName"
    }

    // 参数
    val paramsStack = mutable.Stack[Tree](params map (_.tree): _*)

    val refs = s_format.split("(?<=%[\\w%])|(?=%[\\w%])") map {
      case "%d" => precompute(paramsStack.pop, typeOf[Int])
      case "%s" => precompute(paramsStack.pop, typeOf[String])
      case "%%" => Literal(Constant("%"))
      case part => Literal(Constant(part))
    }

    val stats: ListBuffer[Tree] = evals ++ refs.map(ref => q"print($ref)")

    c.Expr[Unit](q"..$stats")
  }


}