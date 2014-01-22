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
         
        try {
            wait(100);// Wait until piston completly extends
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
    
    void pass(){
//  Will add code later. Should be similar to shoot.
    
    }
}

