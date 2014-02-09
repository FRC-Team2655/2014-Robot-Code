/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author magic_000
 */
public class BallInMittDetector {

    private final RangeFinder rangeFinder;

    public BallInMittDetector() {
        rangeFinder = new RangeFinder(Ports.BallInMittChannel);
    }

    public boolean ballInMitt() {
        return rangeFinder.getDistanceInches() < Global.wantedBallDistance;
    }
}
