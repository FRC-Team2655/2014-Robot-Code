package edu.wpi.first.wpilibj.templates;

// Author Zephan Editor Seth
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class InFeed {

    private final DoubleSolenoid loadArmLift;

    // two spike relays
    private final Relay leftLoadArmMotor;
    private final Relay rightLoadArmMotor;

    private final DoubleSolenoid.Value DROP_ARMS = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value AIR_OFF = DoubleSolenoid.Value.kOff;
    private final DoubleSolenoid.Value LIFT_ARMS = DoubleSolenoid.Value.kReverse;

    private final Relay.Value MOTOR_FORWARD = Relay.Value.kForward;
    private final Relay.Value MOTOR_REVERSE = Relay.Value.kReverse;
    private final Relay.Value MOTOR_OFF = Relay.Value.kOff;

    public InFeed() {
        loadArmLift = new DoubleSolenoid(Ports.liftArmExtendChannel, Ports.liftArmRetractChannel);

        leftLoadArmMotor = new Relay(Ports.infeedLeftArmMotorControlChannel);
        rightLoadArmMotor = new Relay(Ports.infeedRightArmMotorControlChannel);
    }

    // drop the arms
    // turn on the motors
    void on() {

        loadArmLift.set(DROP_ARMS); // turn air on to put arms down

        TeamTimer.delay(Global.loadArmExtendTime);

        loadArmLift.set(AIR_OFF); // turn air off

        leftLoadArmMotor.set(MOTOR_FORWARD); // turn motors on in forward direction
        rightLoadArmMotor.set(MOTOR_REVERSE); // turn motors on in forward direction
    }

    // lift the arms (and ball maybe)
    // turn off the motor
    void off() {

        leftLoadArmMotor.set(MOTOR_OFF); // turn motors off
        rightLoadArmMotor.set(MOTOR_OFF);

        loadArmLift.set(LIFT_ARMS); // turn air on to lift arms

        TeamTimer.delay(Global.loadArmRaiseTime);

        loadArmLift.set(AIR_OFF); // turn air off

    }
}
