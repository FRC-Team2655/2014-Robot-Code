/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// TODO make software test harness (this is not trivial by the way)
public class RobotTemplate extends IterativeRobot {

    private DriveSystem driveSystem;
    private BallHandler ballHandler;
    private LightSensor lightSensor;

    private TeamJoystick joystick;
    private TeamJoystick xbox;

    private RangeFinder rangeFinder;

    private Button shootButton;
    private Button driveModeButton;
    private Button loadButton;
    private Button passButton;
    private Button anchorButton;
    private Button sideArmsButton;
    private Button calibrateGyroButton;
    private int button2Counter;

    private final boolean isNotInGoal = false;

    private boolean hasShot;

    Timer autonomousTimer;

//    private CurrentSensor crioCurrentSensor;
//    private CurrentSensor dscCurrentSensor;
    // TODO enable cRIO CurrentSensor in RobotTemplate when hardware is available 
    // TODO enable DSC CurrentSensor in RobotTemplate when hardware is available 
    public RobotTemplate() {
        // not sure this gets called because we have the IterateRobot
    }

    public void robotInit() {

        joystick = new TeamJoystick(1);
        xbox = new TeamJoystick(2);
        rangeFinder = new RangeFinder(Ports.frontRangeFinderChannel);
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joystick);
        lightSensor = new LightSensor();
        autonomousTimer = new Timer();

        driveModeButton = new Button(joystick, Global.driveModeButton);
        loadButton = new Button(xbox, Global.loadButton);
        shootButton = new Button(xbox, Global.shootButton);
        passButton = new Button(xbox, Global.poopButton);
        anchorButton = new Button(joystick, Global.anchorButton);
        sideArmsButton = new Button(xbox, Global.sideArmsButton);
        calibrateGyroButton = new Button(joystick, Global.calibrateGyroButton);

        button2Counter = 0;
        ballHandler.reset();

        hasShot = false;
    }

    public void disabledInit() {
        driveSystem.setDisabled();
    }

    public void disabledPeriodic() {
        ballHandler.displayPressure();
        driveSystem.setDisabled();
    }

    public void autonomousInit() {
        driveSystem.calibrateGyro();
        driveSystem.setAutonomous();
        autonomousTimer.reset();
        autonomousTimer.start();
        hasShot = false;
    }

    public void autonomousPeriodic() {
        if (autonomousTimer.get() <= 3.5) {
            driveSystem.moveAutonomous(0.25, 0, 0); //first variable is speed. mess with it when needed (Bennett)
        } else {
            if (autonomousTimer.get() < 5 && lightSensor.isGoalHot() == isNotInGoal) {
                TeamTimer.delay(1500);
            } else if (hasShot == false && ballHandler.checkNeededAirPressureToShoot()) {
//            driveSystem.rotateToDegree(0);   
                ballHandler.shootTheBall();
                autonomousTimer.stop();
                hasShot = true;
            }
        }
//        double needToMoveDistance = rangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall;
//        ballHandler.displayPressure();
//
//        if (Math.abs(needToMoveDistance) > 0.5) {
//            driveSystem.moveDistance(needToMoveDistance);
//        } else {
//            ballHandler.shootTheBall();
//            TeamTimer.delay(10000);
//        }
    }

    public void teleopInit() {
        //Global.smartDashBoardGlobalVariables();       
        driveSystem.setTeleop();
        hasShot = false;
    }

    //Gandalf = 100pts
    //Frodo = 50pts
    public void teleopPeriodic() {
//        double needToMoveDistance = rangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall;

//      These display smartdashboard values  
//        SmartDashboard.putNumber("Need to move", needToMoveDistance);
        driveSystem.displayGyro();
        ballHandler.displayPressure();
//        ballHandler.displayBallInMitt();
//        

//        SmartDashboard.putNumber("RangeFinder Inches", stereoRangeFinder.getDistanceInches());
//        SmartDashboard.putNumber("RangeFinder Feet", stereoRangeFinder.getDistanceFeet());
//        SmartDashboard.putNumber("Gyro Angle", driveSystem.gyro.getAngle());
//        SmartDashboard.putNumber("Drive Mode", driveType);
        //Shoot Button -------------------------------------------------------
        if (shootButton.theButtonToggled() && ballHandler.checkNeededAirPressureToShoot()) { //Is the Button Pressed?
//          ballHandler.armTheShooter();
//            driveSystem.rotateToDegree(0);
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
                ballHandler.loadDisable();
            }
        }

        //Anchor Button
        if (anchorButton.theButtonToggled()) {
            if (!ballHandler.anchorIsDown()) {
                ballHandler.lowerAnchor();
            } else {
                ballHandler.raiseAnchor();
            }
        }
        if (sideArmsButton.theButtonToggled()) {
            if (!ballHandler.sideArmsAreDown()) {
                ballHandler.openSideArms();
            } else {
                ballHandler.closeSideArms();
            }
        }
//      Drive Mode Toggle  
//        if (driveModeButton.theButtonToggled()) {
//            if (Global.johnMode == 1) {
//                Global.johnMode = 0;
//            } else {
//                Global.johnMode = 1;
//            }
//            SmartDashboard.putNumber("DriveMode:(0 = R/C Mode 1 = John Mode)", Global.johnMode);
//        }
        if (calibrateGyroButton.theButtonToggled()) {
            driveSystem.calibrateGyro();
        }
    }

    public void testInit() {
        driveSystem.setTest();
    }

    public void testPeriodic() {
        ballHandler.displayPressure();
    }

}
