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
    private final Rotation m_rotate;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_inFeed = inFeed;
        m_anchor = null;
        m_rotate = null;
    }

    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor, InFeed inFeed, Rotation rotation) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;
        m_inFeed = inFeed;
        m_rotate = rotation;
    }

    public void run() {

        if (m_anchor != null) {
            m_rotate.faceForward();
            m_anchor.drop();
            m_shooter.charge();
            m_sideArm.open();
            m_inFeed.lowerArm();
            TeamTimer.delay(Global.waitTimeShoot);
            m_shooter.shooterOff();
            TeamTimer.delay(250);
            m_shooter.retract();
            TeamTimer.delay(1500);
            m_inFeed.liftArms();
            m_sideArm.close();
            m_anchor.raise();
        } else {
            m_sideArm.open();
            m_inFeed.lowerArm();
            m_shooter.pass();
            TeamTimer.delay(2000);
            m_inFeed.liftArms();
            m_sideArm.close();
        }

    }
}
