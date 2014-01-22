

package edu.wpi.first.wpilibj.templates;

// Author Zephan

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SideArms {

    DoubleSolenoid sideArms;
//  int extendTime;
    
    public SideArms(int extendChannel, int retractChannel) {
    
        sideArms = new DoubleSolenoid(extendChannel, retractChannel);
//      extendTime = timeToExtend; 
//      If you add extend time back make sure you add it to the constructor  
//      Also add this back to the constructor if needed int sidecarModule, and the sidearms =. 
    }
//  Opens the sidearms.
    void open() {
        
        sideArms.set(DoubleSolenoid.Value.kForward);
        //thread.sleep(extendTime);
        sideArms.set(DoubleSolenoid.Value.kOff);
    
    }
//  Closes the sidearms.  
    void close() {
    
        sideArms.set(DoubleSolenoid.Value.kReverse);
        //thread.sleep(extendTime);
        sideArms.set(DoubleSolenoid.Value.kOff);
    }
    
  
    
}
