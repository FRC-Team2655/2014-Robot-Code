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

public class RobotTemplate extends IterativeRobot {

    DriveSystem driveSystem;
    BallHandler ballHandler;

    Joystick joyStick;

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

        joyStick = new Joystick(1);
        stereoRangeFinder = new StereoRangeFinder();
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joyStick);

        catchButton = new Button(joyStick, Global.catchButton);
        loadButton = new Button(joyStick, Global.loadButton);

    }

    public void disabledInit() {
        driveSystem.setDisabled();
    }

    public void disabledPeriodic() {
        driveSystem.setDisabled();
    }

    public void autonomousInit() {
        driveSystem.setAutonomous();
    }

    public void autonomousPeriodic() {

        driveSystem.rotate(-stereoRangeFinder.degreesOffset());
        driveSystem.moveDistance(stereoRangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall);
        driveSystem.rotate(-stereoRangeFinder.degreesOffset());
        driveSystem.gyro.reset(); // zero the gyro

    }

    public void teleopInit() {
        driveSystem.setTeleop();
    }

    //Gandalf = 100pts
    //Frodo = 50pts
    public void teleopPeriodic() {

        //Shoot Button -------------------------------------------------------
        if (joyStick.getRawButton(Global.shootButton)) { //Is the Button Pressed?
            ballHandler.shootTheBall();// Do said action

        }

        //Catch Button -------------------------------------------------------
        if (catchButton.ToggleCheck() == true) {
            driveSystem.moveAutonomous(-0.5, 0.0, 0.0);
//                ballHandler.catchEnable();  
        }

            //Load Button
            if (loadButton.ToggleCheck() == true) {
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
        driverStationConsole.println(DriverStationLCD.Line.kUser1, 1, "Left Distance in Inches: " + stereoRangeFinder.getDistanceLeft());
        driverStationConsole.println(DriverStationLCD.Line.kUser2, 1, "Right Distance in Inches: " + stereoRangeFinder.getDistanceRight());
        driverStationConsole.updateLCD();
    }
}
