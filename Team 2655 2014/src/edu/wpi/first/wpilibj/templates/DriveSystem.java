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
                    rotateToDegree(stereoRangeFinder.degreesOffset());
                    gyro.reset(); // zero the gyro

                    while (rightRangeFinder.getDistanceInches() > 60) {
                        moveAutonomous(0.75, 0.0, 0.0); //this should move forward at 75% speed.
                    }
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

    public void moveAutonomous(double magnitude, double direction, double rotation) {
        mainDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    //-----Josh---------------------------------------------------------------------------------------------------------
    public void rotateToDegree(int degree) { //This is to rotate to a certain direction. This is just a start, I know it needs to be modified. --Josh
//        while (gyro.getAngle() != degree) {
//            moveAutonomous(0.0, 0.0, 1.0); //Might want to add a kFactor to slow it down as it gets closer.
//        }
//        moveAutonomous(0.0, 0.0, 0.0); //stops
    }
    //------------------------------------------------------------------------------------------------------------------
}
