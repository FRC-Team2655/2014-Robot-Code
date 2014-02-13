/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author magic_000
 */
public class BallInMittDetector implements LiveWindowSendable {

    private final RangeFinder rangeFinder;
    private final DigitalInput ballInMitt;
    private final boolean BALLINMITT = false;

    public BallInMittDetector() {
        rangeFinder = new RangeFinder(Ports.ballInMittAChannel);
        ballInMitt = new DigitalInput(Ports.ballInMittChannel);
    }

    public boolean ballInMitt() {
        return ballInMitt.get() == BALLINMITT
                || rangeFinder.getDistanceInches() < Global.wantedBallDistance;
    }

    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
            m_table.putBoolean("Ball In Mitt is", ballInMitt());
            m_table.putBoolean("Ball In Mitt DIO", ballInMitt.get());
            m_table.putNumber("Ball In Mitt rangefinder (in)", rangeFinder.getDistanceInches());
        }
    }

    public void startLiveWindowMode() { /* nothing to do here */ }

    public void stopLiveWindowMode() { /* nothing to do here */ }

    public void initTable(ITable subtable) {
        m_table = subtable;
        updateTable();
    }

    public ITable getTable() {
        return m_table;
    }

    public String getSmartDashboardType() {
        return "BallInMittDetector";
    }
}
