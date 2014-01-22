/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Seth - edited by Zephan
 */
public class Anchor {

    DoubleSolenoid anchors;

    public Anchor(int dropChannel, int raiseChannel) {

        anchors = new DoubleSolenoid(dropChannel, raiseChannel);
//      anchors.set(DoubleSolenoid.Value.kOff); - checking if anchors are off?

    }

    void drop() {

        anchors.set(DoubleSolenoid.Value.kForward);
        //Find if we need to stop the solenoid when it reaches the bottom position.

    }

    void raise() {

        anchors.set(DoubleSolenoid.Value.kReverse);
        //Find if we need to stop the solenoid when it reaches th top position.
        
    }
}
