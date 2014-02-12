/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author magic_000
 */
public class TeamDoubleSolenoid {

    private final Relay forward;
    private final Relay reverse;

    public TeamDoubleSolenoid(int forwardChannel, int reverseChannel) {
        forward = new Relay(forwardChannel);
        reverse = new Relay(reverseChannel);
        forward.set(Relay.Value.kOff);
        reverse.set(Relay.Value.kOff);
    }

    public void set(final Value value) {
        switch (value.value) {
            case Value.kOff_val:
                forward.set(Relay.Value.kOff);
                reverse.set(Relay.Value.kOff);
                break;

            case Value.kForward_val:
                reverse.set(Relay.Value.kOff);
                forward.set(Relay.Value.kForward);
                break;

            case Value.kReverse_val:
                forward.set(Relay.Value.kOff);
                reverse.set(Relay.Value.kForward);
                break;
            default:
        }
    }

}
