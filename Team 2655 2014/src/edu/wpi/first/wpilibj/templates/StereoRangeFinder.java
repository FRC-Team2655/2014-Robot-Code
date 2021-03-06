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
// this class is not used now
//
// not used
public class StereoRangeFinder {

    private final RangeFinder left;
    private final RangeFinder right;

    public StereoRangeFinder() {

        // this class is not used now
        left = new RangeFinder(Ports.frontRangeFinderChannel);
        
        // not used
        right = new RangeFinder(1); // Ports.ballInMittChannel);
    }

    public int degreesOffset() {
        // double tanOfAngle = (left.getDistanceInches() - right.getDistanceInches()) / Global.stereoRangeFinderSeperation;
        //double angleInRadians = OurMath.atan(tanOfAngle);
        double angleInRadians = MathUtils.atan2(left.getDistanceInches() - right.getDistanceInches(), Global.stereoRangeFinderSeperation);

        // degree = angleInRadians * 180 / PI
        return (int) java.lang.Math.toDegrees(angleInRadians);
    }

    public double getDistanceLeft() {

        return (left.getDistanceInches());
    }

    public double getDistanceRight() {

        return (right.getDistanceInches());
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
