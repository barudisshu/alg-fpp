package cn.galudisu.fp._2_4_builder

/**
  * Scala实现的构造模式
  *
  * @author galudisu
  */
object Builder extends App {

  case class UsedCar(make: String, // 1
                     model: String,
                     kmDriven: Int,
                     yearOfManufacturing: Int,
                     hasGps: Boolean = false,
                     hasAc: Boolean = false,
                     hasAirBags: Boolean = false,
                     hasAbs: Boolean = false) {
    require(yearOfManufacturing > 1970, "Incorrect year") // 2
    require(checkMakeAndModel(), "Incorrect make and model")

    def checkMakeAndModel(): Boolean =
      if (make == "Maruti") {
        model == "alto"
      } else if (make == "Toyota") {
        model == "Corolla"
      } else {
        true
      }
  }
  val usedMaruti = UsedCar(
    model = "alto",
    make = "Maruti",
    kmDriven = 10000,
    yearOfManufacturing = 1980,
    hasAbs = true,
    hasAirBags = true) // 3

  println(usedMaruti)

  val usedCorolla = usedMaruti.copy(make = "Toyota", model = "Corolla")  // 4

  println(usedCorolla)
  // val wrongModel = usedCorolla.copy(model = "alto") // throws - Incorrect make and model
}
