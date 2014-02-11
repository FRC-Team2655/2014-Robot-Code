/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Seth
 */
public class LoadAndCatchCommand implements Runnable {

    private final InFeed m_loadArm;
    private final SideArms m_sideArm;
    private final BallInMittDetector m_ballInMittLimitSwitch;

    // this constructor is used for the load command
    public LoadAndCatchCommand(SideArms sideArm, BallInMittDetector ballInMittLimitSwitch, InFeed inFeed) {
        m_sideArm = sideArm;
        m_loadArm = inFeed;
        m_ballInMittLimitSwitch = ballInMittLimitSwitch;

    }

    // this is the constructer for the catch command
    public void run() {
        SmartDashboard.putNumber("Load thread has made it to run", 0);

        //Load
        m_sideArm.open();
        m_loadArm.on();

        while (m_ballInMittLimitSwitch.ballInMitt() == false) {
             SmartDashboard.putNumber("Inside while loop", 0);

            // need to rename time to something like
            // poll ball in mitt switch wait timer
            //
            // maybe we could use yield() instead of sleep
            // but, idk how to interrupt a thread from
            // user intput
            Timer.delay(Global.loadIdleTime);
        }
        SmartDashboard.putNumber("Out of while loop", 0);

        m_loadArm.off();
        m_sideArm.close();

    }
}
