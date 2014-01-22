

package edu.wpi.first.wpilibj.templates;

// Author Alex Senneville - edited by Zephan

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {
    
    Relay leftArmMotorControl;
    Relay rightArmMotorControl;
    public InFeed() {
            // Change arguments later.
        
        leftArmMotorControl = new Relay(HardwarePortsEnum.leftArmMotorControlChannel);
        rightArmMotorControl = new Relay(HardwarePortsEnum.rightArmMotorControlChannel);        
    
        // Make sure to change relays and channels later.
    }
//  This could be changed to Foward, reverse, and off.
    void on() {
        leftArmMotorControl.set(Relay.Value.kOn);
        rightArmMotorControl.set(Relay.Value.kOn);
//      rightArmMotorControl.set(Relay.Value.kForward); - not sure if we need this.
        // Turns motors on.

    }

    void off() {
        leftArmMotorControl.set(Relay.Value.kOff);
        rightArmMotorControl.set(Relay.Value.kOff);
            // Turns the motors off. 
    }
}
