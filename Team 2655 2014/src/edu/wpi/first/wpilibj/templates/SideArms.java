package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideArms {

    private final DoubleSolenoid sideArms;

    public SideArms() {
        sideArms = new DoubleSolenoid(Ports.sideArmOpenArmChannel, Ports.sideArmClosedArmChannel);
    }

    public void open() {

        SmartDashboard.putNumber("Sidearms open", 0);

        sideArms.set(DoubleSolenoid.Value.kForward);
        Timer.delay(Global.sideArmOpenTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);

    }

    public void close() {

        SmartDashboard.putNumber("Sidearms close", 0);

        sideArms.set(DoubleSolenoid.Value.kReverse);
        Timer.delay(Global.sideArmCloseTime); //wait for the arm to open
        sideArms.set(DoubleSolenoid.Value.kOff);

    }

}
