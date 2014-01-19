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
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joyStick;
    
    Ultrasonic frontRightRangeFinder;
    Ultrasonic frontLeftRangeFinder;

    boolean shootingInProgress = false; 
    boolean armButtonInProgres = false;
    boolean loadButtonInProgres = false;
    boolean passButtonInProgres = false;
    boolean catchButtonIsInProgres = false;
    
    boolean lastShootButtonState = false;
    boolean lastArmButtonState = false;
    boolean lastLoadButtonState = false;
    boolean lastPassButtonState = false;
    boolean lastCatchButtonState = false;
    boolean pressed = true;
    boolean notPressed = false;

    int driveType; //0 means we will not use the gyro in our drive. 1 means the gyro will be in use during robot drive.

    public RobotTemplate() {
        joyStick = new Joystick(1);
        
        frontRightRangeFinder = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort);
        frontLeftRangeFinder = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort);
        
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);
        
        driveSystem.run();
    }

    public void robotInit() {
        //blank for now
        //Replaced by the Constructor
    }

    public void autonomousPeriodic() {
        frontLeftRangeFinder.setAutomaticMode(true);
        frontRightRangeFinder.setAutomaticMode(true);
        
        while(frontRightRangeFinder.getRangeInches() != frontLeftRangeFinder.getRangeInches())
        {
            if(frontLeftRangeFinder.getRangeInches() > frontRightRangeFinder.getRangeInches()){
                //TODO rotate right
            }
            if(frontRightRangeFinder.getRangeInches() > frontLeftRangeFinder.getRangeInches()){
                //TODO rotate left  
            }
        }
        driveSystem.gyro.reset();
        
        while(frontRightRangeFinder.getRangeInches() > 60){
            //move forward
        }
        ballHandler.shootTheBall();
    }

    public void teleopInit() {
        //blank for now
    }

    //Gandalf = 100pts
    public void teleopPeriodic() {
        
        // "Rising Edge" button logic
          //Shoot Button
          if (joyStick.getRawButton(1)){ //Shoot button
              if(lastShootButtonState == notPressed){
                  ballHandler.shootTheBall();
                  lastShootButtonState = pressed;
              }
          }else{
              lastShootButtonState = notPressed;
          }
 
          //Catch Button
          if(joyStick.getRawButton(2)){ 
              if(lastCatchButtonState == notPressed){
                  ballHandler.catchTheBall();
                  lastCatchButtonState = pressed;
              }
          }else{
           lastCatchButtonState = notPressed;   
          }
          
          //Load Button
          if(joyStick.getRawButton(3)){
              if(lastLoadButtonState == notPressed){
                  ballHandler.loadTheBall();
                  lastLoadButtonState = pressed;
              }
          }else{
           lastLoadButtonState = notPressed;   
          }
          
          //Arm Button
          if(joyStick.getRawButton(4)){
              if(lastArmButtonState == notPressed){
                  ballHandler.armTheShooter();
                  lastArmButtonState = pressed;
              }
          }else{
           lastArmButtonState = notPressed;   
          }
          
          //Poop (Pass) Button
          if(joyStick.getRawButton(5)){
              if(lastPassButtonState == notPressed){
                  ballHandler.passTheBall();
                  lastArmButtonState = pressed;
              }
          }else{
           lastArmButtonState = notPressed;   
          }
    }

    public void testPeriodic() {

    }
}
