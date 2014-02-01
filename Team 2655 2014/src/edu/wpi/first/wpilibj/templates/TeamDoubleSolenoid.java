/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author magic_000
 */
public class TeamDoubleSolenoid {

    DigitalOutput forward;
    DigitalOutput reverse;

    public static class Value {

        public final int value;
        public static final int kOff_val = 0;
        public static final int kForward_val = 1;
        public static final int kReverse_val = 2;
        public static final Value kOff = new Value(kOff_val);
        public static final Value kForward = new Value(kForward_val);
        public static final Value kReverse = new Value(kReverse_val);

        private Value(int value) {
            this.value = value;
        }
    }

    public TeamDoubleSolenoid(int moduleNumber, int forwardChannel, int reverseChannel) {
        forward = new DigitalOutput(moduleNumber, forwardChannel);
        reverse = new DigitalOutput(moduleNumber, reverseChannel);
        forward.set(false);
        reverse.set(false);
    }

    public void set(final Value value) {
        switch (value.value) {
            case Value.kOff_val:
                forward.set(false);
                reverse.set(false);
                break;

            case Value.kForward_val:
                reverse.set(false);
                forward.set(true);
                break;

            case Value.kReverse_val:
                forward.set(false);
                reverse.set(true);
                break;
            default:
        }
    }

}
