package edu.wpi.first.wpilibj.templates;

// Author Zephan
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private DoubleSolenoid inFeedSolenoid;
    Relay infeedArmMotorControl;
    
    public int forward = 1;
    public int reverse = 2;

    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotorControl = new Relay(Ports.crioSlot2,Ports.DigitalModule.infeedArmMotorControlChannel);

    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    
        inFeedSolenoid.set(DoubleSolenoid.Value.kForward);
        infeedArmMotorControl.set(Relay.Value.kOn);
        inFeedSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    void off() {
//  Turns the motors off    

        infeedArmMotorControl.set(Relay.Value.kOff);

    }
}
