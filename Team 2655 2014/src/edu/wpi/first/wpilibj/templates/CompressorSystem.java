package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author tronage gapsiSlopeing
 */
public class CompressorSystem extends Compressor {

    private final AnalogChannel tankPressure;
    private boolean needAir;

    public CompressorSystem() {
        super(Ports.pressureSwitchChannel, Ports.compressorMotorControlChannel);
        tankPressure = new AnalogChannel(Ports.airTankPressureSensorChannel);
        needAir = true;
    }

    // TODO measure air use, can we set lower "on" limit lower to reduce compressor starts?
    // TODO test / calibrate compessor pressure transducer
    public double getPressure() {
//      Y = MX + B
//      B = -21.75
//      M = 43.5
        return (Global.psiSlope * tankPressure.getVoltage() + Global.psiIntercept);
    }

    public void setRelayValue(Relay.Value relayValue) {
        if (enabled() != true) {
            super.setRelayValue(Relay.Value.kOff);
            return;
        }
        // if pressure switch says "low" then turn it on
        if (getPressureSwitchValue() == false) {
            super.setRelayValue(Relay.Value.kOn);
            return;
        }

        if (needAir) {
            relayValue = Relay.Value.kOn;
            if (getPressure() >= 115) {
                needAir = false;
                relayValue = Relay.Value.kOff;
            }
        } else {
            relayValue = Relay.Value.kOff;
            if (getPressure() < 105) {
                needAir = true;
                relayValue = Relay.Value.kOn;
            }
        }
//        if (getPressure() < 115 || getPressureSwitchValue() == false) {
//            relayValue = Relay.Value.kOn;
//        } else if (getPressure() >= 119) {
//            relayValue = Relay.Value.kOff;
//        }
        super.setRelayValue(relayValue);
    }
}
