/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joystick;

    StereoRangeFinder stereoRangeFinder;

    Button shootButton;
    Button anchorButton;
    Button loadButton;
    Button passButton;
    private boolean anchorIsDropped;

    public RobotTemplate() {
        // not sure this gets called because we have the IterateRobot
    }

    public void robotInit() {

        anchorIsDropped = false;

        joystick = new Joystick(1);
        stereoRangeFinder = new StereoRangeFinder();
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joystick);

        anchorButton = new Button(joystick, Global.anchorButton);
        loadButton = new Button(joystick, Global.loadButton);
        shootButton = new Button(joystick, Global.shootButton);
        passButton = new Button(joystick, Global.poopButton);
    }

    public void disabledInit() {
        driveSystem.setDisabled();
    }

    public void disabledPeriodic() {
        driveSystem.setDisabled();
    }

    public void autonomousInit() {
        driveSystem.setAutonomous();
        Global.smartDashBoardGlobalVariables();

    }

    public void autonomousPeriodic() {

//        driveSystem.rotate(-stereoRangeFinder.degreesOffset());
//        driveSystem.moveDistance(stereoRangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall);
//        driveSystem.rotate(-stereoRangeFinder.degreesOffset());
//        driveSystem.gyro.reset(); // zero the gyro
    }

    public void teleopInit() {
        driveSystem.setTeleop();
    }

    //Gandalf = 100pts
    //Frodo = 50pts
    public void teleopPeriodic() {

//        SmartDashboard.putNumber("RangeFinder Inches", stereoRangeFinder.getDistanceInches());
//        SmartDashboard.putNumber("RangeFinder Feet", stereoRangeFinder.getDistanceFeet());
//        SmartDashboard.putNumber("Gyro Angle", driveSystem.gyro.getAngle());
//        SmartDashboard.putNumber("Drive Mode", driveType);
        //Shoot Button -------------------------------------------------------
        if (shootButton.theButtonToggled()) { //Is the Button Pressed?
            ballHandler.shootTheBall();// Do said action
        }

        if (passButton.theButtonToggled()) { //Is the Button Pressed?
            ballHandler.passTheBall();// Do said action
        }

        //Load Button
        if (loadButton.theButtonToggled()) {
            if (ballHandler.loadIsEnabled() == false) {
                ballHandler.loadEnable();
            } else {
                if (ballHandler.loadIsEnabled()) {
                    ballHandler.loadDisable();
                }
            }
        }
        
        //Anchor Button
        if (anchorButton.theButtonToggled()) {
            if (anchorIsDropped == false) {
                anchorIsDropped = true;
                ballHandler.dropAnchor();
            } else {
                anchorIsDropped = false;
                ballHandler.raiseAnchor();
            }
        }
    }

    public void testInit() {
        driveSystem.setTest();
    }

    public void testPeriodic() {

    }
}
