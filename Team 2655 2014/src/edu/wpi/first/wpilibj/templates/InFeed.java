package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private final Relay loadArmLift;
    private final Relay loadArmMotor;

    // define a couple of constants
    private final Relay.Value DROPARMS = Relay.Value.kForward;
    private final Relay.Value LIFTARMS = Relay.Value.kReverse;
    private final Relay.Value LIFTOFF = Relay.Value.kOff;

    private final Relay.Value MOTOR_ON = Relay.Value.kForward;
    private final Relay.Value MOTOR_OFF = Relay.Value.kOff;
    private final long DROPARM_TIMER = Global.loadArmExtendTime;
    private final long LIFTARM_TIMER = Global.loadArmRaiseTime;

    public InFeed() {
        loadArmMotor = new Relay(Ports.infeedArmMotorControlChannel);
        loadArmLift = new Relay(Ports.loadArmLiftChannel);
    }

    // drop the arms
    // turn on the motors
    void on() {

        loadArmLift.set(DROPARMS); // turn air on to put arms down
        
        TeamTimer.delay(DROPARM_TIMER);
        
        loadArmLift.set(LIFTOFF); // turn air off
        
        loadArmMotor.set(MOTOR_ON); // turn motors on in forward direction
    }

    // lift the arms (and ball maybe)
    // turn off the motor
    void off() {

        loadArmLift.set(LIFTARMS); // turn air on to lift arms

        TeamTimer.delay(LIFTARM_TIMER);

        loadArmLift.set(LIFTOFF); // turn air off

        loadArmMotor.set(MOTOR_OFF); // turn motors off

    }
}
