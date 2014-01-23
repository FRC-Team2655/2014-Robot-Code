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
public class StereoRangeFinder {

    //Initialize Rangefinders
    RangeFinder left = new RangeFinder(HardwarePortsEnum.leftRangeFinderPingPort, HardwarePortsEnum.leftRangeFinderEchoPort);
    RangeFinder right = new RangeFinder(HardwarePortsEnum.rightRangeFinderPingPort, HardwarePortsEnum.rightRangeFinderEchoPort);

    public int degreesOffset() {
        int degreesOffset = 0; //Fill this in with Honest John's math.
        
        return degreesOffset;
    }
}
