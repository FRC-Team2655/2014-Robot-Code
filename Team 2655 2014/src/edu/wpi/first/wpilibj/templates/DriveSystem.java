/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Author Zephan
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem {

    private final TeamJoystick driveStick;
    private final Gyro gyro; // TODO test / calibrate gyro
    private final Thread thread;
    private final RobotDrive mainDrive;

    // TODO implement wheel encoders for wheel speed
    private final Encoder leftFrontWheelEncoder;
    private final Encoder rightFrontWheelEncoder;

    private int gyroCounter = 0;

    private int driveMode;

    class DriveModeEnum {
//      Possible states the robot can be in.  

        static final int Disabled = 0;
        static final int Autonomous = 1;
        static final int Teleop = 2;
        static final int Test = 3;
    }

    private class DriveSystemThread extends Thread {

        public DriveSystemThread() {

        }

        public void run() {
//      Runs... Forever  
            while (true) {

                try {
                    if (driveMode == DriveModeEnum.Teleop) {
                        // If the robot is in teleop it will accept input from the joysticks.
                        mainDrive.mecanumDrive_Cartesian(driveStick.getAxis(Joystick.AxisType.kX),
                                driveStick.getAxis(Joystick.AxisType.kY),
                                driveStick.getAxis(Joystick.AxisType.kZ),
                                gyro.getAngle() * Global.johnMode);
                    } else {

                    }

                    Thread.sleep(Global.driveIdleTime);
                } catch (InterruptedException ex) {
                }

            }
        }

    }

    public DriveSystem(TeamJoystick driveStick) {
        this.driveStick = driveStick;
        gyro = new Gyro(Ports.gyroChannel);
        gyro.reset();
        driveMode = DriveModeEnum.Disabled;
        mainDrive = new RobotDrive(Ports.frontLeftMotorChannel, Ports.backLeftMotorChannel, Ports.frontRightMotorChannel, Ports.backRightMotorChannel);

        leftFrontWheelEncoder = new Encoder(Ports.frontLeftMotorRotationAChannel, Ports.frontLeftMotorRotationBChannel);
        rightFrontWheelEncoder = new Encoder(Ports.frontRightMotorRotationAChannel, Ports.frontRightMotorRotationBChannel);

        leftFrontWheelEncoder.setDistancePerPulse(Global.wheelDistancePerPulse);
        rightFrontWheelEncoder.setDistancePerPulse(Global.wheelDistancePerPulse);

        leftFrontWheelEncoder.start();
        rightFrontWheelEncoder.start();

        thread = new DriveSystemThread();
        thread.start();
    }

    public void setDisabled() {
//  Sets the drive mode to Disabled.        
        driveMode = DriveModeEnum.Disabled;

    }

    public void setAutonomous() {
//  Sets the drive mode to Autonomous.
        driveMode = DriveModeEnum.Autonomous;

    }

    public void setTeleop() {
//  Sets the drive mode to Teleop.        
        driveMode = DriveModeEnum.Teleop;

    }

    public void setTest() {
//  Sets the drive mode to Teleop.        
        driveMode = DriveModeEnum.Test;

    }

    void moveAutonomous(double magnitude, double direction, double rotation) {
        mainDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    public void moveDistance(double distanceToMoveInFeet) {
//      The robot should move faster the further it is away from it's goal so
//      to find that we use the equation of a straight line which is Y = MX + B.
//      M = 1/5(0.2) X = 0.2 * distanceToMoveInFeet B = 0.
        if (distanceToMoveInFeet > Global.distanceLimitPositive) {
            distanceToMoveInFeet = Global.distanceLimitPositive;
        }
        if (distanceToMoveInFeet < Global.distanceLimitNegative) {
            distanceToMoveInFeet = Global.distanceLimitNegative;
        }

        double speed = (Global.speedSlopeMoving * distanceToMoveInFeet);

        moveAutonomous(speed, 0, 0);
    }

    public void rotateToDegree(int degree) { // Turns the robot to the degree that is passed into it. 
        double rotationSpeed = (gyro.getAngle() - degree) * Global.speedSlopeRotate;

//      The robot should turn faster the further it is away from it's goal so
//      to find that we use the equation of a straight line which is Y = MX + B.
//      M = 1/180 X = gyro.getAngle() - degree B = 0
//      while (gyro.getAngle() != degree) {
        while (Math.abs(rotationSpeed) > 0.0999999) {
            moveAutonomous(0, 0, rotationSpeed);

            rotationSpeed = (gyro.getAngle() - degree) * Global.speedSlopeRotate;
        }
    }

    public void rotate(int degree) { // Used only in autonomous
        rotateToDegree((int) (gyro.getAngle() + degree));
    }

    public void calibrateGyro() {
        gyro.reset();
    }

    public void outputGyro() {
        gyroCounter++;

        if (gyroCounter == 100) {
            gyroCounter = 0;
            SmartDashboard.putNumber("Gyro Angle:", gyro.getAngle());
        }
    }

}
