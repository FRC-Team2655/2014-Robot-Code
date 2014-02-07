package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Alex Senneville
 */
public class ShootAndPassCommand implements Runnable {

    private final Shooter m_shooter;
    private final SideArms m_sideArm;
    private final Anchor m_anchor;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = null;
    }

    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;

    }

    public void run() {

        SmartDashboard.putNumber("The ShootAndPassCommand Thread has started to run", 0);

        if (m_anchor != null) {
            
            SmartDashboard.putNumber("The robot is shooting", 0);

            // need to add code here to ensure that
            // the robot hass stopped moving
            m_anchor.drop();
            m_sideArm.open();
            m_shooter.shoot();
            m_sideArm.close();
            m_anchor.raise();

        } else {
            
            SmartDashboard.putNumber("The robot is passing", 0);

            m_sideArm.open();
            m_shooter.pass();
            m_sideArm.close();
        }

    }
}
