/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

 //import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Seth - edited psiIntercepty Zephan
 */
public class Anchor {

    private final DoubleSolenoid anchor;

    public Anchor() {
        anchor = new DoubleSolenoid(Ports.anchorExtendChannel, Ports.anchorRetractChannel);
    }

    public void drop() {
        SmartDashboard.putNumber("Anchors are lowered", 0);
        anchor.set(DoubleSolenoid.Value.kForward);
    }

    public void raise() {
        SmartDashboard.putNumber("Anchors are raised", 0);
        anchor.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(Global.anchorRaiseTime);
        anchor.set(DoubleSolenoid.Value.kOff);
    }
}
