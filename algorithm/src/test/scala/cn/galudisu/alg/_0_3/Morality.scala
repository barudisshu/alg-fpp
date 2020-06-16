package cn.galudisu.alg._0_3

/**
 * 流动红旗择优问题
 *
 * 假设德育评比项为m1, m2, m3, ... 。每项分数为(0, S]。某学校有h1, h2, h3, ... 个年级，每个年级对应有c1, c2, c3, ... 个班级。当分数达到k ∈ (0,
 * S]时对班级颁发流动红旗。请建模求解该方案的获奖记录。
 */
object Morality extends App {

  // 评比项
  case class Item(id: String, name: String)
  case class Clazz(id: String, name: String)
  case class Grade(id: String, name: String)

  // 评比记录
  case class Quota(id: String, grade: Grade, clazz: Clazz, item: Item, score: Double)

  case class Result(id: String, clazz: Clazz, item: Item)

  /**
   *
   * @param quota 记录值
   * @param k     颁发条件
   */
  def filter(quota: List[Quota], k: Double): Either[UnsupportedOperationException, Iterable[Result]] = {

    if (quota.isEmpty || k <= 0)
      Left(new UnsupportedOperationException("Illegal params"))

    // 按年级分组, 之后按评比项再分组, 之后排序
    val result =
      quota
        .groupBy(f => f.grade)
        .flatMap(f => {
          f._2.groupBy(g => g.item).flatMap(h => {
            h._2.filter(op => op.score >= k).sortBy(_.score).map(k => Result(k.clazz.id, k.clazz, k.item))
          })
        })
    Right(result)
  }

  // prepare data

  val m1 = Item("1", "纪律")
  val m2 = Item("2", "卫生")
  val m3 = Item("3", "礼仪")
  val m4 = Item("4", "文明")
  val m5 = Item("5", "劳动")
  val m6 = Item("6", "思想")

  val c1  = Clazz("1", "2019届1班")
  val c2  = Clazz("2", "2019届2班")
  val c3  = Clazz("3", "2019届3班")
  val c4  = Clazz("4", "2019届4班")
  val c5  = Clazz("5", "2020届1班")
  val c6  = Clazz("6", "2020届2班")
  val c7  = Clazz("7", "2020届3班")
  val c8  = Clazz("8", "2020届4班")
  val c9  = Clazz("9", "2020届5班")
  val c10 = Clazz("10", "2021届1班")
  val c11 = Clazz("11", "2021届2班")

  val h1 = Grade("1", "1年级")
  val h2 = Grade("2", "2年级")
  val h3 = Grade("3", "3年级")

  val q11 = Quota("11", h1, c5, m1, 95.0)
  val q12 = Quota("12", h1, c5, m2, 96.0)
  val q13 = Quota("13", h1, c5, m3, 96.1)
  val q14 = Quota("14", h1, c5, m4, 95.5)
  val q15 = Quota("15", h1, c5, m5, 94.0)
  val q16 = Quota("16", h1, c5, m6, 80.0)

  val q21 = Quota("21", h1, c6, m1, 43.0)
  val q22 = Quota("22", h1, c6, m2, 43.0)
  val q23 = Quota("23", h1, c6, m3, 43.0)
  val q24 = Quota("24", h1, c6, m4, 43.0)
  val q25 = Quota("25", h1, c6, m5, 43.0)
  val q26 = Quota("26", h1, c6, m6, 43.0)

  val q31 = Quota("31", h1, c7, m1, 43.0)
  val q32 = Quota("32", h1, c7, m2, 43.0)
  val q33 = Quota("33", h1, c7, m3, 43.0)
  val q34 = Quota("34", h1, c7, m4, 43.0)
  val q35 = Quota("35", h1, c7, m5, 43.0)
  val q36 = Quota("36", h1, c7, m6, 43.0)

  val q41 = Quota("41", h1, c8, m1, 43.0)
  val q42 = Quota("42", h1, c8, m2, 43.0)
  val q43 = Quota("43", h1, c8, m3, 43.0)
  val q44 = Quota("44", h1, c8, m4, 43.0)
  val q45 = Quota("45", h1, c8, m5, 43.0)
  val q46 = Quota("46", h1, c8, m6, 43.0)

