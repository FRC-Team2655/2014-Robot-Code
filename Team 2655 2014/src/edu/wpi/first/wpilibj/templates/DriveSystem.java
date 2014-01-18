/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DriveSystem implements Runnable {

    Joystick driveStick;
    Gyro gyro;
    public static int useGyro = 1;
    
    Talon frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;

    RobotDrive mainDrive;

    int driveMode;

    public DriveSystem(Joystick driveStick) {
        this.driveStick = driveStick;
        this.gyro = gyro;
        gyro.reset();
        driveMode = DriveModeEnum.Disabled;
        frontLeftMotor = new Talon(HardwarePortsEnum.frontLeftMotorChannel);
        backLeftMotor = new Talon(HardwarePortsEnum.backLeftMotorChannel);
        frontRightMotor = new Talon(HardwarePortsEnum.frontRightMotorChannel);
        backRightMotor = new Talon(HardwarePortsEnum.backRightMotorChannel);
        
        mainDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
        
        
    }

    //should we add a reset button for the gyro?
    //should we also add another button to automatically point to zero
    public void run() {

        while (true) {
            try {

                mainDrive.mecanumDrive_Cartesian(driveStick.getAxis(Joystick.AxisType.kX),
                        driveStick.getAxis(Joystick.AxisType.kY),
                        driveStick.getAxis(Joystick.AxisType.kZ),
                        gyro.getAngle() * useGyro);

                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

    public void setDriveMode(int mode) {
        if (mode < DriveModeEnum.Disabled) {

        } else if (mode > DriveModeEnum.Teleop) {
            
        } else {
            driveMode = mode;
        }
    }

}
