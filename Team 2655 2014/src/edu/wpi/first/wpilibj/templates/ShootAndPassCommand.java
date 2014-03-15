package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Alex Senneville
 */
public class ShootAndPassCommand implements Runnable {

    private final Shooter m_shooter;
    private final SideArms m_sideArm;
    private final Anchor m_anchor;
    private final InFeed m_inFeed;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_inFeed = inFeed;
        m_anchor = null;
    }

    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;
        m_inFeed = inFeed;
    }

    public void run() {

        if (m_anchor != null) {
//          m_anchor.drop();
            m_sideArm.open();
            m_inFeed.lowerArm();
            m_shooter.shoot();            
            m_inFeed.liftArms();
            m_sideArm.close();
            
        } else {
            m_sideArm.open();
            m_inFeed.lowerArm();
            m_shooter.pass();
            TeamTimer.delay(1500);
            m_inFeed.liftArms();
            m_sideArm.close();
        }

    }
}
