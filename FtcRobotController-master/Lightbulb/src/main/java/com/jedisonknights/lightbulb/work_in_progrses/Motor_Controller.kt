package com.jedisonknights.lightbulb.work_in_progrses

import kotlinx.coroutines.*


open class Motor_Controller(motor : BetterMotor) {
    private var isActive : Boolean = false
    private val motor : BetterMotor = motor
    private var integral : Double = 0.0

    suspend fun PIDF_CONTROLLER(p : Double, i : Double, d: Double, f: Double) = coroutineScope {
        launch {
            var error : Int = motor.ENCODER_TARGET - motor.motor.currentPosition
            var last_time = motor.last_time_talked
            var this_time = motor.get_time()
            integral += i * (error * ( - previous_time))
            var pidf_corrected_error: Double = error * p + integral*i +
        }
    }

     fun run(power: Double){
         runBlocking {
             when (motor.current_runmode) {
                 RUNMODE.Power -> {

                 }

                 RUNMODE.Encoder -> {
                     launch {
                         while (motor.motor.currentPosition != motor.ENCODER_TARGET) {
                             motor.motor.power = power
                         }
                     }
                 }

                 RUNMODE.PIDF -> {
                     launch {

                     }
                 }

                 RUNMODE.PIDF_LIMIT -> {
                     launch {

                     }
                 }
             }
         }
    }

    fun


}