/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 * @author Josh
 */
public class RangeFinder {
    Ultrasonic rangeFinder;
    
    RangeFinder(){
        rangeFinder = new Ultrasonic(HardwarePortsEnum.rangeFinderPingPort, HardwarePortsEnum.rangeFinderEchoPort); //Initalize the sensor
        rangeFinder.setAutomaticMode(true); //Enable it.
    }
    
    double getDistance(){
        return rangeFinder.getRangeInches();//Get distance in inches.
    }
}
