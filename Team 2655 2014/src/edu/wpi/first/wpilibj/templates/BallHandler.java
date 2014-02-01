package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

// edited by Alex and Zephan
public class BallHandler {

    CompressorSystem ballHandlerCompressor;
    Shooter shooter;
    SideArms sideArm;
    Anchor anchor;
    InFeed inFeed;
    DigitalInput ballInMittLimitSwitch;
    boolean loadArmsAreExtended = true;
    boolean loadIsEnabled = true;
    boolean catchIsEnabled = false;

    public BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);
        ballHandlerCompressor.start();
    }

    void catchTheBall() {
        catchIsEnabled = true;
        loadIsEnabled = false;
        
        sideArm.open();

        while (ballInMittLimitSwitch.get() == false) {
        }
        sideArm.close();

    }

    void shootTheBall() {
        anchor.drop();
        sideArm.open();
        //need to put the a timer on the verb shoot so i can shoot 
        shooter.shoot();
        sideArm.close();
        anchor.raise();
    }

    void loadTheBall() {
        catchIsEnabled = false;
        loadIsEnabled = true;
        
        inFeed.on();
        sideArm.open();

        if (RobotTemplate.lastLoadButtonState == true) {
            inFeed.off();
            sideArm.close();
        }
    }

    void passTheBall() {
        sideArm.open();
        shooter.pass();
        sideArm.close();
    }
    
    public void catchEnable () {
    }
    
    public void catchDisable () {
    }
    
    public boolean catchIsEnabled () {
        return catchIsEnabled;
    }
    
    public void loadEnable () {
    }
    
    public void loadDisable () {
    }
    
    public boolean loadIsEnabled () {
        return loadIsEnabled;
    }
}
