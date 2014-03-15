package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Alex Senneville
 */
public class ShootAndPassCommand implements Runnable {

    private final Shooter m_shooter;
    private final SideArms m_sideArm;
    private final Anchor m_anchor;
    private final InFeed m_inFeed;
    private Timer m_timer;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_inFeed = inFeed;
        m_anchor = null;
        m_timer = new Timer();
    }

    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;
        m_inFeed = inFeed;
    }

    public void run() {

        if (m_anchor != null) {

            // SHOOT
            // turn solenoids ON in parallel
//            m_anchor.rawDrop(); // anchors not used during shoot
            m_sideArm.rawOpen();
            m_inFeed.rawLower();
            // total time = 0'ish

            m_timer.start();
            // wait till everything is ready before turning solenoids off
            while (m_timer.get() < Math.max(Global.sideArmOpenTime, Global.inFeedLowerTime)) {
                TeamTimer.delay(5);
            }
            // total time = 100ms
            // turn all solenoids OFF
//            m_anchor.rawOff();
            m_sideArm.rawOff();
            m_inFeed.rawOff();
            // total  time = 100ms

            //
            //
            // the main shooting time
            //
            //
            m_timer.reset();
            m_shooter.rawExtend();
            // we could start letting out anchor air at the same time

            // wait for it, wait for it
            while (m_timer.get() < Global.shooterShootTime) {
                TeamTimer.delay(5);
            }

            // total time = 100 + 250 = 350
            //
            // start pulling it all back together
            //
            m_timer.reset();

            m_shooter.rawRetract();
//            m_anchor.rawRaise();
            m_sideArm.rawClose();
            m_inFeed.rawRaise();

            // total time = 350'ish
            // wait till everything is ready before turn all solenoids off
            while (m_timer.get() < Math.max(Global.shooterRetractTime, Math.max(Global.sideArmCloseTime, Global.inFeedRaiseTime))) {
                TeamTimer.delay(5);
            }
            // how long does it take to raise the anchors ? 250ms ?????

            //
            // total time = 450 (no anchors)
            //
            // turn all solenoids OFF
            //
//            m_anchor.rawOff();
            m_sideArm.rawOff();
            m_inFeed.rawOff();
            m_shooter.rawOff();

            // total time about 450ms
            
        } else { // PASS

            m_sideArm.open();
            m_inFeed.lowerArm();
            m_shooter.pass();
            TeamTimer.delay(1500); /// WOW 1.5 seconds wait here ??
            m_inFeed.liftArms();
            m_sideArm.close();
        }
    }

//    public void run() {
//
//        if (m_anchor != null) {
//            m_anchor.drop();
//            m_sideArm.open();
//            m_inFeed.lowerArm();
//            m_shooter.shoot();
//            m_inFeed.liftArms();
//            m_sideArm.close();
//        } else {
//            // pass only
//            m_sideArm.open();
//            m_inFeed.lowerArm();
//            m_shooter.pass();
//            TeamTimer.delay(1500); /// WOW 1.5 seconds wait here
//            m_inFeed.liftArms();
//            m_sideArm.close();
//        }
//    }
}
