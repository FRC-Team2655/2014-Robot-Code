package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private final DoubleSolenoid loadArmLift;
    private final Relay infeedArmMotorControl;

    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotorControl = new Relay(Ports.DigitalModule.infeedArmMotorControlChannel);
        loadArmLift = new DoubleSolenoid(Ports.SolenoidModule2.loadArmExtendChannel, Ports.SolenoidModule2.loadArmRetractChannel);

    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    

        loadArmLift.set(DoubleSolenoid.Value.kForward);
        try {
            Thread.sleep(Global.loadArmExtendTime);

        } catch (InterruptedException ex) {

        }
        infeedArmMotorControl.set(Relay.Value.kOn);
        loadArmLift.set(DoubleSolenoid.Value.kOff);
    }

    void off() {
//  Turns the motors off    

        loadArmLift.set(DoubleSolenoid.Value.kReverse);
        try {

            Thread.sleep(Global.loadArmRaiseTime);

        } catch (InterruptedException ex) {

        }
        loadArmLift.set(DoubleSolenoid.Value.kOff);
        infeedArmMotorControl.set(Relay.Value.kOff);

    }
}
