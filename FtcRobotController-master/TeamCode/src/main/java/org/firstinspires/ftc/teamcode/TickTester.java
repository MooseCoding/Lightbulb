package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TickTester extends LinearOpMode {
    private int ticks = 0;
    private double time_start = 0;
    private double time_end = 0;

    private double time_elapsed = 0;
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            time_elapsed += getRuntime();
            time_start = getRuntime();
            ticks++;
            time_end = getRuntime();
            telemetry.addData("ticks", ticks);
            telemetry.addData("time_start", time_start);
            telemetry.addData("time_end", time_end);
            telemetry.addData("time_elapsed", time_elapsed);
            telemetry.addData("tps", ticks/time_elapsed);

            telemetry.update();
        }
    }
}

