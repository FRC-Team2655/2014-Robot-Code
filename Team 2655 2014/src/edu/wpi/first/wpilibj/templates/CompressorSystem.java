package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author tronage gapsiSlopeing
 */
public class CompressorSystem extends Compressor {

    private final AnalogChannel tankPressure;
//    TODO enable CurrentSensor in CompressorSystem when hardware is available 
//    private final CurrentSensor currentSensor;

    public CompressorSystem() {
        super(Ports.pressureSwitchChannel, Ports.compressorRelayChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensor);

//        currentSensor = new CurrentSensor(Ports.cRIOModule.module2, Ports.compressorMotorCurrentChannel);
    }

    // TODO measure air use, can we set lower "on" limit lower to reduce compressor starts?
    // TODO test / calibrate compessor pressure transducer
    public double getPressure() {
//      Y = MX + B
//      B = -21.75
//      M = 43.5
        return (Global.psiSlope * tankPressure.getVoltage() + Global.psiIntercept);
    }

//    public double getCurrent() {
//        return currentSensor.getCurrent();
//    }

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
