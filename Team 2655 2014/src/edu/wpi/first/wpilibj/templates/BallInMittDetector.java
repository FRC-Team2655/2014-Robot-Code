/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author magic_000
 */
public class BallInMittDetector {

    private final RangeFinder rangeFinder;
    //   private final DigitalInput ballInMitt;
    //  private final boolean BALLINMITT = false;

    public BallInMittDetector() {
        rangeFinder = new RangeFinder(Ports.ballInMittRangeFinderChannel);
        // ballInMitt = new DigitalInput(Ports.ballInMittChannel);
    }

    public boolean ballInMitt() {
        SmartDashboard.putNumber("RangeFinder BIM Inches", rangeFinder.getDistanceInches());
//        return ballInMitt.get() == BALLINMITT;
        return rangeFinder.getDistanceInches() < Global.wantedBallDistanceInches;
    }

}
