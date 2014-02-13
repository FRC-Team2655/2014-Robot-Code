/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author magic_000
 */
public final class Ports {

    // PWM channels
    public final static int frontLeftMotorChannel = Ports.DSCPWM.ch2;
    public final static int backLeftMotorChannel = Ports.DSCPWM.ch4;
    public final static int frontRightMotorChannel = Ports.DSCPWM.ch1;
    public final static int backRightMotorChannel = Ports.DSCPWM.ch3;
//    public final static int unusedPWMChannel_4 = Ports.DSCPWM.ch4;
//    public final static int unusedPWMChannel_5 = Ports.DSCPWM.ch5;
//    public final static int unusedPWMChannel_6 = Ports.DSCPWM.ch6;
//    public final static int unusedPWMChannel_7 = Ports.DSCPWM.ch7;
//    public final static int unusedPWMChannel_8 = Ports.DSCPWM.ch8;
//    public final static int unusedPWMChannel_9 = Ports.DSCPWM.ch9;
//    public final static int unusedPWMChannel_10 = Ports.DSCPWM.ch10;

    // Digital SideCar DIO
    public final static int shooterRotationAChannel = Ports.DSCDIO.ch1;
    public final static int shooterRotationBChannel = Ports.DSCDIO.ch2;
    public final static int frontLeftMotorRotationAChannel = Ports.DSCDIO.ch3;
    public final static int frontLeftMotorRotationBChannel = Ports.DSCDIO.ch4;
    public final static int frontRightMotorRotationAChannel = Ports.DSCDIO.ch5;
    public final static int frontRightMotorRotationBChannel = Ports.DSCDIO.ch6;
    public final static int backLeftMotorRotationAChannel = Ports.DSCDIO.ch7;
    public final static int backLeftMotorRotationBChannel = Ports.DSCDIO.ch8;
    public final static int backRightMotorRotationAChannel = Ports.DSCDIO.ch9;
    public final static int backRightMotorRotationBChannel = Ports.DSCDIO.ch10;
    public final static int ballInMittChannel = Ports.DSCDIO.ch11;
    public final static int pressureSwitchChannel = Ports.DSCDIO.ch12;
//    public final static int unusedDIOChannel_13 = Ports.DSCDIO.ch13;
//    public final static int unusedDIOChannel_14 = Ports.DSCDIO.ch14;

    // Digital SideCar Relays
    public final static int infeedArmMotorControlChannel = Ports.DSCRelay.ch2;
    public final static int compressorRelayChannel = Ports.DSCRelay.ch1;
    public final static int loadArmExtendChannel = Ports.DSCRelay.ch3;
    public final static int loadArmRetractChannel = Ports.DSCRelay.ch4;
//    public final static int unusedRelayChannel_5 = Ports.DSCRelay.ch5;
//    public final static int unusedRelayChannel_6 = Ports.DSCRelay.ch6;
//    public final static int unusedRelayChannel_7 = Ports.DSCRelay.ch7;
//    public final static int unusedRelayChannel_8 = Ports.DSCRelay.ch8;

    // solenoid module 1
    public final static int leftShooterExtendChannel = Ports.SM.ch1;
    public final static int leftShooterRetractChannel = Ports.SM.ch2;
    public final static int rightShooterExtendChannel = Ports.SM.ch3;
    public final static int rightShooterRetractChannel = Ports.SM.ch4;
    public final static int sideArmOpenArmChannel = Ports.SM.ch5;
    public final static int sideArmClosedArmChannel = Ports.SM.ch6;
    public final static int anchorDropChannel = Ports.SM.ch7;
    public final static int anchorRaiseChannel = Ports.SM.ch8;

    // analog module 1
    public final static int gyroChannel = Ports.AM.ch1;
    public final static int rangeFinderChannel = Ports.AM.ch2;
    public final static int airTankPressureSensor = Ports.AM.ch3;
    public final static int infeedBallDetectChannel = Ports.AM.ch4;
    public final static int ballInMittAChannel = Ports.AM.ch5;
//    public final static int unusedAMChannel_6 = Ports.AM.ch6;
//    public final static int unusedAMChannel_7 = Ports.AM.ch7;
    public final static int temperatureChannel = Ports.AM.ch8;

    // Digital SideCar Relay channels
    public final static class DSCRelay {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }

    // Digital SideCar PWM channels
    public final static class DSCPWM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
        public final static int ch9 = 9, ch10 = 10;
    }

    // Digital SideCar DIO channels
    public final static class DSCDIO {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
        public final static int ch9 = 9, ch10 = 10, ch11 = 11, ch12 = 12;
        public final static int ch13 = 13, ch14 = 14;
    }

    // Solenoid Module channels
    public final static class SM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }

    // Analog Module channels
    public final static class AM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }
}
