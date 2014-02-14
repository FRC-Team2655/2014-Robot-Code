/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author magic_000
 */
public class BatteryMonitor implements LiveWindowSendable {

//    private CurrentSensor crioCurrentSensor;
//    private CurrentSensor dscCurrentSensor;
//    private CurrentSensor frontRightCurrentSensor;
//    private CurrentSensor frontLeftCurrentSensor;
//    private CurrentSensor backRightCurrentSensor;
//    private CurrentSensor backLeftCurrentSensor;
//    private CurrentSensor compresorCurrentSensor;

    public BatteryMonitor() {
//        frontRightCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.frontRightMotorCurrentChannel);
//        frontLeftCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.frontLeftMotorCurrentChannel);
//        backRightCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.backRightMotorCurrentChannel);
//        backLeftCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.backLeftMotorCurrentChannel);
//        crioCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.crioCurrentChannel);
//        dscCurrentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.dscCurrentChannel);
//        currentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.compressorMotorCurrentChannel);
    }

    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
//            m_table.putNumber("FR", frontRightCurrentSensor.getCurrent());
//            m_table.putNumber("FL", frontLeftCurrentSensor.getCurrent());
//            m_table.putNumber("BR", backRightCurrentSensor.getCurrent());
//            m_table.putNumber("BL", backLeftCurrentSensor.getCurrent());
//            m_table.putNumber("CC", crioCurrentSensor.getCurrent());
//            m_table.putNumber("DC", dscCurrentSensor.getCurrent());
//            m_table.putNumber("Comp", compressorCurrentSensor.getCurrent());
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
        return "Battery Monitor";
    }

}
