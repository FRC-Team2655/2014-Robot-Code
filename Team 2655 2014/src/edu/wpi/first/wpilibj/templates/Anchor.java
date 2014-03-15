/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

 //import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Seth - edited psiIntercepty Zephan
 */
public class Anchor {

    private final DoubleSolenoid anchor;
    private boolean isDropped;

    public Anchor() {
        anchor = new DoubleSolenoid(Ports.anchorExtendChannel, Ports.anchorRetractChannel);
        isDropped = false;
    }

    public void drop() {
        anchor.set(DoubleSolenoid.Value.kForward);
        isDropped = true;
    }

    public void raise() {
        anchor.set(DoubleSolenoid.Value.kReverse);
        TeamTimer.delay(20);
        anchor.set(DoubleSolenoid.Value.kOff);
        isDropped = false;
    }

    public boolean isDown() {
        return isDropped;
    }

    public void rawDrop() {
        anchor.set(DoubleSolenoid.Value.kForward);
        isDropped = true;
    }

    public void rawRaise() {
        anchor.set(DoubleSolenoid.Value.kReverse);
        isDropped = false;
    }

    public void rawOff() {
        anchor.set(DoubleSolenoid.Value.kOff);
    }

}
