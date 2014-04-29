/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Zephan
 */
public class LightSensor {

    private final AnalogChannel lightSensor;

    LightSensor() {
        lightSensor = new AnalogChannel(Ports.lightSensorChannel);
    }

    public boolean isGoalHot() {
        // TODO figure out how to smooth range finder distances
        return (lightSensor.getVoltage() > 2);
    }

}
