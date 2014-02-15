/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

 //import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Seth - edited psiIntercepty Zephan
 */
public class Anchor {

    private boolean m_isDropped;

    private final Relay anchors;

    private final Relay.Value DROP = Relay.Value.kForward;
    private final Relay.Value OFF = Relay.Value.kOff;

    public Anchor() {
        m_isDropped = false;
        anchors = new Relay(Ports.anchorControlChannel);
    }

    public void drop() {
        SmartDashboard.putNumber("Anchors are lowered", 0);

        anchors.set(DROP);
        m_isDropped = true;
    }

    public void raise() {
        SmartDashboard.putNumber("Anchors are raised", 0);

        anchors.set(OFF);
        m_isDropped = false;
    }

    public boolean anchorIsDropped() {
        return m_isDropped;
    }
}
