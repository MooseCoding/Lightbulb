package com.jedisonknights.lightbulb.work_in_progrses

import kotlin.concurrent.thread


open class Motor_Controller(motor : BetterMotor) {
    private var isActive : Boolean = false
    private val motor : BetterMotor = motor

    fun PIDF_CONTROLLER(p : Double, i : Double, d: Double, f: Double) {
        var error : Int = motor.ENCODER_TARGET - motor.motor.currentPosition
        var pidf_corrected_error : Double = error*p+i
    }

    fun run(power : Double) {
        when (motor.current_runmode) {
            RUNMODE.Power -> {
                thread {
                    motor.motor.power = power
                }
            }
            RUNMODE.Encoder -> {
                thread {
                    while(motor.motor.currentPosition!= motor.ENCODER_TARGET) {
                        motor.motor.power = power
                    }
                }
            }
            RUNMODE.PIDF -> {
                thread {

                }
            }
            RUNMODE.PIDF_LIMIT -> {
                thread {

                }
            }
        }
    }


}