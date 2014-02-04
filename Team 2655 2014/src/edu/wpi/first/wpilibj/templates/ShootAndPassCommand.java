package edu.wpi.first.wpilibj.templates;


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

        if (m_anchor != null) {
            m_anchor.drop();
            m_sideArm.open();
            m_shooter.shoot();
            m_sideArm.close();
            m_anchor.raise();

        } else {
            m_sideArm.open();
            m_shooter.pass();
            m_sideArm.close();
        }

    }
}
