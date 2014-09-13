package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// edited by Alex and Zephan yay!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
public class BallHandler {

    private final CompressorSystem ballHandlerCompressor;
    private final Shooter shooter;
    private final SideArms sideArm;
    private final Anchor anchor;
    private final InFeed inFeed;
    private final BallInMittDetector ballInMittDetector;

    private Thread m_thread;

    private int airTankRefreshWait = 0;
    private int threadFailTimes;
    private boolean shootingAirPressure;

    public BallHandler() {

        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        m_thread = new Thread();
        ballInMittDetector = new BallInMittDetector();
        threadFailTimes = 0;
        shootingAirPressure = false;

        ballHandlerCompressor.start();

    }

    public void reset() {
        raiseAnchor();
        shooter.retract();
        inFeed.liftArms();
        closeSideArms();
    }

    public void passTheBall() {

//        if (!ballInMittDetector.ballInMitt()) {
//            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);
//            return;
//        }
        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, inFeed));
        m_thread.start();
    }

    public void shootTheBall() {
//        if (!ballInMittDetector.ballInMitt()) {
//            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);
//
//            return;
//        }

        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor, inFeed));

        m_thread.start();
    }

    public void loadEnable() {
//        if (ballInMittDetector.ballInMitt()) {
//            SmartDashboard.putNumber("The robot already has a ball in it", 0);
//
//            return;
//        }
        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }

        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittDetector, inFeed), "LoadAndCatch");
        m_thread.start();
    }

    public void loadDisable() {
        if (!m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("Load is already disabled", threadFailTimes);
            return;
        }
        m_thread.interrupt();
    }

    public void armTheShooter() {
        shooter.shoot();
    }

    public void lowerAnchor() {
        anchor.drop();
    }

    public void raiseAnchor() {
        anchor.raise();
    }

    public void openSideArms() {
        sideArm.open();
    }

    public void closeSideArms() {
        sideArm.close();
    }

    public boolean loadIsEnabled() {
        return m_thread.isAlive();
    }

    public boolean anchorIsDown() {
        return anchor.isDown();
    }

    public boolean sideArmsAreDown() {
        return sideArm.sideArmState();
    }

    public void displayPressure() {
        airTankRefreshWait++;

        if (airTankRefreshWait == 100) {
            SmartDashboard.putNumber("Tank PSI", ballHandlerCompressor.getPressure());
            SmartDashboard.putNumber("Tank PSI Raw Data", ballHandlerCompressor.getPressure());
            airTankRefreshWait = 0;
        }
    }

    public boolean checkNeededAirPressureToShoot() {
        return ballHandlerCompressor.getPressure() >= 80;
    }

    public void displayBallInMitt() {
        ballInMittDetector.displayBallInMitt();
    }
}
