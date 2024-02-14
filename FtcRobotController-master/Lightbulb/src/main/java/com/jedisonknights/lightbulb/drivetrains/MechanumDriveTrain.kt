package com.jedisonknights.lightbulb.drivetrains

import com.jedisonknights.lightbulb.work_in_progrses.BetterMotor
import kotlin.math.abs
import kotlin.math.max

open class MechnaumDriveTrain(front_left : BetterMotor, front_right : BetterMotor, back_left : BetterMotor, back_right : BetterMotor) {
    private val front_left = front_left
    private val front_right = front_right
    private val back_left = back_left
    private val back_right = back_right
    private var mult : Double = 0.0
    fun run_drivetrain(left_stick_x : Double, left_stick_y : Double, right_stick_x : Double){
        var leftFrontPower: Double = left_stick_x + left_stick_y + right_stick_x
        var rightFrontPower: Double = left_stick_x - left_stick_y - right_stick_x
        var leftBackPower: Double = left_stick_x - left_stick_y + right_stick_x
        var rightBackPower: Double = left_stick_x + left_stick_y - right_stick_x


        var max : Double = abs(leftFrontPower).coerceAtLeast(abs(rightFrontPower))
        max = max(max, abs(leftBackPower))
        max = max(max, abs(rightBackPower))

        if (max > 1.0) {
            leftFrontPower /= max
            rightFrontPower /= max
            leftBackPower /= max
            rightBackPower /= max
        }

        front_left.run(leftFrontPower * mult)
        back_left.run(leftBackPower * mult)
        front_right.run(rightFrontPower * mult)
        back_right.run(rightBackPower * mult)
    }

    fun set_multiplier(multi : Double) {
        mult = multi
    }
}