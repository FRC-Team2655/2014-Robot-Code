/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author bennettlewis
 */
// MUST CHANGE THE VALUES
public class Global {
//  Variables used in load arm
    public static long loadArmRaiseTime = 100; // ms
    public static long loadArmExtendTime = 100; // ms
    
//  This number is used when the moveTo method calculates the speed that the robot needs to move at.  
    public static double speedSlopeMoving = 0.2;         // m
    public static double speedSlopeRotate = 0.00555555;  // m

//  THese are the variable used in shooting
    public static double wantedDistanceFromWall = 10.0; // feet
    public static long stereoRangeFinderSeperation = 23; // inches
    public static long waitTimeShoot = 100; // ms, must be larter than waitTimePass
    public static long waitTimePass = 100; // ms
    public static long timeForShooterToRetract = 100; // ms

//  Drive Modes  
    public static int johnMode = 1; // set to 0 to get RC mode

//  Ball handler
    public static long loadIdleTime = 100;// ms
    public static long catchIdleTime = 100; // ms

//  anchor raise and drop times
    public static long anchorDropTime = 100; // ms
    public static long anchorRaiseTime = 100; // ms

//  drive system 
    public static int distanceLimitPositive = 5;
    public static int distanceLimitNegative = -5;

//  side arms
    public static long sideArmCloseTime = 100; // ms
    public static long sideArmOpenTime = 100; // ms
    public static long sideArmIdleTime = 100; // ms

//  Gyro
    public static double kDefaultVoltsPerDegreePerSecond = .007;// gyro sensitivity

//  Temperature slope m = (9mV/C)
//  y = mx + b
    public static double temperatureSlopeCompensation = 111.1111; // m
    public static double temperatureOffset = (-252.7777);         // b

//  RangeFinder
//  public static double voltagePerInch = 512;
    public static double convertMultiplier = 104.16; // m

//  Joystick buttons
    public static int shootButton = 1;
    public static int anchorButton = 3;
    public static int loadButton = 4;
    public static int poopButton = 5;
    
    public final static int wantedBallDistance = 15; // inches
    public static double measuredTimeInAccelerationMeasurement; // micro seconds
    public static double k_timeInAccelerationMeasurement = 10; // micro seconds
    public static double massOfBall; // units ?
    
//  Smart Dashboard 
    public static void smartDashBoardGlobalVariables() {
        SmartDashboard.putNumber("speed Slope Moving", speedSlopeMoving);
        SmartDashboard.putNumber("speed Slope Rotate", speedSlopeRotate);
        SmartDashboard.putNumber("wanted Distance From Wall", wantedDistanceFromWall);
        SmartDashboard.putNumber("stereo Range Finder Seperation", stereoRangeFinderSeperation);
        SmartDashboard.putNumber("distance Cap Positive", distanceLimitPositive);
        SmartDashboard.putNumber("distance Cap Negative", distanceLimitNegative);
        SmartDashboard.putNumber("wait Time Shoot", waitTimeShoot);
        SmartDashboard.putNumber("wait Time Pass", waitTimePass);
        SmartDashboard.putNumber("time For Shooter To Retract", timeForShooterToRetract);
        SmartDashboard.putNumber("anchor Drop Time", anchorDropTime);
        SmartDashboard.putNumber("anchor Raise Time", anchorRaiseTime);
        SmartDashboard.putNumber("side Arm Close Time", sideArmCloseTime);
        SmartDashboard.putNumber("side Arm Open Time", sideArmOpenTime);
        SmartDashboard.putNumber("side Arm Idle Time", sideArmIdleTime);
        SmartDashboard.putNumber("gyro Sensitivity", kDefaultVoltsPerDegreePerSecond);
        SmartDashboard.putNumber("range Finder Convert multiplier", convertMultiplier);
        SmartDashboard.putNumber("John mode toggle", johnMode);
        SmartDashboard.putNumber("load Idle Time", loadIdleTime);
        SmartDashboard.putNumber("temperature Slope Compensation", temperatureSlopeCompensation);
        SmartDashboard.putNumber("temperature Offset", temperatureOffset);
        SmartDashboard.putNumber("Time in measureAcceleration(): ", measuredTimeInAccelerationMeasurement);
        //SmartDashboard.putNumber("k time in acceleration measurement",k_timeInAccelerationMeasurement)
    }
}
