

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author tronage gaming
 */
public class CompressorSystem extends Compressor {
     Compressor ballHandlerCompressor;

    public CompressorSystem() {
        super(HardwarePortsEnum.pressureSwitchChannel, HardwarePortsEnum.compressorRelayChannel);
         }
    }
