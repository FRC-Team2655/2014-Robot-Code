/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

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
    public static int anchorDropTime = 600;
}
