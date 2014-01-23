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
    
    RangeFinder(int beamInPort, int beamOutPort){
        rangeFinder = new Ultrasonic(beamOutPort, beamInPort); //Initalize the sensor
        rangeFinder.setAutomaticMode(true); //Enable it.
    }
    
    double getDistanceInches(){
        return rangeFinder.getRangeInches();//Get distance in inches.
    }
    double getDistanceFeet(){
        return rangeFinder.getRangeInches() / 12; //Gets distance in inches, then devides by 12. Ergo, Feet.
    }
}
