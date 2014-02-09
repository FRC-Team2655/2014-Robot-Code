package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

    //keep in mind that i might need to refactor names
    private final DoubleSolenoid shooterPiston1;
    private final DoubleSolenoid shooterPiston2;
    private double m_t1, m_t2, m_t3, m_tstart, m_tend;
    private int m_x1, m_x2, m_x3;
    private double m_v1, m_v2, m_v3;

    private final Encoder shooterPosition;

    public Shooter() {
        shooterPiston1 = new DoubleSolenoid(Ports.leftShooterExtendChannel, Ports.leftShooterRetractChannel);
        shooterPiston2 = new DoubleSolenoid(Ports.rightShooterExtendChannel, Ports.rightShooterRetractChannel);

        shooterPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel);
    }

    public void pass() {
        SmartDashboard.putNumber("Passing has started", 0);
        shootPass(Global.waitTimePass);
        SmartDashboard.putNumber("Passing has finished", 0);
    }

    public void shoot() {
        SmartDashboard.putNumber("Shooter has started", 0);
        shootPass(Global.waitTimeShoot);
        SmartDashboard.putNumber("Shooter has finished", 0);
    }

    private void measureAcceleration() {

        // FPGA Time returns time in micro seconds
        m_tstart = Utility.getFPGATime();

        // f = m * a
        // need to add rotation sensor positions
        shooterPosition.start();

        m_x1 = shooterPosition.getRaw();
        m_t1 = Utility.getFPGATime();

        Timer.delay(5);
        m_x2 = shooterPosition.getRaw();
        m_t2 = Utility.getFPGATime();

        Timer.delay(5);
        m_x3 = shooterPosition.getRaw();
        m_t3 = Utility.getFPGATime();

        shooterPosition.stop();

        m_tend = Utility.getFPGATime();

    }

    private double calculateMass() {
        // m = force / acceleration of ball
        // a = (v2 - v1) / t
        // v1 can be 0
        // v = (x2 - x1) / t
        // force = pressure * area of piston(s)
        return 0.0;
    }

    private void shootPass(int extendTime) {

        // start firing
        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        shooterPiston2.set(DoubleSolenoid.Value.kForward);

        if (Global.waitTimeShoot == extendTime) {
            // shoot mode
            measureAcceleration();
            Timer.delay(extendTime - (m_tend - m_tstart));
        } else {
            // pass mode
            Timer.delay(extendTime);
        }

        // start retracting
        shooterPiston1.set(DoubleSolenoid.Value.kReverse);
        shooterPiston2.set(DoubleSolenoid.Value.kReverse);

        // Wait until piston completly retracts.
        Timer.delay(Global.timeForShooterToRetract);

        // Now stop wasting air
        shooterPiston1.set(DoubleSolenoid.Value.kOff);
        shooterPiston2.set(DoubleSolenoid.Value.kOff);

        // do a few calculations to figure weight of ball
        // useful later to calculate trajectory
        if (Global.waitTimeShoot == extendTime) {
            Global.measuredTimeInAccelerationMeasurement = (m_tend - m_tstart);
            Global.massOfBall = calculateMass();
        }
    }
}
