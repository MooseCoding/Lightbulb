package com.jedisonknights.lightbulb.drivetrains

import com.qualcomm.robotcore.hardware.Servo

open class Servo_Controller(servo : Servo) : HardwareDevice {
    private val servo : Servo = servo
    private var isServoActive : Boolean = false
    override fun disable() {
        servo.close()
    }

    override fun getDeviceType(): String {
        return "Servo_Controller$servo"
    }

    fun run(power : Double) {
        isServoActive = true
        while (isServoActive) {
            Thread {

                servo.position = servo.position + 360
                if (!isServoActive) {
                    return@Thread
                }
            }.start()
        }
    }

    fun power() : Double {
        return servo.
    }
}