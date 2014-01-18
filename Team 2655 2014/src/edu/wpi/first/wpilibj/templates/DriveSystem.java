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
    Gyro gyro;
    int driveType;
    
    RobotDrive mainDrive = new RobotDrive(1,2,3,4); //change motors later
    
    
    public DriveSystem(Joystick driveStick, Gyro gyro) {
        this.driveStick = driveStick;
        this.gyro = gyro;
        this.driveType = driveType;
    }
    public void teleopDrive(){
       
    }
    
    public void run() {
    
    }

    

}
