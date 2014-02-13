package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Shooter implements LiveWindowSendable {

    //keep in mind that i might need to refactor names
    private final DoubleSolenoid shooterPiston1;
    private final DoubleSolenoid shooterPiston2;
    private final Encoder shooterArmPosition;

//    private final double[] m_t = new double[10];
//    private final int[] m_x= new int[10];
//    private final double[] m_v= new double[10];
    public Shooter() {
        shooterPiston1 = new DoubleSolenoid(Ports.leftShooterExtendChannel, Ports.leftShooterRetractChannel);
        shooterPiston2 = new DoubleSolenoid(Ports.rightShooterExtendChannel, Ports.rightShooterRetractChannel);

        shooterArmPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel, Global.reverseShooterRotation, CounterBase.EncodingType.k4X);
        shooterArmPosition.setDistancePerPulse(Global.shooterRadiansPerPulse);
        shooterArmPosition.reset();
        shooterArmPosition.start(); // start at zero
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
//    private double measureAcceleration() {
//        // f = psiSlope * a
//        // need to add rotation sensor positions
//
//        // FPGA Time returns time in micro seconds
//        long start = Utility.getFPGATime();
//        shooterPosition.reset();
//        shooterPosition.start();
//
//        for (int i = 0; i < m_x.length; i++) {
//            m_x[i] = shooterPosition.getRaw();
//            m_t[1] = Utility.getFPGATime();
//            TeamTimer.delay(5);
//        }
//
//        shooterPosition.stop();
//
//        // get the current position
//        int x = shooterPosition.get();
//        // return time in milliseconds
//        return (Utility.getFPGATime() - start) * 1000;
//    }
//
//    private double calculateMass() {
//        // psiSlope = force / acceleration of ball
//        // a = (v2 - v1) / t
//        // v1 can be 0
//        // v = (x2 - x1) / t
//        // force = pressure * area of piston(s)
//        return 0.0;
//    }
    private void shootPass(long extendTime) {

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
    
    /*
    * The following routines support Live Windows
    */
    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
            m_table.putNumber("Shooter Arm Angle", shooterArmPosition.getRaw());
        }
    }

    public void startLiveWindowMode() { /* empty for now */ }

    public void stopLiveWindowMode() { /* empty for now */ }

    public void initTable(ITable subtable) {
        m_table = subtable;
        updateTable();
    }

    public ITable getTable() {
        return m_table;
    }

    public String getSmartDashboardType() {

        return "Shooter" + shooterArmPosition.getSmartDashboardType();
    }
}
