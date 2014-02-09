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
    public final static int frontLeftMotorChannel = Ports.DSCPWM.ch1;
    public final static int backLeftMotorChannel = Ports.DSCPWM.ch2;
    public final static int frontRightMotorChannel = Ports.DSCPWM.ch3;
    public final static int backRightMotorChannel = Ports.DSCPWM.ch4;

    // DIO
    public final static int ballInMittLimitSwitchChannel = Ports.DSCDIO.ch1;

    // Relays
    public final static int infeedArmMotorControlChannel = Ports.DSCRelay.ch1;
    public final static int compressorRelayChannel = Ports.DSCRelay.ch2;
    public final static int loadArmExtendChannel = Ports.DSCRelay.ch3;
    public final static int loadArmRetractChannel = Ports.DSCRelay.ch4;

    // solenoids
    public final static int leftShooterExtendChannel = Ports.SM.ch1;
    public final static int leftShooterRetractChannel = Ports.SM.ch2;
    public final static int rightShooterExtendChannel = Ports.SM.ch3;
    public final static int rightShooterRetractChannel = Ports.SM.ch4;
    public final static int sideArmOpenArmChannel = Ports.SM.ch5;
    public final static int sideArmClosedArmChannel = Ports.SM.ch6;
    public final static int anchorDropChannel = Ports.SM.ch7;
    public final static int anchorRaiseChannel = Ports.SM.ch8;

    // analog
    public final static int gyroChannel = Ports.AM.ch1;
    public final static int rangeFinderChannel = Ports.AM.ch2;
    public final static int BallInMittChannel = Ports.AM.ch3;
    public final static int pressureSwitchChannel = Ports.AM.ch4;
    // ch5-ch7 not used right now
    public final static int temperatureChannel = Ports.AM.ch8;

    
    // part of Digital Side Car
    public final static class DSCRelay {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }

    // part of Digital Side Car
    public final static class DSCPWM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
        public final static int ch9 = 9, ch10 = 10;
    }

    // part of Digital Side Car
    public final static class DSCDIO {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
        public final static int ch9 = 9, ch10 = 10, ch11 = 11, ch12 = 12;
        public final static int ch13 = 13, ch14 = 14;
    }

    // solenoid module channels
    public final static class SM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }

    // analog module channels
    public final static class AM {

        public final static int ch1 = 1, ch2 = 2, ch3 = 3, ch4 = 4;
        public final static int ch5 = 5, ch6 = 6, ch7 = 7, ch8 = 8;
    }
}
