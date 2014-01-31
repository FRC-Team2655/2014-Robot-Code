/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author Josh / Honest John
 */
public class StereoRangeFinder {

    RangeFinder left;
    RangeFinder right;

    StereoRangeFinder() {

        left = new RangeFinder(Ports.AnalogModule.leftRangeFinderPort);
        right = new RangeFinder(Ports.AnalogModule.rightRangeFinderPort);
    }

    public int degreesOffset() {
        // double tanOfAngle = (left.getDistanceInches() - right.getDistanceInches()) / Global.stereoRangeFinderSeperation;
        //double angleInRadians = OurMath.atan(tanOfAngle);
        double angleInRadians = MathUtils.atan2(left.getDistanceInches() - right.getDistanceInches(), Global.stereoRangeFinderSeperation);

        // degree = angleInRadians * 180 / PI
        return (int) java.lang.Math.toDegrees(angleInRadians);
    }

    public double getDistanceInches() {
        // returns the average distance in inches
        return (left.getDistanceInches() + right.getDistanceInches()) / 2;
    }

    public double getDistanceFeet() {
        // returns the average distance in feet
        return getDistanceInches() / 12;
    }
}
