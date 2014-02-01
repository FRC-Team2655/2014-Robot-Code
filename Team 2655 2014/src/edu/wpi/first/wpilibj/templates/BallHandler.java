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
    private Thread shootThread;

    boolean catchIsEnabled = false;
    int loadMode = loadStates.off;
    boolean shoot = false;

    class loadStates {

        public static final int off = 0;
        public static final int loading = 1;
        public static final int closing = 2;
        public static final int loaded = 3;

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

                        loadMode = loadStates.loaded;
                        break;

                    case loadStates.closing:
                        sideArm.close();
                        inFeed.off();

                        loadMode = loadStates.off;
                        break;

                    case loadStates.loaded:
                        if (ballInMittLimitSwitch.get() == true) {
                            loadMode = loadStates.closing;
                        }
                        break;

                    case loadStates.off:
                        break;

                    default:
                        break;

                }
                Thread.sleep(Global.loadIdleTime);
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
        shootThread = new ShootTheBallThread();
        shootThread.start();
    }

    void catchTheBall() {

        sideArm.open();

        while (ballInMittLimitSwitch.get() == false) {
        }
        sideArm.close();

    }

    void setShoot() {
        shoot = true;
    }

    void setDoNotShoot() {
        shoot = false;
    }

    private class ShootTheBallThread extends Thread {

        public ShootTheBallThread() {
        }

        public void run() {
            try {
                if (shoot) {
                    anchor.drop();
                    sideArm.open();
                    shooter.shoot();
                    sideArm.close();
                    anchor.raise();
                    setDoNotShoot();
                }
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
    }

    void shootTheBall() {
        /*       anchor.drop();
         sideArm.open();
         //need to put the a timer on the verb shoot so i can shoot 
         shooter.shoot();
         sideArm.close();
         anchor.raise();
         */
        setShoot();

    }

    void loadTheBall() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        loadMode = loadStates.loading;
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

        return (loadMode != loadStates.off);

    }

}
