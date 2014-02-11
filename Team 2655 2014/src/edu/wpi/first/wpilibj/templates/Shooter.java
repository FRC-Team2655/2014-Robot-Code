package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

    //keep in mind that i might need to refactor names
    private final DoubleSolenoid shooterPiston1;
    private final DoubleSolenoid shooterPiston2;
    private double m_t1, m_t2, m_t3;
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

    // return value is how much time we took
    // in micro seconds
    private double measureAcceleration() {

        // FPGA Time returns time in micro seconds
        long start = Utility.getFPGATime();

        // f = m * a
        // need to add rotation sensor positions
        shooterPosition.start();
        shooterPosition.reset();

        m_x1 = shooterPosition.getRaw();
        m_t1 = Utility.getFPGATime();

        TeamTimer.delay(5);
        m_x2 = shooterPosition.getRaw();
        m_t2 = Utility.getFPGATime();

        TeamTimer.delay(5);
        m_x3 = shooterPosition.getRaw();
        m_t3 = Utility.getFPGATime();

        shooterPosition.stop();

        // return time in milliseconds
        return (Utility.getFPGATime() - start) * 1000;

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
//        double xTime = 0;
//        if ( extendTime == Global.waitTimeShoot) {
//            // shoot mode
//
//            xTime = measureAcceleration();
//
//            Timer.delay(extendTime - xTime);
//        } else
        {

            TeamTimer.delay(extendTime);
        }

        // start retracting
        shooterPiston1.set(DoubleSolenoid.Value.kReverse);
        shooterPiston2.set(DoubleSolenoid.Value.kReverse);

        // Wait until piston completly retracts.
        TeamTimer.delay(Global.timeForShooterToRetract);

        // Now stop wasting air
        shooterPiston1.set(DoubleSolenoid.Value.kOff);
        shooterPiston2.set(DoubleSolenoid.Value.kOff);

        // do a few calculations to figure weight of ball
        // useful later to calculate trajectory
//        if (extendTime == Global.waitTimeShoot) {
//            Global.measuredTimeInAccelerationMeasurement = xTime;
//            Global.massOfBall = calculateMass();
//        }
    }
}
