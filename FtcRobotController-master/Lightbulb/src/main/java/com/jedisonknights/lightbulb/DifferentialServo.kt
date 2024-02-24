package com.jedisonknights.lightbulb

import com.qualcomm.robotcore.hardware.Servo  
import com.qualcomm.robotcore.hardware.AnalogInput

open class DifferentialServo(axon_servo_encoder : AnalogInput) {
  private var servo_encoder : AnalogInput = axon_servo_encoder
  private val max_voltage : Double = 0.0 //find max voltage of axon max servos

  fun getPosition() : Double {
    return servo_encoder.getVoltage() / max_voltage * 360 
  }
}
