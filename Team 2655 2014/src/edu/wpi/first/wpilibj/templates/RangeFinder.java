/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Josh
 */
public class RangeFinder implements LiveWindowSendable {

    private final AnalogChannel rangeFinder;

    RangeFinder(int port) {
        rangeFinder = new AnalogChannel(port);
    }

    double getDistanceInches() {
        // TODO figure out how to smooth range finder distances
        return rangeFinder.getVoltage() * Global.convertMultiplier; ///Get distance in inches.
    }

    double getDistanceFeet() {
        return getDistanceInches() / 12; //Gets distance in inches, then devides by 12. Ergo, Feet.
    }
    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
            m_table.putNumber("Range (in)", getDistanceInches());
        }
    }

    public void startLiveWindowMode() { /* empty */ }

    public void stopLiveWindowMode() { /* empty */ }

    public void initTable(ITable arg0) {
        m_table = arg0;
        updateTable();
    }

    public ITable getTable() {
        return m_table;
    }

    public String getSmartDashboardType() {
        return "RangeFinder";
    }
}
