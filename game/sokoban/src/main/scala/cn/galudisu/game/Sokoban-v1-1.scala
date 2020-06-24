/*
 * Sokoban Solver in Copris
 * by Naoyuki Tamura
 * http://bach.istc.kobe-u.ac.jp/copris/puzzles/sokoban/
 */
package cn.galudisu.game

import jp.kobe_u.copris._
import jp.kobe_u.copris.dsl._

import scala.io.Source

case class Puzzle(m: Int, n: Int, board: Seq[Seq[String]]) {
  require(board.size == m && m >= 2)
  require(board.forall(_.size == n) && n >= 2)
  def isWall(i: Int, j: Int)   = board(i)(j) == "#"
  def isGoal(i: Int, j: Int)   = board(i)(j).matches("""[\.\*\+]""")
  def isPlayer(i: Int, j: Int) = board(i)(j).matches("""[\@\+]""")
  def isBox(i: Int, j: Int)    = board(i)(j).matches("""[o\*\$]""")
  def isInside(i: Int, j: Int) = 0 <= i && i < m && 0 <= j && j < n && !isWall(i, j)
  val nodes                    = for (i <- 0 until m; j <- 0 until n; if !isWall(i, j)) yield (i, j)
  val dijs                     = Seq((-1, 0), (1, 0), (0, -1), (0, 1))
  def next(i: Int, j: Int) =
    for ((di, dj) <- dijs; if isInside(i + di, j + dj)) yield (i + di, j + dj)
  val badBlocks: Set[Set[(Int, Int)]] = {
    val blocks = for {
      (i, j) <- nodes.toSet
      block = for ((i1, j1) <- Set((i, j), (i, j + 1), (i + 1, j), (i + 1, j + 1)); if isInside(i1, j1))
        yield (i1, j1)
      if block.exists(ij => !isGoal(ij._1, ij._2))
    } yield block
    val blocks1 = blocks.filterNot(b1 => blocks.exists(b2 => b1.size > b2.size && b2.subsetOf(b1)))
    blocks1
  }
  val deadBlocks: Set[Set[(Int, Int)]] = {
    val hBlocks = for {
      (i, j) <- nodes.toSet
      if isWall(i, j - 1)
      len <- 2 until n - 1
      if j + len < n && isWall(i, j + len) && (0 until len).forall(k => isInside(i, j + k))
      if (0 until len).forall(k => isWall(i - 1, j + k) || isWall(i + 1, j + k))
    } yield (0 until len).map(k => (i, j + k)).toSet
    val vBlocks = for {
      (i, j) <- nodes.toSet
      if isWall(i - 1, j)
      len <- 2 until m - 1
      if i + len < m && isWall(i + len, j) && (0 until len).forall(k => isInside(i + k, j))
      if (0 until len).forall(k => isWall(i + k, j - 1) || isWall(i + k, j + 1))
    } yield (0 until len).map(k => (i + k, j)).toSet
    hBlocks ++ vBlocks
  }
}

object Solver {
  val name                 = "sokoban.Solver"
  var help                 = false
  var quiet                = 0
  var verbose              = 0
  var options: Set[String] = Set.empty
  var puzzle: Puzzle       = null

  def parse(source: Source) = {
    val lines = source.getLines.map(_.trim)
    val m     = lines.next.toInt
    val n     = lines.next.toInt
    val board = for (i <- 0 until m; s = lines.next)
      yield s.map(_.toString)
    source.close
    Puzzle(m, n, board)
  }

