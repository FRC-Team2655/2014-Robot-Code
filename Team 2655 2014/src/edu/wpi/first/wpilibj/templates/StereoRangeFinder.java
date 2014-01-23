/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Josh
 */

//I KNOW THIS IS A LITTLE MESSY... I'LL FIX IT. --Josh
public class StereoRangeFinder {

    //Initialize Rangefinders
    RangeFinder left;
    RangeFinder right;
    StereoRangeFinder(RangeFinder leftIn, RangeFinder rightIn){
        left = leftIn;
        right = rightIn;
    }

    public int degreesOffset() {
        int degreesOffset = 0; //Fill this in with Honest John's math.
        
        return degreesOffset;
    }
}
