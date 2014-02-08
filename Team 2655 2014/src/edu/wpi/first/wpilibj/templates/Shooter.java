package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

    //keep in mind that i might need to refactor names
    private final DoubleSolenoid shooterPiston1;
    private final DoubleSolenoid shooterPiston2;

    public Shooter() {
        shooterPiston1 = new DoubleSolenoid(Ports.leftShooterExtendChannel, Ports.leftShooterRetractChannel1);
        shooterPiston2 = new DoubleSolenoid(Ports.rightShooterExtendChannel, Ports.rightShooterRetractChannel);
        //may need to change channels and may need to add spike to conttrol multiple channels
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

    private void shootPass(int extendTime) {

        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        shooterPiston2.set(DoubleSolenoid.Value.kForward);
        try {
            Thread.sleep(extendTime);// Wait until piston completly extends
        } catch (InterruptedException e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kReverse);
            shooterPiston2.set(DoubleSolenoid.Value.kReverse);
            //can leave this in but remember in the future it can be in the catch itself
        }

        // Wait until piston completly retracts.
        try {
            Thread.sleep(Global.timeForShooterToRetract);
        } catch (InterruptedException e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kOff);
            shooterPiston2.set(DoubleSolenoid.Value.kOff);

        }
    }
}
