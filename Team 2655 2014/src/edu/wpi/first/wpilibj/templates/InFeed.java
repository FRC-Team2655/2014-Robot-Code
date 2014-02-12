package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;


public class InFeed {

    private final TeamDoubleSolenoid loadArmLift;
    private final Relay infeedArmMotor;
    private final RangeFinder rangeFinder;
    private final DoubleSolenoid.Value DROP = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value LIFT = DoubleSolenoid.Value.kReverse;
    private final DoubleSolenoid.Value OFF = DoubleSolenoid.Value.kOff;
    private final Relay.Value R_ON = Relay.Value.kOn;
    private final Relay.Value R_OFF = Relay.Value.kOff;
    
    // need to figure out how to use range finder to
    // run in feed system in a smart way.
    
    public InFeed() {
//  Make sure to change relays and channels later.

        infeedArmMotor = new Relay(Ports.infeedArmMotorControlChannel);
        loadArmLift = new TeamDoubleSolenoid(Ports.loadArmExtendChannel, Ports.loadArmRetractChannel);
        rangeFinder = new RangeFinder(Ports.infeedBallDetectChannel);
    }

    void on() {

        loadArmLift.set(DROP); // turn air on to put arms down
        
        TeamTimer.delay(Global.loadArmExtendTime);

        infeedArmMotor.set(R_ON); // turn motors off
        
        loadArmLift.set(OFF); // turn air off
    }

    void off() {

        loadArmLift.set(LIFT); // turn air on to lift arms
        
        TeamTimer.delay(Global.loadArmRaiseTime);

        loadArmLift.set(OFF); // turn air off
        
        infeedArmMotor.set(R_OFF); // turn motors off

    }
}
