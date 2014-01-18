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
    public static int useGyro = 1;
    
    RobotDrive mainDrive = new RobotDrive(1,2,3,4); //change motors later
    
    
    public DriveSystem(Joystick driveStick, Gyro gyro) {
        this.driveStick = driveStick;
        this.gyro = gyro;
        gyro.reset();
    }
   
    //should we add a reset button for the gyro?
    //should we also add another button to automatically point to zero
    public void run() {
        
        
        while(true){ 
            try {
                //make a sleep mode in the smart dash? should the thread sleep?
                //create and if statement to determine if we are in teleop or auto
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

    

}
