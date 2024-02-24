package com.jedisonknights.lightbulb.work_in_progrses;
import com.jedisonknights.lightbulb.drivetrains.HardwareDevice;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BetterMotor implements HardwareDevice {
    public DcMotor motor;
    public MOTOR_TYPE motor_type;
    public double MAX_TPS;

    public boolean get_activity = false;

    public BetterEncoder encoder;

    public int ENCODER_TARGET;

    private Motor_Controller controller;

    private RUNMODE current_runmode = RUNMODE.Power;

    public double last_time_talked;
    @Override
    public void disable() {
        motor.close();
    }

    @Override
    public String getDeviceType() {
        return "Motor " + motor_type;
    }

    public enum MOTOR_TYPE { //credit to FTCLibs for the GoBilda data, REV data is still being tested :)
        RPM_30(5264, 30), RPM_43(3892, 43), RPM_60(2786, 60), RPM_84(1993.6, 84),
        RPM_117(1425.2, 117), RPM_223(753.2, 223), RPM_312(537.6, 312), RPM_435(383.6, 435),
        RPM_1150(145.6, 1150), RPM_1620(103.6, 1620), BARE(28, 6000), NONE(0, 0),
        CORE_HEX_MOTOR(1296, 125), HD_HEX_MOTOR(7, 6000), HD_HEX_MOTOR_40(2800, 150), HD_HEX_MOTOR_20(700, 300),
        ODOMETRY_WHEEL(0,0);
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

    public BetterMotor(HardwareMap hardwareMap, String motor_id, MOTOR_TYPE motor_type, boolean use_encoder) {
        motor = hardwareMap.get(DcMotor.class, motor_id);
        this.motor_type = motor_type;
        MAX_TPS = motor_type.MaxTicks();
        if (use_encoder) {
            this.encoder = new BetterEncoder();
        }
        this.current_runmode = RUNMODE.Power;
    }

    public class BetterEncoder {
        private int updated_pos;
        private int cleared_pos, pos;
        private double velocity, acceleration, last_velocity, last_time_talked, distance_per_pulse;
        private int direction;


        public BetterEncoder() {
            this.distance_per_pulse = 1;
            this.cleared_pos = 0;
            this.velocity = 0;
            this.pos = 0;
            last_time_talked = System.nanoTime()/1000000000.0;
            direction = 1;
        }



        public double getDistance() {
            return distance_per_pulse * getPos();
        }

        public void GET_CURRENT_POS_FROM_CONTROL_HUB() {
            //to be set
        }


        public void setDistance_per_pulse(double distance) {
            distance_per_pulse = distance;
        }

        public double getRevolutions() {
            return getPos()/ motor_type.PPR();
        }

        public double getAcceleration() {
            updateAcceleration();
            return acceleration;
        }

        public double getVelocity() {
            updateVelocity();
            return velocity;
        }

        public int getPos() {
            updatePos();
            return direction * pos - cleared_pos;
        }

        public void isReversed(boolean yes) {
            if (yes) {
                direction = -1;
            }
        }

        public void updatePos() {
            GET_CURRENT_POS_FROM_CONTROL_HUB();
            if (updated_pos == pos) {
                pos = updated_pos;
                velocity = (updated_pos - pos) / (System.nanoTime() / 1000000000.0 - last_time_talked);
                last_time_talked = System.nanoTime() / 1000000000.0;
            }
        }

        public void updateVelocity() {
            last_velocity = velocity;
            updatePos();
        }

        public void updateAcceleration() {
            updateVelocity();
            acceleration = (velocity-last_velocity)/(System.nanoTime() / 1000000000.0 - last_time_talked);
        }

    }

    public void run(double power) {
        controller.run(power);
        get_time();
    }

    public void setEncoder(int target) {
        ENCODER_TARGET = (int)(target * encoder.distance_per_pulse);
    }

    public void setPIDFLimits(double MIN_POS, double MAX_POS, double AMPS) {

    }

    public Double get_time() {
        return System.nanoTime() / 1000000000.0;
    }

    public void setCurrent_runmode(RUNMODE runmode) {
        this.current_runmode = runmode;
    }


    public RUNMODE getCurrent_runmode() {
        get_time();
        return current_runmode;
    }

}
