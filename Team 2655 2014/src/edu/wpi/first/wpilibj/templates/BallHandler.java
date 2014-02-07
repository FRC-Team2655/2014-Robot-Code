package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// edited by Alex and Zephan yay!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
public class BallHandler {

    CompressorSystem ballHandlerCompressor;
    Shooter shooter;
    SideArms sideArm;
    Anchor anchor;
    InFeed inFeed;
    DigitalInput ballInMittLimitSwitch;

    int timesTriedToActivate;
    boolean m_anchorIsDropped;

    Thread m_thread;

    public void BallHandler() {

        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();

        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);
        m_anchorIsDropped = false;
        ballHandlerCompressor.start();

        timesTriedToActivate = 0;
    }

    public void catchEnable() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        if (m_thread.isAlive()) {
            return;
        }
        m_thread = new Thread(new LoadAndCatchCommand(sideArm, ballInMittLimitSwitch, inFeed));
        m_thread.start();
    }

    public void passTheBall() {

        if (ballInMittLimitSwitch.get() == true) {
            SmartDashboard.putNumber("The robot already has a ball in it", 0);
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
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        if (m_thread.isAlive()) {
            return;
        }
        m_thread = new Thread(new ShootAndPassCommand(shooter, sideArm, anchor));
        m_thread.start();
    }

    public void catchDisable() {
        //Experiment with these two methods.
        if (!m_thread.isAlive()) {
            return;
        }
        m_thread.interrupt();
    }

    public boolean catchIsEnabled() {
        return m_thread.isAlive();
    }

    public void loadEnable() {
        if (ballInMittLimitSwitch.get() == true) {
            return;
        }
        if (m_thread.isAlive()) {
            return;
        }

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
