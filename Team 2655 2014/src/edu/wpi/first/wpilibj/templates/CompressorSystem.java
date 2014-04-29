
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
        super(Ports.pressureSwitchChannel, Ports.compressorMotorControlChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensorChannel);
    }
    public double getPressure() {
//      Y = MX + B
//      B = -21.75
//      M = 43.5
        return (Global.psiSlope * tankPressure.getVoltage() + Global.psiIntercept);
    }
}
