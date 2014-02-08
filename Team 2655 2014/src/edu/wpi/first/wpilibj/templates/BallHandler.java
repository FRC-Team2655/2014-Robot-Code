package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// edited by Alex and Zephan yay!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
public class BallHandler {

    private final CompressorSystem ballHandlerCompressor;
    private final Shooter shooter;
    private final SideArms sideArm;
    private final Anchor anchor;
    private final InFeed inFeed;
    private final DigitalInput ballInMittLimitSwitch;

    int timesTriedToActivate;

    private Thread m_thread;

    public BallHandler() {

        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        m_thread = new Thread();

        ballInMittLimitSwitch = new DigitalInput(Ports.ballInMittLimitSwitchChannel);

        ballHandlerCompressor.start();

        timesTriedToActivate = 0;
    }

    public void passTheBall() {

        if (ballInMittLimitSwitch.get() != Global.BALLINMITT) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);
            return;
        }

        if (m_thread.isAlive()) {
            timesTriedToActivate++;
            SmartDashboard.putNumber("The Thread is still alive", timesTriedToActivate);
            return;
        }

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm));
        m_thread.start();
    }

    public void shootTheBall() {

        SmartDashboard.putNumber("Robot Shoots the ball", 0);

        if (ballInMittLimitSwitch.get() != Global.BALLINMITT) {
            SmartDashboard.putNumber("The robot doesn't have a ball in it", 0);

            return;
        }

        if (m_thread.isAlive()) {
            SmartDashboard.putNumber("Thread is still active", 0);

            return;
        }

        SmartDashboard.putNumber("Thread should start", 0);

        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor));

        m_thread.start();
    }

    public void loadEnable() {
        SmartDashboard.putNumber("Checking limit switch", 0);

        if (ballInMittLimitSwitch.get() == Global.BALLINMITT) {
            SmartDashboard.putNumber("The robot already has a ball in it", 0);

            return;
        }
        if (m_thread.isAlive()) {
            return;
        }

        SmartDashboard.putNumber("The load thread shouldn run", 0);

        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch, inFeed));
        m_thread.start();
    }

    public void loadDisable() {
        if (!m_thread.isAlive()) {
            return;
        }
        m_thread.interrupt();
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
}
