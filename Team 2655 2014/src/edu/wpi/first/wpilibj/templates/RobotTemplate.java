/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joyStick;

    Ultrasonic frontRightRangeFinder;
    Ultrasonic frontLeftRangeFinder;
    
//  Variables for the joystick buttons.
  
    boolean shootingInProgress = false;
    boolean armButtonInProgres = false;
    boolean loadButtonInProgres = false;
    boolean passButtonInProgres = false;
    boolean catchButtonIsInProgres = false;
  
//  Variables for possible button states.
   
    boolean lastShootButtonState = false;
    boolean lastArmButtonState = false;
    public static boolean lastLoadButtonState = false;
    boolean lastPassButtonState = false;
    boolean lastCatchButtonState = false;
    
    boolean pressed = true;
    boolean notPressed = false;

//Variables for possible mode states 
   
    boolean armModeEnabled = true;
    boolean loadModeEnabled = true;
    boolean catchModeEnabled = true;
    
    boolean armModeDisabled = false;
    boolean loadModeDisabled = false;
    boolean catchModeDisabled = false;
    
    //John Mode vs. Josh Mode (Starting to think that John mode might be better... --Josh
    int driveType; //0 means we will not use the gyro in our drive. 1 means the gyro will be in use during robot drive.

    public RobotTemplate() {
        
        joyStick = new Joystick(1);

//        frontRightRangeFinder = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort);
//        frontLeftRangeFinder = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort);

        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);
        driveSystem.run();
        //Test mode won't run if we put it in the autonomous init.
    }

    public void robotInit() {
        //blank for now
        //Replaced by the Constructor
    }

    public void autonomousInit(){
    
    }
    
    public void autonomousPeriodic() {
        frontLeftRangeFinder.setAutomaticMode(true); //enable left sonic sensor
        frontRightRangeFinder.setAutomaticMode(true); //enable right sonic sensor

        while (frontRightRangeFinder.getRangeInches() != frontLeftRangeFinder.getRangeInches()) { //checks if left & right distances are equivalent
            if (frontLeftRangeFinder.getRangeInches() > frontRightRangeFinder.getRangeInches()) { //checks if it's rotated left
                driveSystem.moveAutonomous(0.0, 0.0, 0.5); //this should rotate right at half speed.
            }
            if (frontRightRangeFinder.getRangeInches() > frontLeftRangeFinder.getRangeInches()) { // checks if it's rotated right
                driveSystem.moveAutonomous(0.0, 0.0, -0.5); //this should rotate left at half speed.
            }
        }
        driveSystem.gyro.reset(); // zero the gyro

        while (frontRightRangeFinder.getRangeInches() > 60) {
            driveSystem.moveAutonomous(0.75, 0.0, 0.0); //this should move forward at 75% speed.
        }
        ballHandler.shootTheBall();
        
        while(driveSystem.gyro.getAngle() < 180){ //Turn around to get ready for battle
            driveSystem.moveAutonomous(0.0, 0.0, 0.5); //this should turn right at half speed.
        }
    }

    public void teleopInit() {
        //blank for now
    }

    //Gandalf = 100pts
    public void teleopPeriodic() {
     
        // "Rising Edge" button logic
        //Shoot Button -------------------------------------------------------
        
        
        if (joyStick.getRawButton(1)) { //Is the Button Pressed?
            if (lastShootButtonState == notPressed) { //Was the last state "Not Pressed?"
                ballHandler.shootTheBall();// Do said action
                lastShootButtonState = pressed;//set the last state to "pressed"
            }
            else {// So it ISN'T PRESSED!
                lastShootButtonState = notPressed;//set the last state to "not pressed"
          }       
        }

        //Catch Button -------------------------------------------------------
        if (joyStick.getRawButton(2)) { 
            if (lastCatchButtonState == notPressed) { 
                
                if(catchModeEnabled)
                ballHandler.catchTheBall(); 
                
                
                lastCatchButtonState = pressed; 
            }
        } else {
            lastCatchButtonState = notPressed; 
        }

        //Load Button --------------------------------------------------------
        if (joyStick.getRawButton(3)) {
            if (lastLoadButtonState == notPressed) {
                ballHandler.loadTheBall();
                lastLoadButtonState = pressed;
            }
        } else {
            lastLoadButtonState = notPressed;
        }

        //Arm Button ---------------------------------------------------------
        if (joyStick.getRawButton(4)) {
            if (lastArmButtonState == notPressed) {
                ballHandler.armTheShooter();
                lastArmButtonState = pressed;
            }
        } else {
            lastArmButtonState = notPressed;
        }

        //Poop (Pass) Button -------------------------------------------------
        if (joyStick.getRawButton(5)) {
            if (lastPassButtonState == notPressed) {
                ballHandler.passTheBall();
                lastArmButtonState = pressed;
            }
        } else {
            lastArmButtonState = notPressed;
        }
    }

    public void testPeriodic() {

    }
}
