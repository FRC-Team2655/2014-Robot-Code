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
    //DigitalInput shooterLimiterSwitch;

    int timesTriedToActivate;

    Thread m_thread;

    public void BallHandler() {
        //if we ever add or modify a timer do it in the  class itself
        ballHandlerCompressor = new CompressorSystem();
        shooter = new Shooter();
        sideArm = new SideArms();
        anchor = new Anchor();
        inFeed = new InFeed();
        ballInMittLimitSwitch = new DigitalInput(Ports.DigitalModule.ballInMittLimitSwitchChannel);

        timesTriedToActivate = 0;
        //shooterLimiterSwitch = new DigitalInput(Ports.DigitalModule.shooterLimiterSwitchChannel);

        ballHandlerCompressor.start();
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
        //Experiment
        m_thread.interrupt();
        //m_thread.notifyAll();
    }

    public boolean catchIsEnabled() {
        //test this
        //m_thread.equals(this);
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
//        loadState = loadStates.loading;
    }

    public void loadDisable() {
        if (!m_thread.isAlive()) {
            return;
        }
        //Experiment with these two methods.
        //m_thread.interrupt();
        m_thread.notifyAll();
    }

    public boolean loadIsEnabled() {
        //test this
        //m_thread.equals(this);
        return m_thread.isAlive();

    }
}
