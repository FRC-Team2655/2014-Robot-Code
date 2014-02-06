package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Alex Senneville
 */
public class ShootAndPassCommand implements Runnable {

    private Shooter m_shooter;
    private SideArms m_sideArm;
    private Anchor m_anchor;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = null;
    }

    // this costructor is used for the shoot command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;

    }

    public void run() {

        SmartDashboard.putNumber("The Thread has started to run", 0);

        if (m_anchor != null) {
            
            SmartDashboard.putNumber("The robot is shooting", 0);

            m_anchor.drop();
            m_sideArm.open();
            m_shooter.shoot(Global.waitTimeShoot);
            m_sideArm.close();
            m_anchor.raise();

        } else {
            
            SmartDashboard.putNumber("The robot is passing", 0);

            m_sideArm.open();
            m_shooter.shoot(Global.waitTimePass);
            m_sideArm.close();
        }

    }
}
