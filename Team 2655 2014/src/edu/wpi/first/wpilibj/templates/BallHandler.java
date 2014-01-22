/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Zephan edited by Alex
 */
public class BallHandler {

    Compressor compressor;

    Shooter shooter;
    SideArms sideArm;
    Anchor anchor;
    InFeed loadArms;
    DigitalInput ballInMittLimitSwitch;
    private final boolean InMitt = true;
    private final boolean notInMitt = false;
    boolean loadArmsAreExtended = true;

    public BallHandler() {
        compressor = new Compressor(HardwarePortsEnum.pressureSwitchChannel, HardwarePortsEnum.compressorRelayChannel);

        shooter = new Shooter();
        sideArm = new SideArms(HardwarePortsEnum.sideArmOpenArmChannel, HardwarePortsEnum.sideArmCloseArmChannel);
        anchor = new Anchor(HardwarePortsEnum.anchorDropChannel, HardwarePortsEnum.anchorRaiseChannel);
        loadArms = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(1);
        compressor.start();
    }

    void armTheShooter() {
        sideArm.open();
        anchor.drop();

    }

    void shootTheBall() {
        sideArm.open();
        shooter.shoot();
        sideArm.close();
        anchor.raise();
    }

    void loadTheBall() {

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
        //can try a pwm 
        //or turning it on only for a short amount of time
        //put wait statement in here

    }
}
