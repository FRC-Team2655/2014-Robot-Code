package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author tronage gaming
 */
public class CompressorSystem extends Compressor {

    private Compressor ballHandlerCompressor;

    public CompressorSystem() {
        super(Ports.AnalogModule.pressureSwitchChannel, Ports.DigitalModule.compressorRelayChannel);
    }
}
