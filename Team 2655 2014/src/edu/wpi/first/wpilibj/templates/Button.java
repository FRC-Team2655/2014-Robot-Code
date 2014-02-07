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

    private final Joystick joystick;
    private final int buttonNumber;
    private boolean lastButtonState;

    Button(Joystick joystickIn, int buttonNumberIn) {
        joystick = joystickIn;
        buttonNumber = buttonNumberIn;
        lastButtonState = false;
    }

    //The rising edge
    boolean theButtonToggled() {
        
        if (joystick.getRawButton(buttonNumber)) {
            if (lastButtonState == false) {
                lastButtonState = true;
                return true;
            } else {
                return false;
            }
        } else {
            lastButtonState = false;
            return false;
        }

    }
    
    boolean isPressed(){
        return joystick.getRawButton(buttonNumber);
    }
}
