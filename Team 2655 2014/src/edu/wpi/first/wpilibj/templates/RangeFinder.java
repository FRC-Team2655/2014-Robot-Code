/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Josh
 */
public class RangeFinder {

    private final AnalogChannel rangeFinder;

    RangeFinder(int port) {
        rangeFinder = new AnalogChannel(port);
    }

    double getDistanceInches() {
        return rangeFinder.getVoltage() * Global.convertMultiplier; ///Get distance in inches.
    }

    double getDistanceFeet() {
        return getDistanceInches() / 12; //Gets distance in inches, then devides by 12. Ergo, Feet.
    }
}
