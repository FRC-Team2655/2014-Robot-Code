package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shooter {

    DoubleSolenoid shooterPiston1;
    DoubleSolenoid shooterPiston2;

    public Shooter() {
        shooterPiston1 = new DoubleSolenoid(HardwarePortsEnum.ShooterExtendChannel, HardwarePortsEnum.ShooterRetractChannel);
        //may need to change channels and may need to add spike to conttrol multiple channels
        shooterPiston2 = new DoubleSolenoid(HardwarePortsEnum.ShooterExtendChannel, HardwarePortsEnum.ShooterRetractChannel);
    }

    void shoot() {
        shooterPiston1.set(DoubleSolenoid.Value.kForward);
        shooterPiston2.set(DoubleSolenoid.Value.kForward);
        //after the ball is shot we might need a timer in there to make the piston extend
        //wait until piston completly extends 
        try {
            wait(100);
        } catch (Exception e) {

        } finally {
            shooterPiston2.set(DoubleSolenoid.Value.kOff);
            shooterPiston1.set(DoubleSolenoid.Value.kOff);
        }

    }

    void reset() {
        shooterPiston1.set(DoubleSolenoid.Value.kReverse);
        shooterPiston2.set(DoubleSolenoid.Value.kReverse);
        //wait until piston completly retracts wait(timeout);
        try {
            wait(100);
        } catch (Exception e) {

        } finally {
            shooterPiston2.set(DoubleSolenoid.Value.kOff);
            shooterPiston1.set(DoubleSolenoid.Value.kOff);
        }

    }

}
