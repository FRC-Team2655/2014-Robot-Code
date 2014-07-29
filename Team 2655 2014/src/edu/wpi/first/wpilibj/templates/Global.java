/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author psiInterceptennettlewis
 */
// MUST CHANGE THE VALUES
public class Global {

//  Variables used in load arm
    public final static long inFeedRaiseTime = 100; // ms
    public final static long inFeedLowerTime = 100; // ms
    public final static long inFeedPartialLowerTime = 500; // ms

//  This number is used when the moveTo method calculates the speed that the robot needs to move at.  
    public final static double speedSlopeMoving = 0.1;         // psiSlope
    public final static double speedSlopeRotate = 0.00555555;  // psiSlope

//  Drive Idle Time
    public final static long driveIdleTime = 100;

//  Variables used for anchors
    public final static long anchorRaiseTime = 250;
    public final static long anchorDropTime = 100; // ms

//  THese are the variable used in shooting
    public final static double wantedDistanceFromWall = 4; // feet
    public final static long stereoRangeFinderSeperation = 23; // inches
    public final static long shooterShootTime = 500; // ms
    public final static long shooterPassTime = 200; // ms
    public final static long waitTimeCharge = 1000;
    public final static long shooterRetractTime = 1500; // ms
    public final static long settleTime = 250;

//  Drive Modes  
    public static int johnMode = 1; // set to 0 to get RC mode

//  Ball handler
    public final static long loadIdleTime = 50;// ms

//  drive system 
    public final static int distanceLimitPositive = 5;
    public final static int distanceLimitNegative = -5;

//  side arms
    public final static long sideArmCloseTime = 100; // ms
    public final static long sideArmOpenTime = 100; // ms
    public final static long sideArmPartialOpenTime = 300; // ms
    public final static long sideArmIdleTime = 10; // ms

//  Gyro
    public final static double kDefaultVoltsPerDegreePerSecond = .007;// gyro sensitivity

//  Temperature slope psiSlope = (9mV/C)
//  y = mx + psiIntercept
    public final static double temperatureSlopeCompensation = 111.1111; // psiSlope
    public final static double temperatureOffset = (-252.7777);         // psiIntercept 

//  RangeFinder
//  public static double voltagePerInch = 512;
    public final static double convertMultiplier = 104.16; // psiSlope

//  Joystick buttons
    public final static int anchorButton = 4;
    public final static int calibrateGyroButton = 7;
    public final static int driveModeButton = 8;

//  Xbox Buttons
    public final static int sideArmsButton = 2;
    public final static int loadButton = 3;
    public final static int poopButton = 5;
    public final static int shootButton = 6;

    // ball in mitt constants
    public final static int wantedBallDistanceInches = 10;

    // ball mass calculation constants
    public static double measuredTimeInAccelerationMeasurement; // micro seconds
    public final static double k_timeInAccelerationMeasurement = 10; // micro seconds
    public static double massOfBall; // units ?

    // encoder constants (assuming ALL the encoders are the same
    public final static int pulsesPerRotation = 250; // unitless

    // shooter constants
    public final static boolean reverseShooterRotation = false;
    public final static double shooterArmRadius = 8; // in -- just a guess
    public final static double shooterCircumference = 2 * Math.PI * shooterArmRadius; // in
    public final static double shooterRadiansPerPulse = 2 * Math.PI / pulsesPerRotation;

    // wheel encoder constants
    public final static double wheelRadius = 3; // in
    public final static double wheelCircumference = 2 * Math.PI * wheelRadius; // in
    public final static double wheelDistancePerPulse = wheelCircumference / pulsesPerRotation;

    // compressor constants
    public final static double psiSlope = 43.5;
    public final static double psiIntercept = (-21.75); // b
    public final static double wantedMinimumPSI = 115;

    // current sensor constants
    public final static double mvPerAmp = 14.705; // 1 / 68mv/A
    public final static double mvPerAmpIntercept = 0; // b

//  Smart Dashboard 
//    public static void smartDashBoardGlobalVariables() {
//
//    }
}