  def define(minSteps: Int, maxSteps: Int) {
    def declare(step: Int) {
      // Player
      int('pi (step), 0, puzzle.m - 1); int('pj (step), 0, puzzle.n - 1)
      for (i <- 0 until puzzle.m; j <- 0 until puzzle.n) {
        int('p (step, i, j), 0, 1)
        add(('p (step, i, j) === 1) <==> ('pi (step) === i && 'pj (step) === j))
        if (puzzle.isWall(i, j))
          add('p (step, i, j) === 0)
      }
      // Boxes
      for ((i, j) <- puzzle.nodes) {
        int('b (step, i, j), 0, 1)
        // add(('b(step,i,j) === 0) || ('p(step,i,j) === 0))
      }
      // Goal
      int('g (step), 0, 1)
      val gs = for ((i, j) <- puzzle.nodes; if puzzle.isGoal(i, j))
        yield 'b (step, i, j) === 1
      add(('g (step) === 1) <==> And(gs))
      add(('steps <= step) ==> ('g (step) === 1))
      // Hints
      for (block <- puzzle.badBlocks)
        add(!And(block.map(ij => 'b (step, ij._1, ij._2) === 1)))
      for (block <- puzzle.deadBlocks) {
        val count = block.count(ij => puzzle.isGoal(ij._1, ij._2))
        val bs    = for ((i, j) <- block) yield 'b (step, i, j)
        add(Add(bs) <= count)
      }
    }
    def initial(step: Int) {
      for ((i, j) <- puzzle.nodes) {
        add('p (step, i, j) === (if (puzzle.isPlayer(i, j)) 1 else 0))
        add('b (step, i, j) === (if (puzzle.isBox(i, j)) 1 else 0))
      }
    }
    def reachability(step: Int) {
      for ((i, j) <- puzzle.nodes) {
        int('r (step, i, j), 0, 1)
        int('x (step, i, j), 0, puzzle.nodes.size)
        for ((i1, j1) <- puzzle.next(i, j))
          int('e (step, i1, j1, i, j), 0, 1)
        int('in (step, i, j), 0, 1)
      }
      for ((i, j) <- puzzle.nodes) {
        val in = for ((i1, j1) <- puzzle.next(i, j)) yield 'e (step, i1, j1, i, j)
        add('in (step, i, j) === Add(in))
        for ((i1, j1) <- puzzle.next(i, j))
          add(('e (step, i1, j1, i, j) === 1) ==> ('x (step, i1, j1) > 'x (step, i, j)))
        add(('p (step, i, j) === 1) ==> ('in (step, i, j) === 0))
        add(('p (step, i, j) === 0) ==> (('in (step, i, j) === 1) || ('x (step, i, j) === 0)))
        add(('b (step, i, j) === 1) ==> ('in (step, i, j) === 0))
        add(('r (step, i, j) === 1) <==> (('p (step, i, j) === 1) || ('in (step, i, j) > 0)))
      }
    }
    def transition(step0: Int, step1: Int) {
      // Player and Move
      int('di (step0), -1, 1)
      int('dj (step0), -1, 1)
      add(('g (step0) === 1) <==> (('di (step0) === 0) && ('dj (step0) === 0)))
      add(
        (('di (step0) === 0) && ('dj (step0) === 0)) ==> (('pi (step0) === 'pi (step1)) && ('pj (step0) === 'pj (
          step1))))
      reachability(step0)
      for ((i, j) <- puzzle.nodes) {
        val push = for ((di, dj) <- puzzle.dijs; if puzzle.isInside(i - di, j - dj) && puzzle.isInside(i + di, j + dj))
          yield
            ('di (step0) === di) && ('dj (step0) === dj) && ('r (step0, i - di, j - dj) === 1) && ('b (step0,
                                                                                                       i + di,
                                                                                                       j + dj) === 0)
        add(
          ('p (step1, i, j) === 1) ==> ((('p (step0, i, j) === 1) && ('g (step0) === 1)) ||
            (('b (step0, i, j) === 1) && Or(push))))
      }
      // Blocks
      for ((i, j) <- puzzle.nodes) {
        val stay = ('b (step0, i, j) === 1) && ('p (step1, i, j) === 0)
        val push = for ((di, dj) <- puzzle.dijs; if puzzle.isInside(i - di, j - dj))
          yield
            ('di (step0) === di) && ('dj (step0) === dj) && ('b (step0, i - di, j - dj) === 1) && ('p (step1,
                                                                                                       i - di,
                                                                                                       j - dj) === 1)
        add(('b (step1, i, j) === 1) <==> (stay || Or(push)))
      }
    }
    int('steps, minSteps, maxSteps)
    declare(0)
    initial(0)
    for (step <- 1 to maxSteps) {
      declare(step)
      transition(step - 1, step)
    }
  }

  def showSolution(steps: Int) {
    println("Steps = " + steps)
    for (step <- 0 to steps) {
      println("Step = " + step)
      for (i <- 0 until puzzle.m) {
        val cs = for (j <- 0 until puzzle.n) yield {
          if (puzzle.isWall(i, j)) "#"
          else if (solution('p (step, i, j)) > 0 && puzzle.isGoal(i, j)) "+"
          else if (solution('p (step, i, j)) > 0) "@"
          else if (solution('b (step, i, j)) > 0 && puzzle.isGoal(i, j)) "*"
          else if (solution('b (step, i, j)) > 0) "$"
          else if (puzzle.isGoal(i, j)) "."
          else " "
        }
        println(cs.mkString)
      }
    }
    println
  }

  def solve {
    var found    = false
    var minSteps = 0
    var maxSteps = 4
    if (verbose >= 1) {
      println("Rows = " + puzzle.m)
      println("Cols = " + puzzle.n)
    }
    while (!found) {
      if (verbose >= 2) {
        println("Steps = " + minSteps + ".." + maxSteps)
      }
      init
      define(minSteps, maxSteps)
      if (find)
        found = true
      else {
        minSteps = maxSteps + 1
        maxSteps *= 2
      }
    }
    if (options.contains("opt")) {
      init
      define(minSteps, maxSteps)
      minimize('steps)
      if (findOpt)
        found = true
    }
    if (found) {
      val steps = solution('steps)
      showSolution(steps)
    }
  }

  def main(args: Array[String]) = {
    var fileName   = ""
    var solverName = ""
    def parseOptions(args: List[String]): List[String] = args match {
      case "-o" :: opt :: rest => { options = opt.split(",").toSet; parseOptions(rest) }
      case "-s1" :: solver :: rest => {
        solverName = solver
        val satSolver = new sugar.SatSolver1(solver)
        use(new sugar.Solver(csp, satSolver))
        parseOptions(rest)
      }
      case "-s2" :: solver :: rest => {
        solverName = solver
        val satSolver = new sugar.SatSolver2(solver)
        use(new sugar.Solver(csp, satSolver))
        parseOptions(rest)
      }
      case "-smt" :: solver :: rest => {
        solverName = solver
        val smtSolver = new smt.SmtSolver(solver)
        use(new smt.Solver(csp, smtSolver))
        parseOptions(rest)
      }
      case "-q" :: rest => { quiet += 1; parseOptions(rest) }
      case "-v" :: rest => { verbose += 1; parseOptions(rest) }
      case "-h" :: rest => { help = true; parseOptions(rest) }
      case _ =>
        args
    }
    parseOptions(args.toList) match {
      case Nil if !help => {
        fileName = "-"
        puzzle = parse(Source.stdin)
      }
      case file :: Nil if !help => {
        fileName = file
        puzzle = parse(Source.fromFile(file))
      }
      case _ => {
        println("Usage: scala " + name + " [options] [inputFile]")
        println("\t-o options  : Set options")
        println("\t-v          : Increase verbosity level")
        println("\t-q          : Increase quiet level")
        println("\t-s1 solver  : Use SAT solver with one argument (clasp, etc.)")
        println("\t-s2 solver  : Use SAT solver with two arguments (minisat, etc.)")
        println("\t-smt solver : Use SMT solver (z3, etc.)")
        println("\t-dump file  : Output Sugar CSP to the specified file")
        println("\t-h          : Show this help message")
        System.exit(1)
      }
    }
    if (verbose >= 1) {
      println("File = " + fileName)
      println("Solver = " + solverName)
      println("Options = " + options.mkString(","))
    }
    solve
    if (verbose >= 2)
      println("Stats = " + stats)
  }
}
