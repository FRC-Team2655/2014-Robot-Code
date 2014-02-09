/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

 //import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Seth - edited by Zephan
 */
public class Anchor {

    private boolean m_isDropped;
    private final DoubleSolenoid anchors;

    private final DoubleSolenoid.Value DROP = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value RAISE = DoubleSolenoid.Value.kReverse;
    private final DoubleSolenoid.Value OFF = DoubleSolenoid.Value.kOff;

//  You will most likely need a timer.
    public Anchor() {
        m_isDropped = false;
        anchors = new DoubleSolenoid(Ports.anchorDropChannel, Ports.anchorRaiseChannel);
    }

    public void drop() {
        SmartDashboard.putNumber("Anchors are lowered", 0);

        anchors.set(DROP);
        m_isDropped = true;
    }

    public void raise() {
        SmartDashboard.putNumber("Anchors are raised", 0);

        anchors.set(RAISE);
        
        Timer.delay(Global.anchorRaiseTime);

        anchors.set(OFF);
        m_isDropped = false;
    }

    public boolean anchorIsDropped() {
        return m_isDropped;
    }
}
