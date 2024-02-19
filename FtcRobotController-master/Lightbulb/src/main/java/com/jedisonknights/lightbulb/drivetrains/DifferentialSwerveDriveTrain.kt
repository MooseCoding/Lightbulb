package com.jedisonknights.lightbulb.drivetrains

import com.jedisonknights.lightbulb.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Gamepad
import kotlin.math.pow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

open class DifferentialSwerveDriveTrain(front_right : DrivePod, front_left : DrivePod, back_right : DrivePod, back_left : DrivePod, position : Vector2d, gamepad: Gamepad) : OpMode() {
    private val front_right = front_right
    private val front_left = front_left
    private val back_right = back_right
    private val back_left = back_left
    private var current_pos : Vector2d = position

    fun auto_drive_by_inches_straight(inches : Double, power : Double, start_pos : Vector2d) {
        val target : Vector2d = Vector2d(cos(current_pos.angle) * inches, sin(current_pos.angle) * inches, current_pos.angle)
        if (inches < 0) {
            while (!current_pos.vector_equals(target)) {
                front_right.run(power)
                front_left.run(power)
                back_right.run(power)
                back_left.run(power)
            }
        }

        else {
            while (!current_pos.vector_equals(target)) {
                front_right.run(-power)
                front_left.run(-power)
                back_right.run(-power)
                back_left.run(-power)
            }
        }
    }

    fun spline_to(end_pos : Vector2d, start_pos: Vector2d) {
        //change the formula, right now its finding the radius of a circle and going along the circumference
        val diameter = sqrt((end_pos.x_pos - start_pos.x_pos).pow(2.0) + (end_pos.y_pos - start_pos.y_pos).pow(2.0))
        val circumference = PI*diameter

    }

    override fun init() {

    }

    override fun loop() {

    }
}

