

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
        
            //spin motors
        //put arms down
    }

    void retract() {
            //turn the motors off 
        //retract arms
    }
}
