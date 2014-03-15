/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author magic_000
 */
public class CurrentSensor {

    private final AnalogChannel iSensor;

    public CurrentSensor(int module, int port) {
        iSensor = new AnalogChannel(module, port);

    }

    public CurrentSensor(int port) {
        iSensor = new AnalogChannel(Ports.cRIOModule.module1, port);
    }

    public double getCurrent() {
        // y = mx + b
        // m = 1/68mv/A @ vcc=5v
        // b = ?
        return iSensor.getVoltage() * Global.mvPerAmp + Global.mvPerAmpIntercept;
    }
}
