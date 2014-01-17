/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class RobotTemplate extends IterativeRobot {

    DriveStation driveStation;
    BallHandler ballHandler;
    
    Joystick joyStick;
    JoystickButton shootButton;
    JoystickButton armButton;
    JoystickButton loadButton;
    JoystickButton passButton;
    JoystickButton catchButton;
    
    
    public void robotInit() {
        
        ballHandler = new BallHandler();
        
        joyStick = new Joystick(1);
        shootButton = new JoystickButton(joyStick,1);
        armButton = new JoystickButton(joyStick,2);
        loadButton = new JoystickButton(joyStick,3);
        passButton = new JoystickButton(joyStick,4);
        catchButton = new JoystickButton(joyStick,5);
        
        shootButton.whenPressed(ballHandler.shootTheBall()); //Zephan broke it.
    }

    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
    }

    public void testPeriodic() {
       
    }
}

