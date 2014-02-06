/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joystick;

    DriverStationLCD driverStationConsole;

    StereoRangeFinder stereoRangeFinder;

//  Variables for possible button stat
    public static boolean lastLoadButtonState = false;

    boolean pressed = true;
    boolean notPressed = false;

    Button catchButton;
    Button loadButton;

    int driveType; //0 means we will not use the gyro in our drive. 1 means the gyro will be in use during robot drive.

    public RobotTemplate() {
        // not sure this gets called because we have the IterateRobot
    }

    public void robotInit() {

        joystick = new Joystick(1);
        stereoRangeFinder = new StereoRangeFinder();
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joystick);

        catchButton = new Button(joystick, Global.catchButton);
        loadButton = new Button(joystick, Global.loadButton);

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

        SmartDashboard.putNumber("RangeFinder Inches", stereoRangeFinder.getDistanceInches());
        SmartDashboard.putNumber("RangeFinder Feet", stereoRangeFinder.getDistanceFeet());
        SmartDashboard.putNumber("Gyro Angle", driveSystem.gyro.getAngle());
        SmartDashboard.putNumber("Drive Mode", driveType);

        //Shoot Button -------------------------------------------------------
        if (joystick.getRawButton(Global.shootButton)) { //Is the Button Pressed?
            ballHandler.shootTheBall();// Do said action
        }

        //Catch Button -------------------------------------------------------
        if (catchButton.theButtonToggled() == true) {
            driveSystem.moveAutonomous(-0.5, 0.0, 0.0);
//                ballHandler.catchEnable();  
        }

        //Load Button
        if (loadButton.theButtonToggled() == true) {
            if (ballHandler.loadIsEnabled() == false) {
                ballHandler.loadEnable();
            }
        } else {
            if (ballHandler.loadIsEnabled() == true) {
                ballHandler.loadDisable();
            }
        }
    }

    public void testInit() {
        driveSystem.setTest();
    }

    public void testPeriodic() {

    }
}
