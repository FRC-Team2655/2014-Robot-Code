/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends IterativeRobot {

    private DriveSystem driveSystem;
    private BallHandler ballHandler;

    private Joystick joystick;

    private RangeFinder rangeFinder;

    private Button shootButton;
    private Button anchorButton;
    private Button loadButton;
    private Button passButton;

    public RobotTemplate() {
        // not sure this gets called because we have the IterateRobot
    }

    public void robotInit() {

        joystick = new Joystick(1);
        rangeFinder = new RangeFinder(Ports.rangeFinderChannel);
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
//      Global.smartDashBoardGlobalVariables();

    }

    public void autonomousPeriodic() {

        if (rangeFinder.getDistanceFeet() > Global.wantedDistanceFromWall) {
            
            driveSystem.moveDistance((rangeFinder.getDistanceFeet()) - Global.wantedDistanceFromWall);
            
        } else {
            ballHandler.shootTheBall();
        }

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

            SmartDashboard.putNumber("Button 1 has been pressed", 0);

            ballHandler.shootTheBall();// Do said action
        }

        if (passButton.theButtonToggled()) { //Is the Button Pressed?
            ballHandler.passTheBall();// Do said action
        }

        //Load Button
        if (loadButton.theButtonToggled()) {
            SmartDashboard.putNumber("Button 4 has been pressed", 0);

            if (ballHandler.loadIsEnabled() == false) {
                SmartDashboard.putNumber("Load enabled", 0);

                ballHandler.loadEnable();
            } else {
                if (ballHandler.loadIsEnabled()) {
                    ballHandler.loadDisable();
                }
            }
        }

        //Anchor Button
        if (anchorButton.theButtonToggled()) {
            if (ballHandler.anchorIsUp()) {
                ballHandler.dropAnchor();
            } else {
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
