package com.jedisonknights.lightbulb

import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import kotlin.math.cos
import kotlin.math.sin

open class Position(start_pos: Vector2d, trackwidth : Double, forward_offset : Double, parallel_1 : Encoder, parallel_2: Encoder, perpendicular_1 : Encoder) : OpMode() {
    private var current_pos : Vector2d = start_pos
    private val trackwidth : Double = trackwidth
    private val forward_offset : Double = forward_offset
    private var parallel_1_start : Int = 0
    private var parallel_1_end : Int = 0
    private var parallel_2_start : Int = 0
    private var parallel_2_end : Int = 0
    private var perpendicular_1_start : Int = 0
    private var perpendicular_1_end : Int = 0
    private val parallel_1 = parallel_1
    private val parallel_2 = parallel_2
    private val perpendicular_1 = perpendicular_1
    override fun init() {}

    override fun loop()  {
        parallel_1_start = parallel_1.position
        parallel_2_start = parallel_2.position
        perpendicular_1_start = perpendicular_1.position

        val dt_parallel_1 : Int = (parallel_1_end-parallel_1_start)
        val dt_parallel_2 : Int = parallel_2_end-parallel_2_start
        val dt_perpendicular_1 : Int = perpendicular_1_end-perpendicular_1_start
        val dt_theta : Double = (dt_parallel_1-dt_parallel_2)/trackwidth
        val dt_center : Double = (dt_parallel_1+dt_parallel_2)/2.0
        val dt_horizontal : Double = (dt_perpendicular_1) - (forward_offset * dt_theta)

        current_pos.x_pos += dt_center*cos(current_pos.angle)-dt_horizontal*sin(current_pos.angle)
        current_pos.y_pos += dt_center*sin(current_pos.angle)+dt_horizontal*cos(current_pos.angle)
        current_pos.angle += dt_theta

        parallel_1_end = parallel_1_start
        parallel_2_end = parallel_2_start
        perpendicular_1_end = perpendicular_1_start
    }

}