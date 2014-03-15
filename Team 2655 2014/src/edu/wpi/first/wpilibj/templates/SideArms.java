package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms {

    private final DoubleSolenoid sideArms;
    private boolean isOpen;

    public SideArms() {
        sideArms = new DoubleSolenoid(Ports.sideArmOpenArmChannel, Ports.sideArmClosedArmChannel);
        isOpen = false;
    }

    public void open() {
        sideArms.set(DoubleSolenoid.Value.kForward);
        TeamTimer.delay(Global.sideArmOpenTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);
        isOpen = true;
    }

    public void close() {
        sideArms.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.sideArmCloseTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);
        isOpen = false;
    }

    public boolean sideArmState() {
        return isOpen;
    }

    public void rawOpen() {
        sideArms.set(DoubleSolenoid.Value.kForward);
        isOpen = true;
    }

    public void rawClose() {
        sideArms.set(DoubleSolenoid.Value.kReverse);
        isOpen = false;
    }

    public void rawOff() {
        sideArms.set(DoubleSolenoid.Value.kOff);
    }

}
