package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

public class Shooter {

    private final DoubleSolenoid shooterPiston;

//    private final Encoder shooterArmPosition;
    private final DoubleSolenoid.Value EXTEND = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value AIR_OFF = DoubleSolenoid.Value.kOff;
    private final DoubleSolenoid.Value RETRACT = DoubleSolenoid.Value.kReverse;

    public Shooter() {
        shooterPiston = new DoubleSolenoid(Ports.ShooterExtendChannel, Ports.ShooterRetractChannel);

        // TODO finish shooter arm sensor code (angle, angular position, velocity, acceleration
//        shooterArmPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel, Global.reverseShooterRotation, CounterBase.EncodingType.k4X);
//        shooterArmPosition.setDistancePerPulse(Global.shooterRadiansPerPulse);
//        shooterArmPosition.reset();
//        shooterArmPosition.start(); // start at zero
    }

    public void shoot() {
        shooterPiston.set(EXTEND);
        
        // TODO when we get shoot encoder uncomment the next lines
        // need to figure out how many pulses is the right shoot angle
        //while (shooterArmPosition.get() < Global.wantedShooterEndAngle) {};
        // also don't need the time delay in the next line.
        TeamTimer.delay(Global.shooterShootTime);

        shooterPiston.set(RETRACT);
        //while (shooterArmPosition.get() < 10) {};        
        // don't forget to uncomment out the following delay
        TeamTimer.delay(Global.shooterShootTime);

        shooterPiston.set(AIR_OFF);
    }

    public void retract() {// Used in reseting the robot during power on.
        shooterPiston.set(RETRACT);
        TeamTimer.delay(1500);
        shooterPiston.set(AIR_OFF);
    }

    public void pass() {
        shooterPiston.set(EXTEND);
        TeamTimer.delay(Global.shooterPassTime);

        shooterPiston.set(RETRACT);
        TeamTimer.delay(Global.shooterRetractTime);

        shooterPiston.set(AIR_OFF);
    }

    public void shooterOff() {
        shooterPiston.set(AIR_OFF);
    }

    public void rawExtend() {
        shooterPiston.set(EXTEND);
    }

    public void rawRetract() {
        shooterPiston.set(RETRACT);
    }

    public void rawOff() {
        shooterPiston.set(AIR_OFF);
    }

}
