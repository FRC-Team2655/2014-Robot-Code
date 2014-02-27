/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

// TODO make software test harness (this is not trivial by the way)
public class RobotTemplate extends IterativeRobot implements LiveWindowSendable {

    private DriveSystem driveSystem;
    private BallHandler ballHandler;

    private TeamJoystick joystick;

    private RangeFinder rangeFinder;

    private Button shootButton;
    private Button driveModeButton;
    private Button loadButton;
    private Button passButton;
    private Button calibrateGyroButton;
    private int button1Counter;
    private int button2Counter;

//    private CurrentSensor crioCurrentSensor;
//    private CurrentSensor dscCurrentSensor;
    // TODO enable cRIO CurrentSensor in RobotTemplate when hardware is available 
    // TODO enable DSC CurrentSensor in RobotTemplate when hardware is available 
    public RobotTemplate() {
        // not sure this gets called because we have the IterateRobot
    }

    public void robotInit() {

        joystick = new TeamJoystick(1);
        rangeFinder = new RangeFinder(Ports.frontRangeFinderChannel);
        ballHandler = new BallHandler();
        driveSystem = new DriveSystem(joystick);

        driveModeButton = new Button(joystick, Global.driveModeButton);
        loadButton = new Button(joystick, Global.loadButton);
        shootButton = new Button(joystick, Global.shootButton);
        passButton = new Button(joystick, Global.poopButton);
        calibrateGyroButton = new Button(joystick, Global.calibrateGyroButton);

        button1Counter = 0;
        button2Counter = 0;

    }

    public void disabledInit() {

        driveSystem.setDisabled();
    }

    public void disabledPeriodic() {
        ballHandler.displayPressure();
        driveSystem.setDisabled();
    }

    public void autonomousInit() {
        driveSystem.setAutonomous();
//      Global.smartDashBoardGlobalVariables();

    }

    public void autonomousPeriodic() {
        double needToMoveDistance = rangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall;
        ballHandler.displayPressure();

        if (Math.abs(needToMoveDistance) > 0.5) {
            driveSystem.moveDistance(needToMoveDistance);
        } else {
            ballHandler.shootTheBall();
            TeamTimer.delay(10000);
        }

    }

    public void teleopInit() {

        Global.smartDashBoardGlobalVariables();

        driveSystem.setTeleop();
    }

    //Gandalf = 100pts
    //Frodo = 50pts
    public void teleopPeriodic() {
        double needToMoveDistance = rangeFinder.getDistanceFeet() - Global.wantedDistanceFromWall;

//      These display smartdashboard values  
        SmartDashboard.putNumber("Need to move", needToMoveDistance);
        SmartDashboard.putNumber("RangeFinder Feet", rangeFinder.getDistanceFeet());
        driveSystem.outputGyro();
        ballHandler.displayPressure();
//        

//        SmartDashboard.putNumber("RangeFinder Inches", stereoRangeFinder.getDistanceInches());
//        SmartDashboard.putNumber("RangeFinder Feet", stereoRangeFinder.getDistanceFeet());
//        SmartDashboard.putNumber("Gyro Angle", driveSystem.gyro.getAngle());
//        SmartDashboard.putNumber("Drive Mode", driveType);
        //Shoot Button -------------------------------------------------------
        if (shootButton.theButtonToggled()) { //Is the Button Pressed?
            button1Counter++;

            SmartDashboard.putNumber("Button 1 has been pressed this many times:", button1Counter);
            ballHandler.shootTheBall();// Do said action
        }

        if (passButton.theButtonToggled()) { //Is the Button Pressed?
            ballHandler.passTheBall();// Do said action
        }

        //Load Button
        if (loadButton.theButtonToggled()) {
            button2Counter++;
            SmartDashboard.putNumber("Button 2 has been pressed this many times:", button2Counter);

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
        if (driveModeButton.theButtonToggled()) {
            if (Global.johnMode == 1) {
                Global.johnMode = 0;
            } else {
                Global.johnMode = 1;
            }
            SmartDashboard.putNumber("DriveMode:(0 = R/C Mode 1 = John Mode)", Global.johnMode);
        }
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

    //
    // LIveWindows support code
    //
    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
//            m_table.putNumber("DSC Current", dscCurrentSensor.getCurrent());
//            m_table.putNumber("cRIO Current", crioCurrentSensor.getCurrent());
            m_table.putNumber("Range finder (ft)", rangeFinder.getDistanceFeet());
        }
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }

    public void initTable(ITable arg0) {
        m_table = arg0;
        updateTable();
    }

    public ITable getTable() {
        return m_table;
    }

    public String getSmartDashboardType() {
        return "Robby The Robot";
    }

}
