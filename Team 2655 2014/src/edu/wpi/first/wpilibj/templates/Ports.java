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
public class Ports {
//  Hardware ports are incorrect and/or temporary.

    public static int crioSlot1 = 1;
    public static int crioSlot2 = 2;
    public static int crioSlot3 = 3;
    public static int crioSlot4 = 4;

    public static class DigitalModule {

        public static int frontLeftMotorChannel = 1;
        public static int backLeftMotorChannel = 2;
        public static int frontRightMotorChannel = 3;
        public static int backRightMotorChannel = 4;
        public static int ballInMittLimitSwitchChannel = 5;
        public static int infeedArmMotorControlChannel = 6;
        public static int compressorRelayChannel = 7;
        public static int shooterLimiterSwitchChannel = 8;

    }

    public static class SolenoidModule {

        public static int anchorDropChannel = 1;
        public static int anchorRaiseChannel = 2;
        public static int shooterExtendChannel = 3;
        public static int shooterRetractChannel = 4;
        public static int sideArmOpenArmChannel = 5;
        public static int sideArmClosedArmChannel = 6;

    }

    public static class AnalogModule {

        public static int gyroChannel = 1;
        public static int leftRangeFinderPort = 2;
        public static int rightRangeFinderPort = 3;
        public static int pressureSwitchChannel = 4;

    }

}
