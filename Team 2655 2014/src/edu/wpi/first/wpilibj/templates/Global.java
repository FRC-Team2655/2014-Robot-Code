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
    public static long loadArmRaiseTime = 100;
    
//  This number is used when the moveTo method calculates the speed that the robot needs to move at.  
    public static double speedSlopeMoving = 0.2;         // m
    public static double speedSlopeRotate = 0.00555555;  // m

//  THese are the variable used in shooting
    public static double wantedDistanceFromWall = 10.0;
    public static int stereoRangeFinderSeperation = 23;
    public static int waitTimeShoot = 1000;
    public static int waitTimePass = 100;
    public static int timeForShooterToRetract = 250;

//  Drive Modes  
    public static int johnMode = 1; // set to 0 to get RC mode

//  Ball handler
    public static int loadIdleTime = 100;
    public static int catchIdleTime = 100;

//  anchor raise and drop times
    public static int anchorDropTime = 600;
    public static int anchorRaiseTime = 600;

//  drive system 
    public static int distanceLimitPositive = 5;
    public static int distanceLimitNegative = -5;

//  side arms
    public static int sideArmCloseTime = 100;
    public static int sideArmOpenTime = 100;
    public static int sideArmIdleTime = 100;

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
    }
}
