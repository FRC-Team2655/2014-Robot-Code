package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

// edited by Alex
public class BallHandler {

    Compressor compressor;

    Shooter shooter;
    SideArms sideArm;
    Anchor anchor;
    InFeed inFeed;
    DigitalInput ballInMittLimitSwitch;
    private final boolean inMitt = true;
    private final boolean notInMitt = false;
    boolean loadArmsAreExtended = true;

    public BallHandler() {
        compressor = new Compressor(HardwarePortsEnum.pressureSwitchChannel, HardwarePortsEnum.compressorRelayChannel);

        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor(HardwarePortsEnum.anchorDropChannel, HardwarePortsEnum.anchorRaiseChannel);
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(1);
        compressor.start();
    }

    void shootTheBall() {
        sideArm.open();
        //need to put the a timer on the verb open so i can shoot 
        shooter.shoot();
        sideArm.close();
        anchor.raise();
    }

    void loadTheBall() {

        if (RobotTemplate.lastLoadButtonState == true) {
            inFeed.off();
        } else {
            inFeed.on();
        }
    }

    void openSideArmsForCatching() {
        sideArm.open();
        if (ballInMittLimitSwitch.get() == inMitt) {
            sideArm.close();
        }
    }
    
     void closeSideArmsForCatching() {
        sideArm.close();
    }    
        
     void passTheBall() {
        sideArm.open();
        shooter.pass();
        sideArm.close();
       
    }
}
