/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

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
        //Load
        m_sideArm.open();
        m_loadArm.on();

        try {
            while (true) {
                Thread.sleep(Global.loadIdleTime);
            }
        } catch (InterruptedException e) {
        }

        m_loadArm.off();
        m_sideArm.close();
    }
}
