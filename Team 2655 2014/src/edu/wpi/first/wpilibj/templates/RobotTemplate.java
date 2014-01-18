/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
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
    
    boolean shootButtonIsPressed = joyStick.getRawButton(1);
    boolean armButtonIsPressed = joyStick.getRawButton(2);
    boolean loadButtonIsPressed = joyStick.getRawButton(3);
    boolean passButtonIsPressed = joyStick.getRawButton(4);
    boolean catchButtonIsPressed = joyStick.getRawButton(5);

    int driveType; //0 means we will not use the gyro in our drive. 1 means the gyro will be in use during robot drive.

    public RobotTemplate() {

        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);

        joyStick = new Joystick(1);

        driveSystem.run();//Runs Drivetrain -- Do we need to pass the joystick here???
    }

    public void robotInit() {
        //blank for now
    }

    public void autonomousPeriodic() {
        
        ballHandler.shootTheBall();
    }

    public void teleopInit() {
        //blank for now
    }

    public void teleopPeriodic() {
        if (shootButtonIsPressed)
            ballHandler.shootTheBall();
        else if (armButtonIsPressed) 
            ballHandler.catchTheBall();
        else if (loadButtonIsPressed) 
            ballHandler.loadTheBall();
        else if (passButtonIsPressed) 
            ballHandler.armTheShooter();
        else if (catchButtonIsPressed) 
            ballHandler.passTheBall();
    }

    public void testPeriodic() {

    }
}
