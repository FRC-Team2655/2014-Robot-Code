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
public class GlobalVariables {
//  This number is used when the moveTo method calculates the speed that the robot needs to move at.  
    public static double speedSlopeMoving = 0.2;
    public static double speedSlopeRotate = 0.00555555;
    
    public static double wantedDistanceFromWall = 10.0;
    public static int stereoRangeFinderSeperation = 23; // 
    public static int waitTimeShoot = 1000;
    public static int waitTimePass = 100;
    public static int timeForShooterToRetract = 250;

//  Drive Modes  
    public static int johnMode = 1;
    public static int rcMode = 2;
    
//anchor raise and drop times
     public static int anchorDropTime = 600;
    public static int anchorRaiseTime = 600;

// drive system 
    public static int distanceLimitPositive = 5;
    public static int distanceLimitNegative = -5;
    
// smart dash board 
    protected void smartDashBoardVariables() {
        SmartDashboard.putNumber("speedSlopeMoving" , speedSlopeMoving);
        SmartDashboard.putNumber("speedSlopeRotate", speedSlopeRotate );
        SmartDashboard.putNumber("wantedDistanceFromWall", wantedDistanceFromWall);
        SmartDashboard.putNumber("stereoRangeFinderSeperation", stereoRangeFinderSeperation);
        SmartDashboard.putNumber("distanceCapPositive", distanceLimitPositive);
        SmartDashboard.putNumber("distanceCapNegative", distanceLimitNegative);
        SmartDashboard.putNumber("waitTimeShoot", waitTimeShoot);
        SmartDashboard.putNumber("waitTimePass", waitTimePass);
        SmartDashboard.putNumber("timeForShooterToRetract", timeForShooterToRetract);
        SmartDashboard.putNumber("anchorDropTime", anchorDropTime);
        SmartDashboard.putNumber("anchorRaiseTime", anchorRaiseTime);
    }
}
