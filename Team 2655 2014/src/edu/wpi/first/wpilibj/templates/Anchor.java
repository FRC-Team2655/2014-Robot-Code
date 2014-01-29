/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

 //import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Seth - edited by Zephan
 */
public class Anchor {
    
    DoubleSolenoid anchors;
    int anchorRaiseTime = 600;
    boolean debugRaiseException = false; //if this is true, then any exceptions in this class will be printed
    //DigitalInput ballInMittLimitSwitch;
//  You will most likely need a timer.

    public Anchor() {
        
        anchors = new DoubleSolenoid(HardwarePorts.anchorDropChannel, HardwarePorts.anchorRaiseChannel);
        //ballInMittLimitSwitch = new DigitalInput(1);
//      anchors.set(DoubleSolenoid.Value.kOff); - Double checking if anchors are off?

    }
    
    void drop() {
        
        anchors.set(DoubleSolenoid.Value.kForward);
    }
    
    void raise() {
        
        anchors.set(DoubleSolenoid.Value.kReverse);
        try {
            wait(anchorRaiseTime);
            //Find if we need to stop the solenoid when it reaches th top position.
        } catch (InterruptedException ex) {
            if (debugRaiseException) {
                ex.printStackTrace();
            }
        }
        anchors.set(DoubleSolenoid.Value.kOff);
    }
}
