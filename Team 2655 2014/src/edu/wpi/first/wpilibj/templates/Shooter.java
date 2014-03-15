package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shooter {

    //keep in mind that i might need to refactor names
    private final DoubleSolenoid shooterPiston;
//    private final Encoder shooterArmPosition;

//    private final double[] m_t = new double[10];
//    private final int[] m_x= new int[10];
//    private final double[] m_v= new double[10];
    public Shooter() {
        shooterPiston = new DoubleSolenoid(Ports.ShooterExtendChannel, Ports.ShooterRetractChannel);
        // TODO finish shooter arm sensor code (angle, angular position, velocity, acceleration
//        shooterArmPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel, Global.reverseShooterRotation, CounterBase.EncodingType.k4X);
//        shooterArmPosition.setDistancePerPulse(Global.shooterRadiansPerPulse);
//        shooterArmPosition.reset();
//        shooterArmPosition.start(); // start at zero
    }

    public void shoot() {
        shooterPiston.set(DoubleSolenoid.Value.kForward);
        TeamTimer.delay(Global.shooterShootTime);

        shooterPiston.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.shooterShootTime);

        shooterPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void retract() {// Used in reseting the robot during power on.
        shooterPiston.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(1500);
        shooterPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void pass() {
        shooterPiston.set(DoubleSolenoid.Value.kForward);
        TeamTimer.delay(Global.waitTimePass);

        shooterPiston.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.waitTimePass);

        shooterPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void shooterOff() {
        shooterPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void rawExtend() {
        shooterPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void rawRetract() {
        shooterPiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void rawOff() {
        shooterPiston.set(DoubleSolenoid.Value.kOff);
    }

}
