/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class RobotTemplate extends IterativeRobot {

    RobotDrive drive;
    
    DriveSystem driveSystem;
    BallHandler ballHandler;
    
    Joystick joyStick;
    JoystickButton shootButton;
    JoystickButton armButton;
    JoystickButton loadButton;
    JoystickButton passButton;
    JoystickButton catchButton;
    
    
    public void robotInit() {
        
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);
        
        joyStick = new Joystick(1);
        
        // WE DON'T NEED THIS STUFF. IT'S FOR A DIFFERENT PROGRAM STRUCTURE. --Josh
//        shootButton = new JoystickButton(joyStick,1);
//        armButton = new JoystickButton(joyStick,2);
//        loadButton = new JoystickButton(joyStick,3);
//        passButton = new JoystickButton(joyStick,4);
//        catchButton = new JoystickButton(joyStick,5);
//        
//        shootButton.whenPressed();
        
        
        
        //shootButton.whenPressed(ballHandler.shootTheBall()); //Zephan broke it.
    }

    
    public void autonomousPeriodic() {
       //drive Foward...
        ballHandler.shootTheBall();
    }

    public void teleopPeriodic() {
        driveSystem.run(); //Runs Drivetrain -- Do we need to pass the joystick here???
        if(joyStick.getRawButton(1))
            ballHandler.shootTheBall();
        if(joyStick.getRawButton(2))
            ballHandler.catchTheBall();
        if(joyStick.getRawButton(3))
            ballHandler.loadTheBall();
        if(joyStick.getRawButton(4))
            ballHandler.armTheShooter();
        if(joyStick.getRawButton(5))
            ballHandler.passTheBall();
            
            
        
        
        
        
        
    }

    public void testPeriodic() {
       
    }
}

