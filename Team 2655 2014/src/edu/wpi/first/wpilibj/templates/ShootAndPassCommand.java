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
            //
            // SHOOT THE BALL
            //

            // TODO : test this open delay -- they don't have to open all the way
            // so I put in a partial time. better to have an angle encoder but
            // a programmer's gotta do what he's gotta do
            double openDelay = Math.max(Global.sideArmPartialOpenTime, Global.inFeedPartialLowerTime);
            double shootDelay = Global.shooterShootTime;
            double retractDelay = Math.max(Global.shooterRetractTime, Math.max(Global.sideArmCloseTime, Global.inFeedRaiseTime));
            
            m_timer.start();
            // turn solenoids ON in parallel
            m_sideArm.rawOpen();
            m_inFeed.rawLower();
            // wait till everything is ready before turning solenoids off
            TeamTimer.delay((long) (openDelay - m_timer.get()));

            // total time = 100ms
            // turn all solenoids OFF
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
            TeamTimer.delay((long) (shootDelay - m_timer.get()));

            // total time = 100 + 250 = 350           
            
            // start pulling it all back together
            //
            m_timer.reset();

            m_shooter.rawRetract();
            m_sideArm.rawClose();
            m_inFeed.rawRaise();

            // total time = 350'ish
            // wait till everything is ready before turn all solenoids off
            TeamTimer.delay((long) (retractDelay - m_timer.get()));

            //
            // total time = 450 (no anchors)
            //
            // turn all solenoids OFF
            //
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
