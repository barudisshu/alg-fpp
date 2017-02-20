package cn.galudisu.fp._2_3_factory_method

/**
  * @author galudisu
  */
trait Currency2 {
  def getConversionRateToIndianRupee: String
}

/**
  * Scala实现的工厂方法
  */
object Currency2Converter extends App{

  private object EuroToRupee extends Currency2 {
    override def getConversionRateToIndianRupee: String = "82"
  }

  private object DollarToRupee extends Currency2 {
    override def getConversionRateToIndianRupee: String = "60"
  }

  private object NoIdea extends Currency2 {
    override def getConversionRateToIndianRupee: String = "No Idea"
  }

  // the currency factory method
  // Note: Scala if statement is an expression.

  def apply(s: String): Currency2 = {
    if (s == "Dollar")
      DollarToRupee
    else if (s == "Euro")
      EuroToRupee
    else
      NoIdea
  }

  val c = Currency2Converter("Dollar")
  println(c.getConversionRateToIndianRupee)
}

