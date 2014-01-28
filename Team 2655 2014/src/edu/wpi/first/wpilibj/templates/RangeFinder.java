/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 * @author Josh
 */
public class RangeFinder {
    //Ultrasonic rangeFinder;
    AnalogChannel rangeFinder;
    double voltagePerInch = 512;
    
    RangeFinder(int port){
        rangeFinder = new AnalogChannel(port);
    }
    
    double getDistanceInches(){
        return rangeFinder.getVoltage() / voltagePerInch; ///Get distance in inches.
    }
    double getDistanceFeet(){
        return getDistanceInches() / 12; //Gets distance in inches, then devides by 12. Ergo, Feet.
    }
}
