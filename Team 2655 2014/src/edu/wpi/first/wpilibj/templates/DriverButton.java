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
public class DriverButton {

    Joystick joystick;
    int buttonNumber;
    boolean lastShootButtonState;
    boolean lastModeState;

    boolean notPressed = false;
    boolean pressed = true;
    boolean enabled = true;
    boolean disabled = false;

    DriverButton(Joystick joystickIn, int buttonNumberIn) {
        joystick = joystickIn;
        buttonNumber = buttonNumberIn;
        lastShootButtonState = false;
        lastModeState = false;

    }

    void toggle() {
        if (joystick.getRawButton(buttonNumber)) {
//Is the Button Pressed?
            if (lastShootButtonState == notPressed) { //Was the last state "Not Pressed?"
                if (lastModeState == enabled) {
                    lastModeState = disabled;
                } else {
                    lastModeState = enabled;
                }
                lastShootButtonState = pressed;//set the last state to "pressed"

            } else {// So it ISN'T PRESSED!
                lastShootButtonState = notPressed;//set the last state to "not pressed"

            }
        }
    }
    boolean getLastModeState(){
        return lastModeState;
    }

}
