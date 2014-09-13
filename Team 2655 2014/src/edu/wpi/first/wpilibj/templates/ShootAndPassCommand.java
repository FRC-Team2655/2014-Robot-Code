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
    private final Timer m_timer;

    // this constructor is used for the pass command
    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, InFeed inFeed) {
        this(shooter,sideArm,null,inFeed);
//        m_shooter = shooter;
//        m_sideArm = sideArm;
//        m_inFeed = inFeed;
//        m_anchor = null;
//        m_timer = new Timer();
    }

    public ShootAndPassCommand(Shooter shooter, SideArms sideArm, Anchor anchor, InFeed inFeed) {
        m_shooter = shooter;
        m_sideArm = sideArm;
        m_anchor = anchor;
        m_inFeed = inFeed;
        m_timer = new Timer();
    }

    public void run() {

        if (m_anchor != null) {
            //
            // SHOOT THE BALL
            //
            double openDelay = Global.sideArmPartialOpenTime;
            double shootDelay = Global.shooterShootTime;
            double retractDelay = Math.max(Global.sideArmCloseTime, Global.inFeedRaiseTime);
            double shooterRetractDelay = Global.shooterRetractTime;
            double inFeedLowerDelay = Global.inFeedPartialLowerTime;

            m_timer.reset();
            m_timer.start();

            m_sideArm.rawOpen();
            m_shooter.rawExtend(); // CHARGE up the shooter pistons
//            m_anchor.rawDrop();

            // wait until anchors are down
            // wait until sideamrs are open
            // shooter is still CHARGING....
            TeamTimer.delay((long) (openDelay - m_timer.get()));

            // anchors and sidearms are far enough, stop them
//            m_anchor.rawOff();
            m_sideArm.rawOff();

            TeamTimer.delay((long) (shootDelay - m_timer.get()));

            // ok -- shooter is CHARGED enough, shoot the bloody thing            
            m_inFeed.rawLower(); // unlatch the shooter

            m_timer.reset();

            TeamTimer.delay((long) (inFeedLowerDelay - m_timer.get()));

            // total time = 100 + 250 = 350           
            // start pulling it all back together
            m_inFeed.rawOff();
            m_shooter.rawRetract();
//            m_anchor.rawRaise();

            m_timer.reset();

            // total time = 350'ish
            // wait till everything is ready before turn all solenoids off
            TeamTimer.delay((long) (shooterRetractDelay - m_timer.get()));

            //
            // total time = 450 (no anchors)
            //
            // turn all solenoids OFF
            //
//            m_anchor.rawOff();
            m_inFeed.rawRaise();
            m_sideArm.rawClose();

            TeamTimer.delay((long) (retractDelay - m_timer.get()));

            m_sideArm.rawOff();
            m_inFeed.rawOff();

            m_timer.stop();
//            m_shooter.rawOff();
            // total time about 450ms
        } else { // PASS

            double openDelay = Math.max(Global.sideArmPartialOpenTime, Global.inFeedPartialLowerTime);
            double passDelay = Global.shooterPassTime;
            double shooterRetractDelay = Global.shooterRetractTime;
            double retractDelay = Math.max(Global.sideArmCloseTime, Global.inFeedRaiseTime);

            m_timer.reset();
            m_timer.start();
            // turn solenoids ON in parallel
            m_sideArm.rawOpen();
            m_inFeed.rawLower();
            // wait till everything is ready before turning solenoids off
            TeamTimer.delay((long) (openDelay - m_timer.get()));

            // total time = 100ms
            // turn all solenoids OFF
            // total  time = 100ms
            //
            //
            // the main shooting time
            //
            //
            m_timer.reset();

            //start passing before we turn off the other two
            // might as well save even a bit more time here too
            m_shooter.rawExtend(); // start the PASS

            m_sideArm.rawOff();
            m_inFeed.rawOff();

            TeamTimer.delay((long) (passDelay - m_timer.get()));

            // total time = 100 + 250 = 350           
            // start pulling it all back together
            //
            m_timer.reset();

            m_shooter.rawRetract(); // end of PASS, turns the air off
            //let arms start to come back

            // total time = 350'ish
            // wait till everything is ready before turn all solenoids off
            TeamTimer.delay((long) (shooterRetractDelay - m_timer.get()));

            //
            // total time = 450 (no anchors)
            //
            // turn all solenoids OFF
            //
            m_sideArm.rawClose();
            m_inFeed.rawRaise();

            TeamTimer.delay((long) (retractDelay - m_timer.get()));

            m_inFeed.rawOff();
            m_sideArm.rawOff();

            m_timer.stop();
//            m_shooter.rawOff();
        }
    }
}
