/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Author Zephan
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSystem implements Runnable {

    RangeFinder rangeFinder;
    RangeFinder rightRangeFinder = new RangeFinder(HardwarePortsEnum.rightRangeFinderPingPort, HardwarePortsEnum.rightRangeFinderEchoPort);//Default
    StereoRangeFinder stereoRangeFinder;
    Joystick driveStick;
    Gyro gyro;
    public static int useGyro = 1;

    RobotDrive mainDrive = new RobotDrive(1, 2, 3, 4); //change motors later

    int driveMode;

    public DriveSystem(Joystick driveStick) {
        this.driveStick = driveStick;
        gyro = new Gyro(useGyro);
        gyro.reset();
        driveMode = DriveModeEnum.Disabled;
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

    public void run() {
//      Runs... Forever  
        while (true) {

            try {

                if (driveMode == DriveModeEnum.Disabled) {
                    // If the robot is disabled ot will do nothing.

                } else if (driveMode == DriveModeEnum.Autonomous) {
                    // If the robot is in autonomous it will run autonomous
                } else if (driveMode == DriveModeEnum.Teleop) {
                    // If the robot is in teleop it will accept input from the joysticks.
                    mainDrive.mecanumDrive_Cartesian(driveStick.getAxis(Joystick.AxisType.kX),
                            driveStick.getAxis(Joystick.AxisType.kY),
                            driveStick.getAxis(Joystick.AxisType.kZ),
                            gyro.getAngle() * useGyro);
                } else {
                    // If this happens something very bad has happened.
                    System.out.println("Something BAD very BAD has happened :(. Or you suck :D");
                }

                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }

        }
    }

    void moveAutonomous(double magnitude, double direction, double rotation) {
        mainDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    public void moveFoward(double distanceInFeet) {

        if (GlobalVariables.wantedDistanceFromWall < distanceInFeet) {
            moveAutonomous(.5, 0, 0);
        } else if (GlobalVariables.wantedDistanceFromWall > distanceInFeet) {
            moveAutonomous(-.25, 0, 0);
        } else {
            moveAutonomous(0, 0, 0);
        }

    }

    public void rotateToDegree(int degree) { // Turns the robot to the degree that is passed into it. 

        double rotationSpeed;
//      The robot should turn faster the further it is away from it's goal so
//      to find that we use the equation of a straight line which is Y = MX + B.
//      M = 1/180 X = gyro.getAngle() - degree B = 0
        while (gyro.getAngle() != degree) {
            rotationSpeed = (gyro.getAngle() - degree) / 180;

            moveAutonomous(0, 0, rotationSpeed);

        }

    }

    public void rotate(int degree) { // Used only in autonomous

        rotateToDegree((int) (gyro.getAngle() + degree));

    }

}
