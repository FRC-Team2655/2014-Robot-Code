package edu.wpi.first.wpilibj.templates;
// Author Alex Senneville

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shooter {

    DoubleSolenoid shooterSolenoid;

    public Shooter() {
        shooterSolenoid = new DoubleSolenoid(HardwarePortsEnum.shooterExtendChannel, HardwarePortsEnum.shooterRetractChannel);
        //may need to change channels and may need to add spike to conttrol multiple channels
    }

    void shoot() {
       
        shooterSolenoid.set(DoubleSolenoid.Value.kForward);
        //after the ball is shot we might need a timer in there to make the piston extend
      
        // Wait until piston completly extends 
        try {
            wait(100);
        } catch (Exception e) {

        } finally {
             shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
        }

        // Wait until piston completly retracts.
        try {
            wait(100);
        } catch (Exception e) {

        } finally {
             shooterSolenoid.set(DoubleSolenoid.Value.kOff);
       }

    }

   }

