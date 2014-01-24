/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Josh / Honest John
 */
public class StereoRangeFinder {

    RangeFinder left;
    RangeFinder right;

    StereoRangeFinder() {

        left = new RangeFinder(HardwarePortsEnum.leftRangeFinderEchoPort, HardwarePortsEnum.leftRangeFinderPingPort);
        right = new RangeFinder(HardwarePortsEnum.rightRangeFinderEchoPort, HardwarePortsEnum.rightRangeFinderPingPort);
    }

    public int degreesOffset() {
        double tanOfAngle = (left.getDistanceInches() - right.getDistanceInches()) / globalVariables.stereoRangeFinderSeperation;

        return Math.atan(tanOfAngle);
    }

    public double getDistanceInches() {
        // returns the average distance in inches
        return (left.getDistanceInches() - right.getDistanceInches()) / 2;
    }

    public double getDistanceFeet() {
        // returns the average distance in feet
        return getDistanceInches() / 12;
    }
}
