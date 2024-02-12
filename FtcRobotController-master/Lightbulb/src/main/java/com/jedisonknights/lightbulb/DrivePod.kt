package com.jedisonknights.lightbulb

import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.atan

open class DrivePod(BetterMotor drive_motor, Servo servo_pod) {
  private val drive_motor = drive_motor
  private val servo_pod = servo_pod
  
  private var angle : Double = 0.0

  private fun update(left_stick_x : Double, left_stick_y : Double, right_stick_x : Double) {
    if (left_stick_x == 0) {
      
    }
  }

  private fun goToDegree(theta : Double) {
    if (theta > angle + 180) {
      
    }
  }

  private fun getAngle() {
    
  }
}
