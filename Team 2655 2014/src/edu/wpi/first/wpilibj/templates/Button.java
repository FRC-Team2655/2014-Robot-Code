/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Josh
 */
public class Button {

    Joystick joystick;
    int buttonNumber;
    boolean lastShootButtonState;
    boolean lastModeState;

    boolean notPressed = false;
    boolean pressed = true;
    boolean enabled = true;
    boolean disabled = false;

    Button(Joystick joystickIn, int buttonNumberIn) {
        joystick = joystickIn;
        buttonNumber = buttonNumberIn;
        lastShootButtonState = false;
        lastModeState = false;

    }

    boolean ToggleCheck() {
        if (joystick.getRawButton(buttonNumber)) {
                if (lastModeState == enabled) {
                    lastModeState = disabled;
                } else {
                    lastModeState = enabled;
            }
        }
        return lastModeState;
    }
}
