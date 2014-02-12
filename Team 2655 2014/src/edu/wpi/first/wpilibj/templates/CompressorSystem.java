package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author tronage gaming
 */
public class CompressorSystem extends Compressor {

    public AnalogChannel tankPressure;

    public CompressorSystem() {
        super(Ports.pressureSwitchChannel, Ports.compressorRelayChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensor);
    }
}
