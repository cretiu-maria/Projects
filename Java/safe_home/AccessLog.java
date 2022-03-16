
package isp.lab9.exercise3;

import java.time.LocalDateTime;

public class AccessLog {
    private String tenantName;
    private LocalDateTime dateTime;
    private String operation;
    private DoorStatus doorStatus;
    private String errorMessage;

    public AccessLog(String tenantName, LocalDateTime dateTime, String operation, DoorStatus doorStatus, String errorMessage) {
        this.tenantName = tenantName;
        this.dateTime = dateTime;
        this.operation = operation;
        this.doorStatus = doorStatus;
        this.errorMessage = errorMessage;
    }

    AccessLog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTenantName() {
        return tenantName;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOperation() {
        return operation;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

 
    public String getErrorMessage() {
        return errorMessage;
    }

}
