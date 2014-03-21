package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {

    private final Solenoid shooterExtend1;
    private final Solenoid shooterExtend2;

//    private final Encoder shooterArmPosition;
    private final boolean EXTEND = true;
    private final boolean AIR_OFF = false;
    private final boolean RETRACT = true;

    public Shooter() {
        shooterExtend1 = new Solenoid(Ports.ShooterExtend1Channel);
        shooterExtend2 = new Solenoid(Ports.ShooterExtend2Channel);

        // TODO finish shooter arm sensor code (angle, angular position, velocity, acceleration
//        shooterArmPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel, Global.reverseShooterRotation, CounterBase.EncodingType.k4X);
//        shooterArmPosition.setDistancePerPulse(Global.shooterRadiansPerPulse);
//        shooterArmPosition.reset();
//        shooterArmPosition.start(); // start at zero
    }

    public void shoot() {
        shooterExtend1.set(EXTEND);

        // TODO when we get shoot encoder uncomment the next lines
        // need to figure out how many pulses is the right shoot angle
        //while (shooterArmPosition.get() < Global.wantedShooterEndAngle) {};
        // also don't need the time delay in the next line.
        TeamTimer.delay(Global.shooterShootTime);

        shooterExtend1.set(AIR_OFF); // Turns the shooter solenoid off and turns the retract on.
        shooterExtend2.set(RETRACT);

        TeamTimer.delay(Global.shooterShootTime);

        shooterExtend2.set(AIR_OFF);
    }

    public void retract() {// Used in reseting the robot during power on.
        shooterExtend1.set(AIR_OFF); // Makes sure that the extend was not open.
        shooterExtend2.set(AIR_OFF);

//        shooterRetractSolenoid.set(RETRACT);
//        TeamTimer.delay(1500);
    }

    public void pass() {
        shooterExtend1.set(EXTEND);
        TeamTimer.delay(Global.shooterPassTime);

        shooterExtend1.set(RETRACT);
        TeamTimer.delay(Global.shooterRetractTime);

        shooterExtend1.set(AIR_OFF);
    }

    public void rawExtend() {
        shooterExtend1.set(EXTEND);
        shooterExtend2.set(EXTEND);
    }

    public void rawRetract() {
        shooterExtend1.set(AIR_OFF);
        shooterExtend2.set(AIR_OFF);
    }

//    public void rawOff() {
//        shooterExtend1.set(AIR_OFF);
//        shooterExtend2.set(AIR_OFF);
//    }

}
