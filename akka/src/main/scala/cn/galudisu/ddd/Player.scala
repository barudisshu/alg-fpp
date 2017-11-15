package cn.galudisu.ddd

import java.util.UUID

class Player {

  var id: PlayerId = PlayerId(UUID.randomUUID().toString)
  var xp: Int = 0
  var credits: Int = 0
}
