package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

// edited by Alex and Zephan
public class BallHandler {

    CompressorSystem ballHandlerCompressor;
    Shooter shooter;
    SideArms sideArm;
    Anchor anchor;
    InFeed inFeed;
    DigitalInput ballInMittLimitSwitch;
    private final boolean inMitt = true;
    boolean loadArmsAreExtended = true;

    public BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(HardwarePortsEnum.ballInMittLimitSwitchChannel );
        ballHandlerCompressor.start();
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
