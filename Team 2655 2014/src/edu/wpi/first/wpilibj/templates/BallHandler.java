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
    
    Solenoid shooter;
    Solenoid sideArm;
    Anchor anchor;
    InFeed loadArms;
    DigitalInput ballInMittLimitSwitch;
    private final boolean InMitt = true;
    boolean loadArmsAreExtended = true;
    private final boolean notInMitt = false;
    
    public BallHandler () {
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        
        shooter = new Solenoid(1, 1, 2, 100);
        sideArm = new Solenoid(1, 1, 2, 100);
        anchor = new Anchor(1, 2);
        loadArms = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(1);
        compressor.start();
    }
    void armTheShooter() {
        sideArm.extend();
        anchor.drop();
        
        
    }
    void shootTheBall() {
        shooter.extend();
        shooter.retract();
        sideArm.retract();
        anchor.raise();
    }
    
    void loadTheBall() {
        if (loadArmsAreExtended == true){
            loadArms.retract();
        }
        if (ballInMittLimitSwitch.get() != InMitt){
            loadArms.extend();
            loadArms.retract();
        }
    }
    
    void catchTheBall() {
        sideArm.extend();
        if (ballInMittLimitSwitch.get() == notInMitt ){
            sideArm.retract();
        }
        //the side arm extending is making the arms open or flower.
        
                       
    }
    void passTheBall() {
        sideArm.extend();
        wait(1000);
        //can try a pwm 
        //or turning it on only for a short amount of time
        //put wait statement in here
                
        
    }
}
