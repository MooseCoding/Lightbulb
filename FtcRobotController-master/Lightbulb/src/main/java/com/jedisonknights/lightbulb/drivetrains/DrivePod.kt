package com.jedisonknights.lightbulb.drivetrains

import com.jedisonknights.lightbulb.work_in_progrses.BetterMotor
import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.PI
import kotlin.math.atan

open class DrivePod(drive_motor : BetterMotor, servo_pod : Servo) {
  private val drive_motor = drive_motor
  private val servo_pod = servo_pod
  
  private var angle : Double = 0.0

  private fun update(left_stick_x : Double, left_stick_y : Double, right_stick_x : Double) {
    if (left_stick_y == 0.0) {
        goToAngle(right_stick_x*2*PI)
    }

    else if (left_stick_x == 0.0) {
      goToAngle(right_stick_x*2*PI) //will have to be tuned before driving for optimal performance
    }

    else if (right_stick_x == 0.0) {
      goToAngle(atan(left_stick_y/left_stick_x))
    }

    else {
      goToAngle(atan(left_stick_y/left_stick_x)+right_stick_x*PI*2)
    }
  }

  fun goToAngle(theta : Double) {
    if (theta > angle + 180) {
      goToAngle(theta-360)
    }
    if (theta < angle) {

    }
    else {

    }
  }

  fun run(power : Double) {
    drive_motor.run(power)

  }
}
