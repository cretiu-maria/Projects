
package isp.lab9.exercise3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoorLockController implements ControllerInterface {
    private Map<Tenant,AccessKey> validAccess = new HashMap<>();
    private Door d;
    private List<Tenant> tList = new ArrayList();
    private List<AccessLog> aList = new ArrayList();
    private List<AccessKey> aKeyList = new ArrayList();
    private int c=0;

    public DoorLockController(Door d) {
        this.d = d;
    }
    
     public Map<Tenant, AccessKey> getValidAccess() {
        return validAccess;
    }

    DoorLockController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DoorStatus enterPin(String pin) throws InvalidPinException{
        boolean status = false;
        
        if(pin.equals(MASTER_KEY)){
            c = 0;
            status = true;
            if(d.getStatus() == DoorStatus.OPEN){
                d.loockDoor();
            }
            else{
                d.unlockDoor();
            }
            aList.add(new AccessLog("Master User", LocalDateTime.now(), "Inserted", d.getStatus(), "no error"));   
        } 
        else if(c == 2){
            aList.add(new AccessLog("unknow", LocalDateTime.now(),"wrong pin", d.getStatus(), "too many tries"));
            TooManyAttemotsException exception = new TooManyAttemotsException();
            try {
                throw exception;
            } catch (TooManyAttemotsException ex) {
                Logger.getLogger(DoorLockController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        for (AccessKey key : aKeyList) {
            if (pin.equals(key.getPin())) {
                status = true;
                if (d.getStatus() == DoorStatus.OPEN) {
                    d.loockDoor();

                } else {
                    d.unlockDoor();

                }
            }
        }
        if (!status) {
            aList.add(new AccessLog("name", LocalDateTime.now(), "wrong pin", d.getStatus(), "incorrect pin"));
            c++;
            InvalidPinException exception = new InvalidPinException();
            throw exception;
        }
        return d.getStatus();
    }

   
    @Override
    public void addTenant(String pin, String name) throws TenantAlreadyExistsException {
       for (Tenant tenant : validAccess.keySet()) {
            if (name.equals(tenant.getName())) {

                aList.add(new AccessLog(name, LocalDateTime.now(), "Can not add tenant", d.getStatus(), "Tenant already exists"));
                TenantAlreadyExistsException exception = new TenantAlreadyExistsException();
                throw exception;
            }
        }
        Tenant tenant1= new Tenant(name);
        tList.add(tenant1);
        aKeyList.add(new AccessKey(pin));
        validAccess.put(tenant1, new AccessKey(pin));
        aList.add(new AccessLog(name, LocalDateTime.now(), "added Tenant", d.getStatus(), "no error"));
    }
    
    @Override
     public void removeTenant(String name) throws Exception{
        boolean status1 = true;
        int i = 0;

        for (Tenant t : tList) {
            if (name.equals(t.getName())) {
                status1 = false;
                i = tList.indexOf(t);
            }
        }

        if(i != 0) {
            aList.add(new AccessLog(name, LocalDateTime.now(), "can not remove Tenant", d.getStatus(), "tenant not found"));
            TenantNotFoundException exception = new TenantNotFoundException();
            try {
                throw exception;
            } catch (TenantNotFoundException ex) {
                Logger.getLogger(DoorLockController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        aList.add(new AccessLog(name, LocalDateTime.now(), "removed Tenant", d.getStatus(), "no error"));
        System.out.println(validAccess.remove(tList.get(i)));
        aKeyList.remove(i);

        tList.remove(tList.get(i));
     
        
    }
}
    
    
    
    

