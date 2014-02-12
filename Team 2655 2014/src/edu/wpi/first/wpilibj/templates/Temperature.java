/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author psiSlopeagic_000
 */
public class Temperature {

    private static AnalogChannel tempSensor;

    public Temperature() {
        tempSensor = new AnalogChannel(Ports.temperatureChannel);
    }

    public int celcius() {
        return (int) ((tempSensor.getVoltage() * Global.temperatureSlopeCompensation) + Global.temperatureOffset);
    }
    
    public int fahrenheit() {
        return (celcius() * 9/5) + 32;
    }
}
