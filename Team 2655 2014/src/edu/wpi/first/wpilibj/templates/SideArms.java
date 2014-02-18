package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms {

    private final DoubleSolenoid sideArms;

    public SideArms() {
        sideArms = new DoubleSolenoid(Ports.sideArmOpenArmChannel, Ports.sideArmClosedArmChannel);
    }

    public void open() {
        sideArms.set(DoubleSolenoid.Value.kForward);
        TeamTimer.delay(Global.sideArmOpenTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);
    }

    public void close() {
        sideArms.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.sideArmCloseTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);
    }

}
