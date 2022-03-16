
package isp.lab9.exercise3;

import static isp.lab9.exercise3.DoorStatus.CLOSE;
import static isp.lab9.exercise3.DoorStatus.OPEN;


public class Door {
    private DoorStatus status;

    public Door(DoorStatus status) {
        this.status = status;
    }

    Door() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public void loockDoor(){
       this.status = DoorStatus.CLOSE;

   }  
   
    public void unlockDoor(){
       this.status = DoorStatus.OPEN;

   }  

    public DoorStatus getStatus() {
        return status;
    }
    
   
    
}
