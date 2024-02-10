package org.firstinspires.ftc.teamcode;
import com.jedisonknights.lightbulb.SubsystemClass;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.subsystems.intialSubsystem;

@TeleOp
public class Robot extends LinearOpMode {
    private DcMotor br;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor fl;

    private double mult = 1;
    private double turnMult = 0.8;

    private SubsystemClass c = new intialSubsystem(10, 5);
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        br = hardwareMap.get(DcMotor.class, "br");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        fl = hardwareMap.get(DcMotor.class, "fl");

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            // Put run blocks here.
            br.setDirection(DcMotorSimple.Direction.REVERSE);
            bl.setDirection(DcMotorSimple.Direction.FORWARD);

            double y = -gamepad1.left_stick_y * mult; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * mult; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x * turnMult;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            fl.setPower(frontLeftPower);
            bl.setPower(backLeftPower);
            fr.setPower(frontRightPower);
            br.setPower(backRightPower);

            if(gamepad1.left_bumper){
                mult = 1;
                turnMult = 0.8;
            }

            if(gamepad1.right_bumper){
                mult = 0.4;
                turnMult = 0.3;
            }

            telemetry.addData("br", br.getCurrentPosition());
            telemetry.addData("bl", bl.getCurrentPosition());
            telemetry.addData("fr", fr.getCurrentPosition());
            telemetry.addData("fl", fl.getCurrentPosition());

            telemetry.update();
        }
    }
}

