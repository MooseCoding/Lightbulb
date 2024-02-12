package com.jedisonknights.lightbulb

open class Vector2d(x : Int, y : Int, theta : Double) {
  val x_pos = x 
  val y_pos = y
  val angle = theta

  private fun equals(Vector2d vector) {
    if (this.x_pos == vector.x_pos && this.y_pos == vector.y_pos && this.angle = vector.angle) {
      return true
    }
    return false
  }

  
}

