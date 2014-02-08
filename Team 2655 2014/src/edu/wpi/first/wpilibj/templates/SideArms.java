package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideArms {

    private final DoubleSolenoid sideArms;

    public SideArms() {
        sideArms = new DoubleSolenoid(Ports.sideArmOpenArmChannel, Ports.sideArmClosedArmChannel); 
    }
//  Opens the sidearms.

    public void open() {

        SmartDashboard.putNumber("Sidearms open", 0);

        sideArms.set(DoubleSolenoid.Value.kForward);
        try {
            Thread.sleep(Global.sideArmOpenTime); //wait for the arm to open
        } catch (InterruptedException e) {

        }
        sideArms.set(DoubleSolenoid.Value.kOff);
        //can leave this in but remember in the future it can be in the catch itself
    }

//  Closes the sidearms.  
    public void close() {

        SmartDashboard.putNumber("Sidearms close", 0);

        sideArms.set(DoubleSolenoid.Value.kReverse);
        try {
            Thread.sleep(Global.sideArmCloseTime); //wait for the arm to open
        } catch (InterruptedException e) {

        }
        sideArms.set(DoubleSolenoid.Value.kOff);

    }

}
