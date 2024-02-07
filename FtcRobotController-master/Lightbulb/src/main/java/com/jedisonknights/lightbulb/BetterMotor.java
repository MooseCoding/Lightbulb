package com.jedisonknights.lightbulb;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;



import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import java.util.jar.Manifest;

public class BetterMotor implements HardwareDevice {
    public DcMotor motor;
    public MOTOR_TYPE motor_type;
    public double MAX_TPS;

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.ModernRobotics;
    }

    @Override
    public String getDeviceName() {
        return "Motor " + motor.getDeviceName() + " from " + motor.getManufacturer() +
                " in port" + motor.getPortNumber();
    }

    @Override
    public String getConnectionInfo() {
        return motor.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return motor.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        motor.resetDeviceConfigurationForOpMode();
    }


    @Override
    public void close() {
        motor.close();
    }

    enum Runmode {
        PIDF_LIMIT,
        PIDF,
        Encoder,
        Power
    }
    public enum MOTOR_TYPE { //credit to FTCLibs for the GoBilda data, REV data is still being tested :)
        RPM_30(5264, 30), RPM_43(3892, 43), RPM_60(2786, 60), RPM_84(1993.6, 84),
        RPM_117(1425.2, 117), RPM_223(753.2, 223), RPM_312(537.6, 312), RPM_435(383.6, 435),
        RPM_1150(145.6, 1150), RPM_1620(103.6, 1620), BARE(28, 6000), NONE(0, 0),
        CORE_HEX_MOTOR(1296, 125), HD_HEX_MOTOR(7, 6000), HD_HEX_MOTOR_40(2800, 150), HD_HEX_MOTOR_20(700, 300);
        private double ppr, rpm;

        MOTOR_TYPE(double ppr, double rpm) {
            this.ppr = ppr;
            this.rpm = rpm;
        }

        public double PPR() {
            return ppr;
        }

        public double RPM() {
            return rpm;
        }

        public double MaxTicks() {
            return ppr * rpm / 60;
        }
    }

    public BetterMotor(HardwareMap hardwareMap, String motor_id, MOTOR_TYPE motor_type) {
        motor = hardwareMap.get(DcMotor.class, motor_id);
        this.motor_type = motor_type;
        MAX_TPS = motor_type.MaxTicks();
    }

    public BetterEncoder {
        private Supplier<Integer> motor_pos;

    }

    public void setPIDFLimits(double MIN_POS, double MAX_POS, double AMPS) {
        this.motor.setMode(BetterRunmodes.PIDF_LIMITER(MIN_POS, MAX_POS, AMPS));
    }

}
