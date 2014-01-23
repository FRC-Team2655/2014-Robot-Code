

package edu.wpi.first.wpilibj.templates;

// Author Zephan

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {
    
    Relay leftArmMotorControl;
    Relay rightArmMotorControl;
    
    public InFeed() {
//  Make sure to change relays and channels later.
        leftArmMotorControl = new Relay(HardwarePortsEnum.leftArmMotorControlChannel);
        rightArmMotorControl = new Relay(HardwarePortsEnum.rightArmMotorControlChannel);         
    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    
        leftArmMotorControl.set(Relay.Value.kOn);
        rightArmMotorControl.set(Relay.Value.kOn);
        rightArmMotorControl.set(Relay.Value.kForward);
        leftArmMotorControl.set(Relay.Value.kForward);
    }


    void off() {
//  Turns the motors off    
        leftArmMotorControl.set(Relay.Value.kOff);
        rightArmMotorControl.set(Relay.Value.kOff);
    }
}
