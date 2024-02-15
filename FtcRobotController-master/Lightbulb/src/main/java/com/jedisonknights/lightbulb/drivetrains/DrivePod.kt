package com.jedisonknights.lightbulb.drivetrains

import com.qualcomm.robotcore.hardware.DcMotor;
import com.jedisonknights.lightbulb.DifferentialServo
import kotlin.math.PI
import kotlin.math.atan

open class DrivePod(drive_motor : DcMotor, servo_pod : DifferentialServo, servo_encoder : AnalogInput){
  private val drive_motor = drive_motor
  private val servo_pod = servo_pod
  private val servo_encoder : DifferentialServo = servo_encoder
  private val degree_pos_motor : Double = 0.0 //find position required to turn motor 1 degree clockwise 
  private val servo_pos_motor : Double = 0.0 
  
  private var angle : Double = 0.0

  fun teleop_update(left_stick_x : Double, left_stick_y : Double, right_stick_x : Double, current_angle : Double) {
    angle = current_angle
    
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
    if (theta > angle) { //make sure this actually works going forward
      drive_motor.setPosition(degree_pos_motor*theta/360 + drive_motor.getPosition()) //find the value to rotate 1 degree
      servo_pod.setPosition(degree_pos_servo*theta/360 + servo_encoder.getPosition())
      drive_motor.setPower(1) //find the value to rotate 1 degree
      servo_pod.setPower(1)
    }
    else {//make sure this one rotates reversed
      drive_motor.setPosition(-degree_pos_motor*theta/360 + drive_motor.getPosition())//find the value to rotate 1 degree
      servo_pod.setPosition(-degree_pos_servo*theta/360 + servo_encoder.getPosition())//find the value to rotate 1 degree
      drive_motor.setPower(1)
      servo_pod.setPower(1)
    }
  }

  fun drive_forward(power : Double) {
    drive_motor.setPower(power)
    servo_pod.setPower(-power)
  }

  fun control_drive(power_motor : Double, power_servo : Double) {
    drive_motor.setPower(power_motor)
    servo_pod.setPower(power_servo)
  }

  fun strafe_right(power : Double) {
    goToAngle(90)
    drive_forward(power)
  }

  fun strafe_left(power : Double) {
    goToAngle(90)
    drive_forward(-power)
  }
}
