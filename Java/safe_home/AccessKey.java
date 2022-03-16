
package isp.lab9.exercise3;

import java.util.Objects;


public class AccessKey {
    private String pin;

    public AccessKey(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "AccessKey{" + "pin=" + pin + '}';
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccessKey other = (AccessKey) obj;
        if (!Objects.equals(this.pin, other.pin)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
