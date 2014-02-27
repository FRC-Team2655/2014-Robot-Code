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

    public BallHandler() {

        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        m_thread = new Thread();
        ballInMittDetector = new BallInMittDetector();
        threadFailTimes = 0;

        ballHandlerCompressor.start();

    }

    public void passTheBall() {

        if (!ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);
            return;
        }

        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, inFeed), "ShootAndPass");
        m_thread.start();
    }

    public void shootTheBall() {
        if (!ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);

            return;
        }

        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }
        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor, inFeed), "ShootAndPass");

        m_thread.start();
    }

    public void loadEnable() {
        if (ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot already has a ball in it", 0);

            return;
        }
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

    public void lowerAnchor() {
        if (m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("The Robot is still doing something or just finished", threadFailTimes);
            return;
        }
        
        m_thread = new Thread(new Anchor());
        anchor.run();
    }

    public void raiseAnchor() {
        if (!m_thread.isAlive()) {
            threadFailTimes++;
            SmartDashboard.putNumber("Load is already disabled", threadFailTimes);
            return;
        }
        m_thread.interrupt();
    }

    public boolean loadIsEnabled() {
        return m_thread.isAlive();
    }

    public boolean anchorIsEnabled() {
        return m_thread.isAlive();
    }

    public void displayPressure() {
        airTankRefreshWait++;

        if (airTankRefreshWait == 100) {
            SmartDashboard.putNumber("Tank PSI", ballHandlerCompressor.getPressure());
            airTankRefreshWait = 0;
        }
    }
}
