package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
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

        // TODO finish shooter arm sensor code (angle, angular position, velocity, acceleration
        shooterArmPosition = new Encoder(Ports.shooterRotationAChannel, Ports.shooterRotationBChannel, Global.reverseShooterRotation, CounterBase.EncodingType.k4X);
        shooterArmPosition.setDistancePerPulse(Global.shooterRadiansPerPulse);
        shooterArmPosition.reset();
        shooterArmPosition.start(); // start at zero
    }

    public void charge() {
        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        shooterPiston2.set(DoubleSolenoid.Value.kForward);
    }

    public void retract() {
        shooterPiston1.set(DoubleSolenoid.Value.kReverse);
        shooterPiston2.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.waitTimeShoot);
    }

    public void shooterOff() {
        shooterPiston1.set(DoubleSolenoid.Value.kOff);
        shooterPiston2.set(DoubleSolenoid.Value.kOff);
    }

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

        return "Shooter";
    }
}
