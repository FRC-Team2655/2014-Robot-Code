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
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joyStick;
    JoystickButton shootButton;
    JoystickButton armButton;
    JoystickButton loadButton;
    JoystickButton passButton;
    JoystickButton catchButton;

    Ultrasonic rangeFinderSensor;

    int driveType; //0 means we will not use the gyro in our drive. 1 means the gyro will be in use during robot drive.

    public RobotTemplate() {
        joyStick = new Joystick(1);
        rangeFinderSensor = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort);
        
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);
        
        driveSystem.run();
    }

    public void robotInit() {
        //blank for now
    }

    public void autonomousPeriodic() {
        //go farward
        ballHandler.shootTheBall();
    }

    public void teleopInit() {
        //blank for now
    }

    //Gandalf = 100pts
    public void teleopPeriodic() {
        if (joyStick.getRawButton(HardwarePortsEnum.shootButtonNumber)) {
            ballHandler.shootTheBall();
        } else if (joyStick.getRawButton(2)) {
            ballHandler.catchTheBall();
        } else if (joyStick.getRawButton(3)) {
            ballHandler.loadTheBall();
        } else if (joyStick.getRawButton(4)) {
            ballHandler.armTheShooter();
        } else if (joyStick.getRawButton(5)) {
            ballHandler.passTheBall();
        }
    }

    public void testPeriodic() {

    }
}
