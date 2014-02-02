package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shooter {

    //keep in mind that i might need to refactor names
    DoubleSolenoid shooterPiston1;
    // DoubleSolenoid shooterPiston2;

    public Shooter() {
        shooterPiston1 = new DoubleSolenoid(Ports.SolenoidModule.shooterExtendChannel, Ports.SolenoidModule.shooterRetractChannel);
        //shooterPiston2 = new DoubleSolenoid(Ports.SolenoidModule.shooterExtendChannel, Ports.SolenoidModule.shooterRetractChannel);
        //may need to change channels and may need to add spike to conttrol multiple channels
    }

    public void shoot() {

        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        // shooterPiston2.set(DoubleSolenoid.Value.kForward);      
        try {
            wait(Global.waitTimeShoot);// Wait until piston completly extends
        } catch (Exception e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kReverse);
            // shooterPiston2.set(DoubleSolenoid.Value.kReverse);
            //can leave this in but remember in the future it can be in the catch itself
        }

        // Wait until piston completly retracts.
        try {
            wait(Global.timeForShooterToRetract);
        } catch (Exception e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kOff);
            // shooterPiston2.set(DoubleSolenoid.Value.kOff);
        }

    }

    void pass() {
        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        //shooterPiston2.set(DoubleSolenoid.Value.kForward);      
//  Will add code later. Should be similar to shoot.
//change the wait time for both
        try {
            wait(Global.waitTimePass);// Wait until piston completly extends
        } catch (Exception e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kReverse);
            //   shooterPiston2.set(DoubleSolenoid.Value.kReverse);
            //can leave this in but remember in the future it can be in the catch itself
        }

        try {
            wait(Global.timeForShooterToRetract);
        } catch (Exception e) {

        } finally {
            shooterPiston1.set(DoubleSolenoid.Value.kOff);
            //   shooterPiston2.set(DoubleSolenoid.Value.kOff);
        }

    }
}
