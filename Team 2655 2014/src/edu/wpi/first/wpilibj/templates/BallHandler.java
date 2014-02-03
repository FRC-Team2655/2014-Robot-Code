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
    private Thread catchThread;

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
            } catch(InterruptedException ex) {
                
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
            ballInMittLimitSwitch = new DigitalInput(Ports.crioSlot2, Ports.DigitalModule.ballInMittLimitSwitchChannel);
            shooterLimiterSwitch = new DigitalInput(Ports.crioSlot2, Ports.DigitalModule.shooterLimiterSwitchChannel);
            loadThread = new LoadThread();
            shootThread = new ShootTheBallThread();

            ballHandlerCompressor.start();
            loadThread.start();
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
                    Thread.sleep(100);
                } catch (InterruptedException ex) {

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
            loadState = loadStates.loading;
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
            catchState = catchStates.opening;
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
}
