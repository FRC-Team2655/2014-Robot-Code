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

public class DriveSystem {

    Joystick driveStick;
    Gyro gyro;
    private Thread thread;
    RobotDrive mainDrive;

    int driveMode;

    class DriveModeEnum {
//      Possible states the robot can be in.  

        static final int Disabled = 0;
        static final int Autonomous = 1;
        static final int Teleop = 2;
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
                                gyro.getAngle() * GlobalVariables.johnMode);
                    }

                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }

            }
        }

    }

    public DriveSystem(Joystick driveStick) {
        this.driveStick = driveStick;
        gyro = new Gyro(HardwarePortsEnum.gyroChannel);
        gyro.reset();
        driveMode = DriveModeEnum.Disabled;
        mainDrive = new RobotDrive(1, 2, 3, 4);
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

    void moveAutonomous(double magnitude, double direction, double rotation) {
        mainDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    public void moveDistance(double distanceToMoveInFeet) {
//      The robot should move faster the further it is away from it's goal so
//      to find that we use the equation of a straight line which is Y = MX + B.
//      M = 1/5(0.2) X = 0.2 * distanceToMoveInFeet B = 0.
        if (distanceToMoveInFeet > 5) {
            distanceToMoveInFeet = 5;
        }
        if (distanceToMoveInFeet > - 5) {
            distanceToMoveInFeet = -5;
        }

        double speed = (GlobalVariables.speedSlopeMoving * distanceToMoveInFeet);

        moveAutonomous(speed, 0, 0);
    }

    public void rotateToDegree(int degree) { // Turns the robot to the degree that is passed into it. 

        double rotationSpeed;
//      The robot should turn faster the further it is away from it's goal so
//      to find that we use the equation of a straight line which is Y = MX + B.
//      M = 1/180 X = gyro.getAngle() - degree B = 0
//      while (gyro.getAngle() != degree) {
        rotationSpeed = (gyro.getAngle() - degree) * GlobalVariables.speedSlopeRotate;

        moveAutonomous(0, 0, rotationSpeed);

    }

    public void rotate(int degree) { // Used only in autonomous

        rotateToDegree((int) (gyro.getAngle() + degree));

    }

}
