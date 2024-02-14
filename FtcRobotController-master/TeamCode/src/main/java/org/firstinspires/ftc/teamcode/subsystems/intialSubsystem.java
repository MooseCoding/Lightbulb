package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.jedisonknights.lightbulb.subsystems.SubsystemClass;

public class intialSubsystem extends SubsystemClass {
    private DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");

    public intialSubsystem(int ticks_last, int ticks_refresh_rate) {
        super(ticks_last, ticks_refresh_rate);
    }
}
