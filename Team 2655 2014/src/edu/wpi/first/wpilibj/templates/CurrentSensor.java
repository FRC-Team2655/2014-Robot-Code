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
 * @author magic_000
 */
public class CurrentSensor implements LiveWindowSendable {

    private final AnalogChannel iSensor;

    public CurrentSensor(int module, int port) {
        iSensor = new AnalogChannel(module, port);

    }

    public CurrentSensor(int port) {
        iSensor = new AnalogChannel(Ports.cRIOModule.module1, port);
    }

    public double getCurrent() {
        // y = mx + b
        // m = 1/68mv/A @ vcc=5v
        // b = ?
        return iSensor.getVoltage() * Global.mvPerAmp + Global.mvPerAmpIntercept;
    }

    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
            m_table.putNumber("Amps", getCurrent());
        }
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }

    public void initTable(ITable arg0) {
        m_table = arg0;
        updateTable();
    }

    public ITable getTable() {
        return m_table;
    }

    public String getSmartDashboardType() {
        return "CurrentSensor";
    }
}
