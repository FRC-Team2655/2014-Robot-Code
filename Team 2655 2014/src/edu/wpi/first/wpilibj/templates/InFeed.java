package edu.wpi.first.wpilibj.templates;

// Author Zephan
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    Relay infeedArmMotorControl;

    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotorControl = new Relay(Ports.crioSlot2,Ports.DigitalModule.infeedArmMotorControlChannel);

    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    

        infeedArmMotorControl.set(Relay.Value.kOn);
    }

    void off() {
//  Turns the motors off    

        infeedArmMotorControl.set(Relay.Value.kOff);

    }
}