  val q51 = Quota("51", h1, c9, m1, 43.0)
  val q52 = Quota("52", h1, c9, m2, 43.0)
  val q53 = Quota("53", h1, c9, m3, 43.0)
  val q54 = Quota("54", h1, c9, m4, 43.0)
  val q55 = Quota("55", h1, c9, m5, 43.0)
  val q56 = Quota("56", h1, c9, m6, 43.0)

  val q61 = Quota("61", h2, c1, m1, 43.0)
  val q62 = Quota("62", h2, c1, m2, 43.0)
  val q63 = Quota("63", h2, c1, m3, 43.0)
  val q64 = Quota("64", h2, c1, m4, 43.0)
  val q65 = Quota("65", h2, c1, m5, 43.0)
  val q66 = Quota("66", h2, c1, m6, 43.0)

  val q71 = Quota("71", h2, c2, m1, 95.0)
  val q72 = Quota("72", h2, c2, m2, 43.0)
  val q73 = Quota("73", h2, c2, m3, 43.0)
  val q74 = Quota("74", h2, c2, m4, 43.0)
  val q75 = Quota("75", h2, c2, m5, 43.0)
  val q76 = Quota("76", h2, c2, m6, 43.0)

  val q81 = Quota("81", h2, c3, m1, 43.0)
  val q82 = Quota("82", h2, c3, m2, 43.0)
  val q83 = Quota("83", h2, c3, m3, 43.0)
  val q84 = Quota("84", h2, c3, m4, 99.0)
  val q85 = Quota("85", h2, c3, m5, 43.0)
  val q86 = Quota("86", h2, c3, m6, 43.0)

  val q91 = Quota("91", h2, c4, m1, 43.0)
  val q92 = Quota("92", h2, c4, m2, 43.0)
  val q93 = Quota("93", h2, c4, m3, 98.1)
  val q94 = Quota("94", h2, c4, m4, 43.0)
  val q95 = Quota("95", h2, c4, m5, 96.0)
  val q96 = Quota("96", h2, c4, m6, 43.0)

  val qa1 = Quota("a1", h3, c10, m1, 43.0)
  val qa2 = Quota("a2", h3, c10, m2, 43.0)
  val qa3 = Quota("a3", h3, c10, m3, 43.0)
  val qa4 = Quota("a4", h3, c10, m4, 90.0)
  val qa5 = Quota("a5", h3, c10, m5, 43.0)
  val qa6 = Quota("a6", h3, c10, m6, 43.0)

  val qb1 = Quota("b1", h3, c11, m1, 43.0)
  val qb2 = Quota("b2", h3, c11, m2, 96.0)
  val qb3 = Quota("b3", h3, c11, m3, 43.0)
  val qb4 = Quota("b4", h3, c11, m4, 43.0)
  val qb5 = Quota("b5", h3, c11, m5, 97.1)
  val qb6 = Quota("b6", h3, c11, m6, 43.0)

  // @formatter:off
  val quotas = List(
    q11,q12,q13,q14,q15,q16,
    q21,q22,q23,q24,q25,q26,
    q31,q32,q33,q34,q35,q36,
    q41,q42,q43,q44,q45,q46,
    q51,q52,q53,q54,q55,q56,
    q61,q62,q63,q64,q65,q66,
    q71,q72,q73,q74,q75,q76,
    q81,q82,q83,q84,q85,q86,
    q91,q92,q93,q94,q95,q96,
    qa1,qa2,qa3,qa4,qa5,qa6,
    qb1,qb2,qb3,qb4,qb5,qb6
  )
  // @formatter:on

  val resultList = filter(quotas, 95)

  // @formatter:off
  resultList match {
    case Left(e) => println(e.getMessage)
    case Right(v) => v.groupBy(_.clazz.name).map(f => f._1 -> f._2.map(k => k.item.name)).map(f => println(s"${f._1}: ${f._2.mkString(", ")}"))
  }
  // @formatter:on
}
