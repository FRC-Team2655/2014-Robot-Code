/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Seth
 */
public class LoadAndCatchCommand implements Runnable {

    private InFeed m_loadArm;
    private SideArms m_sideArm;
    private DigitalInput ballInMittLimitSwitch;

    // this constructor is used for the load command
    public LoadAndCatchCommand(SideArms sideArm, DigitalInput ballInMittLimitSwitch, InFeed inFeed) {
        m_sideArm = sideArm;
        m_loadArm = inFeed;

    }

    // this is the constructer for the catch command
    public void run() {

        //Load
        m_sideArm.open();
        m_loadArm.on();

        while (ballInMittLimitSwitch.get() != true) {
            try {
                Thread.sleep(Global.loadIdleTime);
            } catch (InterruptedException ex) {

            }
        }
        m_loadArm.off();
        m_sideArm.close();

    }
}
