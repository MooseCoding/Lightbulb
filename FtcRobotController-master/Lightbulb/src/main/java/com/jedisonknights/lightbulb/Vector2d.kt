package com.jedisonknights.lightbulb

open class Vector2d(x : Double, y : Double, theta : Double) {
  var x_pos = x
  var y_pos = y
  var angle = theta

  fun vector_equals(vector : Vector2d) : Boolean {
    return this.x_pos == vector.x_pos && this.y_pos == vector.y_pos && this.angle == vector.angle
  }
}

