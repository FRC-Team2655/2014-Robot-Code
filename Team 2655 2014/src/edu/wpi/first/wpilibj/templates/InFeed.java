package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private final TeamDoubleSolenoid loadArmLift;
    private final Relay infeedArmMotor;

    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotor = new Relay(Ports.infeedArmMotorControlChannel);
        loadArmLift = new TeamDoubleSolenoid(Ports.loadArmExtendChannel, Ports.loadArmRetractChannel);

    }

    void on() {
//  Turns the motors on in the foward direction which should pull the ball in.    

        loadArmLift.set(DoubleSolenoid.Value.kForward);
        try {
            Thread.sleep(Global.loadArmExtendTime);

        } catch (InterruptedException ex) {

        }
        infeedArmMotor.set(Relay.Value.kOn);
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
        infeedArmMotor.set(Relay.Value.kOff);

    }
}
