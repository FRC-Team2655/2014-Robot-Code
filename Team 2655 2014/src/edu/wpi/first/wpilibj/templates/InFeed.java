package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private DoubleSolenoid loadArmLift;
    Relay infeedArmMotorControl;

    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotorControl = new Relay(Ports.crioSlot2,Ports.DigitalModule.infeedArmMotorControlChannel);
        loadArmLift = new DoubleSolenoid(Ports.crioSlot2,Ports.SolenoidModule2.loadArmExtendChannel);
        loadArmLift = new DoubleSolenoid(Ports.crioSlot2,Ports.SolenoidModule2.loadArmRetractChannel);

        
    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    
        try {
            loadArmLift.set(DoubleSolenoid.Value.kForward);
            Thread.sleep(100);
            infeedArmMotorControl.set(Relay.Value.kOn);
            loadArmLift.set(DoubleSolenoid.Value.kOff);
        } catch (InterruptedException ex) {
            
        }
    }

    void off() {
//  Turns the motors off    
        
        try {
            loadArmLift.set(DoubleSolenoid.Value.kForward);
            Thread.sleep(100);
            infeedArmMotorControl.set(Relay.Value.kOn);
            loadArmLift.set(DoubleSolenoid.Value.kOff);
        } catch (InterruptedException ex) {
            
        }

    }
}
