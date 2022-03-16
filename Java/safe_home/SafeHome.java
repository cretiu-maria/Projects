package isp.lab9.exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SafeHome {
    
    public static void main(String[] args) throws Exception, InvalidPinException, TooManyAttemotsException, TenantNotFoundException, TenantAlreadyExistsException {
        Door door = new Door();
        AccessLog accessL = new AccessLog();
        List<Tenant> tenantList = new ArrayList<>();
        DoorLockController controler = new DoorLockController();
        try {
            controler.addTenant("2314", "tenant 1");
            controler.addTenant("3186", "tenant 2");
            controler.addTenant("0009", "tenant 3");
            controler.addTenant("8965", "tenant 4");
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        controler.removeTenant("tenant 1");
        Map<Tenant, AccessKey> a = controler.getValidAccess();
        for (Tenant name : a.keySet())
            System.out.println(name.getName());
        try {
            controler.enterPin("2314");
        } catch (InvalidPinException e) {
            e.printStackTrace();
        }

    }
}
