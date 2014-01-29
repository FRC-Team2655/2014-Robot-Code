package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms implements Runnable {
    

    DoubleSolenoid sideArms;
    int sideArmCloseTime = 100;
    int sideArmOpenTime = 100;
    int sideArmIdleTime = 100;//this is how long to wait in miliseconds before turning off
    int sideArmMode = SideArmStates.noAirState;
    //   boolean debugSideArmThreadException = false;

    class SideArmStates {

        public static final int noAirState = 0;
        public static final int openState = 1;
        public static final int closeState = 2;

    }
    public SideArms() {

        sideArms = new DoubleSolenoid(HardwarePorts.sideArmOpenArmChannel,HardwarePorts.sideArmClosedArmChannel);
//      extendTime = timeToExtend; 
//      If you add extend time back make sure you add it to the constructor  
//      Also add this back to the constructor if needed int sidecarModule, and the sidearms =. 

    }
//  Opens the sidearms.

    public void open() {
        sideArmMode = SideArmStates.openState;
    }
//  Closes the sidearms.  

    public void close() {
        sideArmMode = SideArmStates.closeState;

    }

    public void run() {

        while (true) {

            switch (sideArmMode) {
                case SideArmStates.openState:
                    /*set the solenoid to open, wait some time, 
                     then turn off. next go to no air state*/
                    sideArms.set(DoubleSolenoid.Value.kForward);
                    try {
                        Thread.sleep(sideArmOpenTime); //wait for the arm to open
                    } catch (Exception e) {
                        /* if (debugSideArmThreadException) { 
                         e.printStackTrace();
                         }*/
                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmStates.noAirState;
                    break;
                case SideArmStates.closeState:
                    /*set the solenoid to close, wait some time, 
                     then turn off. next go to no air state*/

                    sideArms.set(DoubleSolenoid.Value.kReverse);
                    try {
                        Thread.sleep(sideArmCloseTime); //wait for the arm to open
                    } catch (Exception e) {
                        /* if (debugSideArmThreadException) { 
                         e.printStackTrace();
                         }*/
                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmStates.noAirState;
                    break;
                case SideArmStates.noAirState:
// wait some time

                    try {
                        Thread.sleep(sideArmIdleTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    break;

            }

        }
    }

}
