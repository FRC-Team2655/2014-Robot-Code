package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

// edited by Alex and Zephan yay!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
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
    private Thread catchThread;
    Thread m_thread;

    boolean catchIsEnabled = false;
    int loadState = loadStates.off;
    int catchState = catchStates.off;
    boolean shoot = false;

    class loadStates {

        public static final int off = 0;
        public static final int loading = 1;
        public static final int closing = 2;
        public static final int loaded = 3;

    }

    class catchStates {

        public static final int off = 0;
        public static final int opening = 1;
        public static final int closing = 2;

    }

    private class catchThread extends Thread {

        public catchThread() {

        }

        public void run() {
            try {
                switch (catchState) {
                    case catchStates.opening:
                        sideArm.open();

                        catchState = catchStates.off;
                        break;
                    case catchStates.closing:
                        sideArm.close();

                        catchState = catchStates.off;
                        break;
                    case catchStates.off:

                        break;

                }
                Thread.sleep(Global.catchIdleTime);
            } catch (InterruptedException ex) {

            }
        }
    }

    private class LoadThread extends Thread {

        public LoadThread() {

        }

        public void run() {

            try {
                switch (loadState) {
                    case loadStates.loading:
                        sideArm.open();
                        inFeed.on();

                        loadState = loadStates.loaded;
                        break;

                    case loadStates.closing:
                        sideArm.close();
                        inFeed.off();

                        loadState = loadStates.off;
                        break;

                    case loadStates.loaded:
                        if (ballInMittLimitSwitch.get() == true) {
                            loadState = loadStates.closing;
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

    public void BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);
        shooterLimiterSwitch = new DigitalInput(Ports.DigitalModule.shooterLimiterSwitchChannel);

        loadThread = new LoadThread();
        // shootThread = new ShootTheBallCommand();
        ballHandlerCompressor.start();
        loadThread.start();
        shootThread.start();
    }

    public void catchTheBall() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch));
        m_thread.start();

    }

    public void loadTheBall() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch, inFeed));
        m_thread.start();
    }

    public void passTheBall() {
        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm));
        m_thread.start();
    }

    public Anchor getAnchor() {
        return anchor;
    }
        //-------------------------------------------------------------------------------------------//

    //-------------------------------------------------------------------------------------------//
    public void catchEnable() {
        catchState = catchStates.opening;
    }

    public void shootTheBall() {
        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor));
        m_thread.start();
    }

    public void catchDisable() {
        catchState = catchStates.closing;
    }

    public boolean catchIsEnabled() {

        return (catchState != catchStates.off);
    }

    public void loadEnable() {
        loadState = loadStates.loading;
    }

    public void loadDisable() {
        loadState = loadStates.closing;
    }

    public boolean loadIsEnabled() {

        return (loadState != loadStates.off);

    }
}
