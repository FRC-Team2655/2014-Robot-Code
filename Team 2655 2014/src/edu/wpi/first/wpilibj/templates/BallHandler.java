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
    Solenoid anchor;
    LoadArms loadArms;
    DigitalInput ballInMittLimitSwitch;
    private final boolean InMitt = true;
    boolean loadArmsAreExtended = true;
    
    public BallHandler () {
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        
        shooter = new Solenoid(1, 1, 2, 100);
        sideArm = new Solenoid(1, 1, 2, 100);
        anchor = new Solenoid(1, 1, 2, 100);
        loadArms = new LoadArms();
        ballInMittLimitSwitch = new DigitalInput(1);
    }
    void armTheShooter() {
        sideArm.extend();
        anchor.extend();
        
        
    }
    void shootTheBall() {
        shooter.extend();
        shooter.retract();
        sideArm.retract();
        anchor.retract();
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
        //the side arm extending is making the arms open or flower.
        
                       
    }
    void passTheBall() {
        sideArm.extend();
        
        
    }
}
