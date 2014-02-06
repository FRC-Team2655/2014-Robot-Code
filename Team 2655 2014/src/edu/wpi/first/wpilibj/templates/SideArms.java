package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideArms {

    DoubleSolenoid sideArms;

    int sideArmMode;
    //   boolean debugSideArmThreadException = false;

    private Thread thread;

    class SideArmStates {

        public static final int noAirState = 0;
        public static final int openState = 1;
        public static final int closeState = 2;
    }

    private class sideArmThread extends Thread {

        public sideArmThread() {

        }

        public void run() {

            switch (sideArmMode) {
                case SideArmStates.openState:
                    /*set the solenoid to open, wait some time, 
                     then turn off. next go to no air state*/
                    sideArms.set(DoubleSolenoid.Value.kForward);
                    try {
                        thread.sleep(Global.sideArmOpenTime); //wait for the arm to open
                    } catch (Exception e) {

                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmStates.noAirState;
                    break;
                case SideArmStates.closeState:
                    /*set the solenoid to close, wait some time, 
                     then turn off. next go to no air state*/

                    sideArms.set(DoubleSolenoid.Value.kReverse);
                    try {
                        thread.sleep(Global.sideArmCloseTime); //wait for the arm to open
                    } catch (Exception e) {

                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmStates.noAirState;
                    break;
                case SideArmStates.noAirState:

                    try {
                        thread.sleep(Global.sideArmIdleTime);
                    } catch (InterruptedException ex) {

                    }
                    break;
                default:
                    break;

            }
        }
    }

    public SideArms() {
        thread = new sideArmThread();
        sideArms = new DoubleSolenoid(Ports.SolenoidModule.sideArmOpenArmChannel, Ports.SolenoidModule.sideArmClosedArmChannel);
        sideArmMode = SideArmStates.noAirState;
              
//      extendTime = timeToExtend; 
//      If you add extend time back make sure you add it to the constructor  
//      Also add this back to the constructor if needed int sidecarModule, and the sidearms =. 

    }
//  Opens the sidearms.

    public void open() {

        SmartDashboard.putNumber("Sidearms open", 0);
       
        if (thread.isAlive()) {
            SmartDashboard.putNumber("Something is trying to run the sidearm thread(close and open) at the same time. You have a problem :(", 0);
            return;
        }

        sideArmMode = SideArmStates.openState;
        thread.start();
    }
//  Closes the sidearms.  

    public void close() {

        SmartDashboard.putNumber("Sidearms close", 0);
       
        if (thread.isAlive()) {
            SmartDashboard.putNumber("Something is trying to run the sidearm thread(close and open) at the same time. You have a problem :(", 0);
            return;
        }

        sideArmMode = SideArmStates.closeState;
        thread.start();

    }

}
