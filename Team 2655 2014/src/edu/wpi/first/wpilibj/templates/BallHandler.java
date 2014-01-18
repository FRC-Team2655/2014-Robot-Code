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
 * @author Zephan
 * edited by Alex
 */
public class BallHandler {
    Compressor compressor;
    public  final int pressureSwitchChannel = 1;
    public final int compressorRelayChannel = 2;
    
    Shooter shooter;
    SideArm sideArm;
    Anchor anchor;
    InFeed loadArms;
    DigitalInput ballInMittLimitSwitch;
    private final boolean InMitt = true;
    boolean loadArmsAreExtended = true;
    private final boolean notInMitt = false;
    
    public BallHandler () {
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        
        shooter = new Shooter();
        sideArm = new SideArm(1, 2);
        anchor = new Anchor(1, 2);
        loadArms = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(1);
        compressor.start();
    }

    
    void armTheShooter() {
        sideArm.open();
        anchor.drop();
        
        
    }
    void shootTheBall() {
        shooter.shoot();
        shooter.reload();
        sideArm.close();
        anchor.raise();
    }
    
    void loadTheBall() {
        if (loadArmsAreExtended == true){
            loadArms.raise();
        }
        if (ballInMittLimitSwitch.get() != InMitt){
            loadArms.lower();
            loadArms.raise();
        }
    }
    
    void catchTheBall() {
        sideArm.open();
        if (ballInMittLimitSwitch.get() == notInMitt ){
            sideArm.close();
        }
        //the side arm extending is making the arms open or flower.
        
                       
    }
    void passTheBall() {
        sideArm.open();
        //can try a pwm 
        //or turning it on only for a short amount of time
        //put wait statement in here
                
        
    }
}
