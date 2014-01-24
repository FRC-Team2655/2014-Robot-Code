package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms implements Runnable {

    DoubleSolenoid sideArms;
    int sideArmCloseTime = 100;
    int sideArmOpenTime = 100;
    int sideArmIdleTime = 100;//this is how long to wait in miliseconds before turning off
    int sideArmMode = SideArmEnum.noAirState;
    //   boolean debugSideArmThreadException = false;

    public SideArms() {

        sideArms = new DoubleSolenoid(PublicEnumHardwarePorts.sideArmOpenArmChannel, PublicEnumHardwarePorts.sideArmClosedArmChannel);
//      extendTime = timeToExtend; 
//      If you add extend time back make sure you add it to the constructor  
//      Also add this back to the constructor if needed int sidecarModule, and the sidearms =. 

    }
//  Opens the sidearms.

    public void open() {
        sideArmMode = SideArmEnum.open;
    }
//  Closes the sidearms.  

    public void close() {
        sideArmMode = SideArmEnum.close;

    }

    public void run() {

        while (true) {

            switch (sideArmMode) {
                case SideArmEnum.open:
                    sideArms.set(DoubleSolenoid.Value.kForward);
                    try {
                        Thread.sleep(sideArmOpenTime); //wait for the arm to open
                    } catch (Exception e) {
                        /* if (debugSideArmThreadException) { 
                         e.printStackTrace();
                         }*/
                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmEnum.noAirState;
                    break;
                case SideArmEnum.close:
                    sideArms.set(DoubleSolenoid.Value.kReverse);
                    try {
                        Thread.sleep(sideArmCloseTime); //wait for the arm to open
                    } catch (Exception e) {
                        /* if (debugSideArmThreadException) { 
                         e.printStackTrace();
                         }*/
                    }
                    sideArms.set(DoubleSolenoid.Value.kOff);
                    sideArmMode = SideArmEnum.noAirState;
                    break;
                case SideArmEnum.noAirState:
                    try {
                        Thread.sleep(sideArmIdleTime);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;

            }

        }
    }

}
