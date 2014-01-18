

package edu.wpi.first.wpilibj.templates;

// Author Alex Senneville

import edu.wpi.first.wpilibj.Relay;

public class InFeed {
    Solenoid liftArmControl;
    Relay leftArmMotorControl;
    Relay rightArmMotorControl;
    public InFeed() {
            //change arguments later
        leftArmMotorControl = new Relay(1);
        rightArmMotorControl = new Relay(2);
        liftArmControl = new Solenoid(3, 4, 5, 566);
        //make sure to change relays and channels later
    }

    void extend() {
        leftArmMotorControl.set(Relay.Value.kOn);
        leftArmMotorControl.set(Relay.Value.kForward);
        rightArmMotorControl.set(Relay.Value.kOn);
        rightArmMotorControl.set(Relay.Value.kForward);
        liftArmControl.extend();
            //spin motors
        //put arms down
    }

    void retract() {
        leftArmMotorControl.set(Relay.Value.kOff);
        rightArmMotorControl.set(Relay.Value.kOff);
        liftArmControl.retract();
            //turn the motors off 
        //retract arms
    }
}
