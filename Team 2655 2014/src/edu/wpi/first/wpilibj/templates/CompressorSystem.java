package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author tronage gapsiSlopeing
 */
public class CompressorSystem extends Compressor {

    private final AnalogChannel tankPressure;

    public CompressorSystem() {
        super(Ports.pressureSwitchChannel, Ports.compressorRelayChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensor);
    }

    public double getPressure() {
//      Y = MX + B
//      B = -27.75
//      M = 43.5
        return (Global.psiSlope * tankPressure.getVoltage() - Global.psiIntercept);
    }
}
