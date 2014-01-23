
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
    private final boolean InMitt = true;
    private final boolean notInMitt = false;
    boolean loadArmsAreExtended = true;

    public BallHandler() {
        compressor = new Compressor(HardwarePortsEnum.pressureSwitchChannel, HardwarePortsEnum.compressorRelayChannel);

        shooter = new Shooter();
        sideArm = new SideArms(HardwarePortsEnum.sideArmOpenArmChannel, HardwarePortsEnum.sideArmCloseArmChannel);
        anchor = new Anchor(HardwarePortsEnum.anchorDropChannel, HardwarePortsEnum.anchorRaiseChannel);
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(1);
        compressor.start();
    }

    void armTheShooter() {
        sideArm.open();
        anchor.drop();
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

    void catchTheBall() {

        if (ballInMittLimitSwitch.get() != InMitt) {
            sideArm.open();
        }

        if (ballInMittLimitSwitch.get() == InMitt) {
            sideArm.close();
        } //the side arm extending is making the arms open or flower.
    }

    void passTheBall() {
        sideArm.open();
        shooter.pass();
        //add timer to pass verb 
        sideArm.close();       
        //can try a pwm 
        //or turning it on only for a short amount of time
        //put wait statement in here
    }
}
