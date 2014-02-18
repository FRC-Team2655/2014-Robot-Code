/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author magic_000
 */
public class TeamJoystick extends Joystick {

    public TeamJoystick(final int port) {
        super(port);
    }
    
    // change this one to false to disable this feature
    private final boolean DESENSITIZE_ENABLED = true;

    // change these to increase/decrease sensitivity
    private final double twistSensitiviy = 1;
    private final double xSensitiviy = 1;
    private final double ySensitiviy = 1;

    // change these to increase/decrease the deadzone
    // right now its set to about 1/10 th dead zone.
    // assuming that zone = -1 to + 1
    private final double MINZONE = -0.1;
    private final double MAXZONE = 0.1;

    private double filterDeadZone(double v) {
        if (v > MAXZONE) return v - MAXZONE;
        if (v < MINZONE) return v - MINZONE;
        return 0.0;
    }
    public double getTwist() {
        double m = super.getThrottle();  // super.getTwist();
        SmartDashboard.putNumber("get twist / throttle -- Over ride works", m);
        if (DESENSITIZE_ENABLED) return m;
        return filterDeadZone(m) * twistSensitiviy;
    }
    public double getThrottle() {
        double m = super.getThrottle();
        if (DESENSITIZE_ENABLED) return m;
        return filterDeadZone(m) * twistSensitiviy;
    }
    public double getX(Hand hand) {
        double m = super.getX(hand);
        if (DESENSITIZE_ENABLED) return m;
        return filterDeadZone(m) * xSensitiviy;
    }
    public double getY(Hand hand) {
        double m = super.getY(hand);
        if (DESENSITIZE_ENABLED) return m;
        return filterDeadZone(m) * ySensitiviy;
    }
}
