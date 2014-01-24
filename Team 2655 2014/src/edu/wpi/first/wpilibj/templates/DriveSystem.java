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
        
        driveMode = DriveModeEnum.Disabled;
        
    }
    
    public void setAutonomous() {
        
        driveMode = DriveModeEnum.Autonomous;
        
    }
    
    public void setTeleop() {
        
        driveMode = DriveModeEnum.Teleop;
        
    }

    //should we add a reset button for the gyro?
    //should we also add another button to automatically point to zero
    public void run() {
        
        while (true) {
            
            try {
                
                if (driveMode == DriveModeEnum.Disabled) {
                    
                } else if (driveMode == DriveModeEnum.Autonomous) {
                    
                } else if (driveMode == DriveModeEnum.Teleop) {
                    mainDrive.mecanumDrive_Cartesian(driveStick.getAxis(Joystick.AxisType.kX),
                            driveStick.getAxis(Joystick.AxisType.kY),
                            driveStick.getAxis(Joystick.AxisType.kZ),
                            gyro.getAngle() * useGyro);
                } else {
                    System.out.println("Something BAD very BAD has happened :(. Or you suck :D");
                }
                
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            
        }
    }

//    public void setDriveMode(int mode) {
//        if (mode < DriveModeEnum.Disabled) {
//            
//        } else if (mode > DriveModeEnum.Teleop) {
//
//        } else {
//            driveMode = mode;
//        }
//    }
    public void moveAutonomous(double magnitude, double direction, double rotation) {
        mainDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    //-----Josh---------------------------------------------------------------------------------------------------------
    public void rotateToDegree(int degree) { //This is to rotate to a certain direction. This is just a start, I know it needs to be modified. --Josh
        while (gyro.getAngle() != degree) {
            moveAutonomous(0.0, 0.0, 1.0); //Might want to add a kFactor to slow it down as it gets closer.
        }
        moveAutonomous(0.0, 0.0, 0.0); //stops
    }
    //------------------------------------------------------------------------------------------------------------------
}
