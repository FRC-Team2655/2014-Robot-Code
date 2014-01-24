package edu.wpi.first.wpilibj.templates;

// Author Bennett
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms implements Runnable {

    DoubleSolenoid sideArms;
    int armMoveTime = 100; //this is how long to wait in miliseconds before turning off
    int sideArmMode = SideArmEnum.noAirState;
    boolean debugSideArmThreadException = false;

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
//sleeps    
    public void noAirState(){
        sideArmMode = SideArmEnum.noAirState;
    }

    public void run() {
        
        while (true) {

            if (sideArmMode == SideArmEnum.open) {
                
                sideArms.set(DoubleSolenoid.Value.kForward);

                try {
                    
                    Thread.sleep(armMoveTime); //wait for the arm to open
                    
                } catch (Exception e) {
                    
                    if (debugSideArmThreadException) {
                        
                        e.printStackTrace();
                    }
                }
                sideArms.set(DoubleSolenoid.Value.kOff);
                noAirState();

            } 
            
            else if (sideArmMode == SideArmEnum.close) {
                sideArms.set(DoubleSolenoid.Value.kReverse);

                try {
                    
                    Thread.sleep(armMoveTime); //wait for the arm to close
                    
                } catch (Exception e) {
                    if (debugSideArmThreadException) {
                        
                        e.printStackTrace();
                        
                    }
                }
                sideArms.set(DoubleSolenoid.Value.kOff);
                        
                noAirState(); //switch to the noAirState
            } 
            
            else if (sideArmMode == SideArmEnum.noAirState) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

}
