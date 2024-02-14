package com.jedisonknights.lightbulb.drivetrains

import com.arcrobotics.ftclib.kinematics.wpilibkinematics.SwerveDriveOdometry
import com.jedisonknights.lightbulb.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import kotlin.math.cos
import kotlin.math.sin

open class DifferentialSwerveDriveTrain(front_right : DrivePod, front_left : DrivePod, back_right : DrivePod, back_left : DrivePod, odometry_1 : SwerveDriveOdometry, odometry_2 : SwerveDriveOdometry, position : Vector2d) : OpMode() {
    private val front_right = front_right
    private val front_left = front_left
    private val back_right = back_right
    private val back_left = back_left
    private var current_pos : Vector2d = position

    private fun auto_drive_by_inches_straight(inches : Double, power : Double, start_pos : Vector2d) {
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

    private fun auto_drive_by_position(end_pos : Vector2d, start_pos: Vector2d) {

    }

    override fun init() {

    }

    override fun loop() {

    }
}

