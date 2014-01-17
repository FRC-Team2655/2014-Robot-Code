/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Zephan
 */
public class Solenoid {
    DoubleSolenoid my_solenoid;
    int extendTime;
    
    public Solenoid(int sidecarModule, int extendChannel, int retractChannel, int timeToExtend) {
         my_solenoid = new DoubleSolenoid(sidecarModule, extendChannel, retractChannel);
         extendTime = timeToExtend;
    }
    
    void extend() {
        my_solenoid.set(DoubleSolenoid.Value.kForward);
        //thread.sleep(extendTime);
        my_solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    void retract() {
        
    }
}
