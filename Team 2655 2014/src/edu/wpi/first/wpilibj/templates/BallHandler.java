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
    DigitalInput shooterLimiterSwitch;

    private Thread loadThread;

    boolean loadArmsAreExtended = true;
    boolean loadIsEnabled = true;
    boolean catchIsEnabled = false;
    int loadMode = loadStates.off;

    class loadStates {

        public static final int off = 0;
        public static final int loading = 1;
        public static final int closing = 2;

    }

    private class LoadThread extends Thread {

        public LoadThread() {

        }

        public void run() {

            try {
                switch (loadMode) {
                    case loadStates.loading:
                        sideArm.open();
                        inFeed.on();
                        break;
                    case loadStates.closing:
                        sideArm.close();
                        inFeed.off();
                        break;
                    case loadStates.off:
                        break;
                    default:
                        break;

                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }

        }
    }

    public BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);
        shooterLimiterSwitch = new DigitalInput(Ports.DigitalModule.shooterLimiterSwitchChannel);
        ballHandlerCompressor.start();
        loadThread = new LoadThread();
        loadThread.start();
    }

    void catchTheBall() {

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

        loadMode = loadStates.loading;

        if (RobotTemplate.lastLoadButtonState == true) {

            loadMode = loadStates.closing;

        }
    }

    void passTheBall() {
        sideArm.open();
        shooter.pass();
        sideArm.close();
    }

    public Anchor getAnchor() {
        return anchor;
    }
    //-------------------------------------------------------------------------------------------//

    public void catchEnable() {

    }

    public void catchDisable() {

    }

    public boolean catchIsEnabled() {

        return catchIsEnabled;
    }

    public void loadEnable() {

    }

    public void loadDisable() {

    }

    public boolean loadIsEnabled() {

        return loadIsEnabled;
    }
}
