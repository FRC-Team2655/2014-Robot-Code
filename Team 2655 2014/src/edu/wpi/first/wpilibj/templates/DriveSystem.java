/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSystem implements Runnable {
    
    
    Joystick driveStick;
    Gyro greg;
    int driveType;
    RobotDrive mainDrive = new RobotDrive(1,2,3,4);
    
    
    public DriveSystem(Joystick driveStick, Gyro greg, int driveType) {
        this.driveStick = driveStick;
        this.greg = greg;
        this.driveType = driveType;
    }
    public void teleopDrive(){
        mainDrive.mecanumDrive_Cartesian(driveStick.getAxis(Joystick.AxisType.kX), driveStick.getAxis(Joystick.AxisType.kY), 
                driveStick.getAxis(Joystick.AxisType.kZ),greg.getAngle() * driveType);
    }
    
    public void run() {
//        throw new java.lang.UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
