package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private final TeamDoubleSolenoid loadArmLift;
    private final Relay infeedArmMotor;

    // define a couple of constants
    private final DoubleSolenoid.Value DROPARMS = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value LIFTARMS = DoubleSolenoid.Value.kReverse;
    private final DoubleSolenoid.Value LIFTOFF = DoubleSolenoid.Value.kOff;
    private final Relay.Value MOTOR_ON = Relay.Value.kOn;
    private final Relay.Value MOTOR_OFF = Relay.Value.kOff;
    private final long DROPARM_TIMER = Global.loadArmExtendTime;
    private final long LIFTARM_TIMER = Global.loadArmRaiseTime;

    public InFeed() {
        infeedArmMotor = new Relay(Ports.infeedArmMotorControlChannel);
        loadArmLift = new TeamDoubleSolenoid(Ports.loadArmExtendChannel, Ports.loadArmRetractChannel);
    }

    // drop the arms
    // turn on the motors
    void on() {

        loadArmLift.set(DROPARMS); // turn air on to put arms down

        TeamTimer.delay(DROPARM_TIMER);

        infeedArmMotor.set(MOTOR_ON); // turn motors on

        loadArmLift.set(LIFTOFF); // turn air off
    }

    // lift the arms (and ball maybe)
    // turn off the motor
    void off() {

        loadArmLift.set(LIFTARMS); // turn air on to lift arms

        TeamTimer.delay(LIFTARM_TIMER);

        loadArmLift.set(LIFTOFF); // turn air off

        infeedArmMotor.set(MOTOR_OFF); // turn motors off

    }
}
