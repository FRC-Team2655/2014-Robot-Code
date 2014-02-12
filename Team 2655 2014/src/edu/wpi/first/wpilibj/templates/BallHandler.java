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

    public BallHandler() {

        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        m_thread = new Thread();
        ballInMittDetector = new BallInMittDetector();

        ballHandlerCompressor.start();

    }

    public void passTheBall() {

        if (!ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);
            return;
        }

        if (m_thread.isAlive()) {
            SmartDashboard.putNumber("The Thread is still alive", 0);
            return;
        }

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm), "ShootAndPass");
        m_thread.start();
    }

    public void shootTheBall() {

        SmartDashboard.putNumber("Robot Shoots the ball", 0);

        if (!ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);

            return;
        }

        if (m_thread.isAlive()) {
            SmartDashboard.putNumber("Thread is still active", 0);

            return;
        }

        SmartDashboard.putNumber("Thread should start", 0);

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor), "ShootAndPass");

        m_thread.start();
    }

    public void loadEnable() {
        SmartDashboard.putNumber("Checking limit switch", 0);

        if (ballInMittDetector.ballInMitt()) {
            SmartDashboard.putNumber("The robot already has a ball in it", 0);

            return;
        }
        if (m_thread.isAlive()) {
            return;
        }

        SmartDashboard.putNumber("The load thread shouldn run", 0);

        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittDetector, inFeed), "LoadAndCatch");
        m_thread.start();
    }

    public void loadDisable() {
        SmartDashboard.putNumber("Made it to loadDisable", 0);

        if (!m_thread.isAlive()) {
            SmartDashboard.putNumber("Thread is already alive", 0);
            return;
        }

        SmartDashboard.putNumber("Trys to disable load", 0);
        m_thread.interrupt();
        SmartDashboard.putNumber("Made it past thread.interrupt", 0);

    }

    public boolean loadIsEnabled() {
        return m_thread.isAlive();
    }

    public void dropAnchor() {
        anchor.drop();
    }

    public void raiseAnchor() {
        anchor.raise();
    }

    public boolean anchorIsUp() {
        return !anchor.anchorIsDropped();
    }

    public void displayPressure() {
        airTankRefreshWait++;

        if (airTankRefreshWait == 100) {
            SmartDashboard.putNumber("Tank PSI", ballHandlerCompressor.getPressure());
            airTankRefreshWait = 0;
        }
    }
}
