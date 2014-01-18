

package edu.wpi.first.wpilibj.templates;

// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {
    DoubleSolenoid liftArmControl;
    Relay leftArmMotorControl;
    Relay rightArmMotorControl;
    public InFeed() {
            //change arguments later
        
        leftArmMotorControl = new Relay(1);
        rightArmMotorControl = new Relay(2);
        liftArmControl = new DoubleSolenoid(PublicEnumHardwarePorts.inFeedlowerChannel, PublicEnumHardwarePorts.inFeedraiseChannel );
        
    
        //make sure to change relays and channels later
    }

    void lower() {
        leftArmMotorControl.set(Relay.Value.kOn);
        leftArmMotorControl.set(Relay.Value.kForward);
        rightArmMotorControl.set(Relay.Value.kOn);
        rightArmMotorControl.set(Relay.Value.kForward);
        liftArmControl.set(DoubleSolenoid.Value.kForward);
        //check forward and reverse
            //spin motors
        //put arms down
    }

    void raise() {
        leftArmMotorControl.set(Relay.Value.kOff);
        rightArmMotorControl.set(Relay.Value.kOff);
        liftArmControl.set(DoubleSolenoid.Value.kReverse);
            //turn the motors off 
        //retract arms
    }
}
