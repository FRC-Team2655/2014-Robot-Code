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

    Thread m_thread;

    boolean catchIsEnabled = false;
    int loadState = loadStates.off;
    int catchState = catchStates.off;
    boolean shoot = false;

//    class loadStates {
//
//        public static final int off = 0;
//        public static final int loading = 1;
//        public static final int closing = 2;
//        public static final int loaded = 3;
//
//    }
//    class catchStates {
//
//        public static final int off = 0;
//        public static final int opening = 1;
//        public static final int closing = 2;
//
//    }
    public void BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);
        shooterLimiterSwitch = new DigitalInput(Ports.DigitalModule.shooterLimiterSwitchChannel);

        // shootThread = new ShootTheBallCommand();
        ballHandlerCompressor.start();
    }

    public void catchEnable() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }

        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch, inFeed));
        m_thread.start();

//        catchState = catchStates.opening;
    }


    public void passTheBall() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm));
        m_thread.start();
    }

    public void shootTheBall() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
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

        if (ballInMittLimitSwitch.get() == true) {
            return;
        }

        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch, inFeed));
        m_thread.start();
//        loadState = loadStates.loading;
    }

    public void loadDisable() {
        loadState = loadStates.closing;
    }

    public boolean loadIsEnabled() {

        return (loadState != loadStates.off);

    }
}
