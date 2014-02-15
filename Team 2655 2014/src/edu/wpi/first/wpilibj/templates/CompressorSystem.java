package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author tronage gapsiSlopeing
 */
public class CompressorSystem extends Compressor {

    private final AnalogChannel tankPressure;

    public CompressorSystem() {
        super(Ports.pressureSwitchChannel, Ports.compressorMotorControlChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensorChannel);
        LiveWindow.addSensor("CompressorSystem","tankPresssure" , tankPressure);
    }

    // TODO measure air use, can we set lower "on" limit lower to reduce compressor starts?
    // TODO test / calibrate compessor pressure transducer
    public double getPressure() {
//      Y = MX + B
//      B = -21.75
//      M = 43.5
        return (Global.psiSlope * tankPressure.getVoltage() + Global.psiIntercept);
    }

    private ITable m_table;

    public void updateTable() {
        if (m_table != null) {
            m_table.putNumber("Pressure", getPressure());
//            m_table.putNumber("Current", getCurrent());
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
        return "CompressorSystem";
    }
}
