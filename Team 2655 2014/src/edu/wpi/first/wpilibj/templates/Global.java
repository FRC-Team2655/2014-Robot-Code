/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author psiInterceptennettlewis
 */
// MUST CHANGE THE VALUES
public class Global {

//  Variables used in load arm
    public static long loadArmRaiseTime = 1000; // ms
    public static long loadArmExtendTime = 1000; // ms

//  This number is used when the moveTo method calculates the speed that the robot needs to move at.  
    public static double speedSlopeMoving = 0.2;         // psiSlope
    public static double speedSlopeRotate = 0.00555555;  // psiSlope

//  Drive Idle Time
    public static long driveIdleTime = 100;

//  THese are the variable used in shooting
    public static double wantedDistanceFromWall = 10.0; // feet
    public static long stereoRangeFinderSeperation = 23; // inches
    public static long waitTimeShoot = 1000; // ms
    public static long waitTimePass = 1000; // ms
    public static long timeForShooterToRetract = 250; // ms

//  Drive Modes  
    public static int johnMode = 1; // set to 0 to get RC mode

//  Ball handler
    public static long loadIdleTime = 100;// ms

//  drive system 
    public static int distanceLimitPositive = 5;
    public static int distanceLimitNegative = -5;

//  side arms
    public static long sideArmCloseTime = 1000; // ms
    public static long sideArmOpenTime = 1000; // ms
    public static long sideArmIdleTime = 100; // ms

//  Gyro
    public static double kDefaultVoltsPerDegreePerSecond = .007;// gyro sensitivity

//  Temperature slope psiSlope = (9mV/C)
//  y = mx + psiIntercept
    public final static double temperatureSlopeCompensation = 111.1111; // psiSlope
    public final static double temperatureOffset = (-252.7777);         // psiIntercept

//  RangeFinder
//  public static double voltagePerInch = 512;
    public final static double convertMultiplier = 104.16; // psiSlope

//  Joystick buttons
    public final static int shootButton = 1;
    public final static int loadButton = 3;
    public final static int anchorButton = 4;
    public final static int poopButton = 5;

    // ball in mitt constants
    public final static int wantedBallDistance = 10; // inches

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

    // current sensor constants
    public final static double mvPerAmp = 14.705; // 1 / 68mv/A
    public final static double mvPerAmpIntercept = 0; // b

//  Smart Dashboard 
    public static void smartDashBoardGlobalVariables() {

    }
}
