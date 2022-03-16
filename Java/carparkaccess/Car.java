
package isp.lab8.carparkaccess;


import java.util.Objects;

public class Car {
    private String plateNumber;
    
    
    public boolean equals(Car c){
        return this.plateNumber.equals(c.plateNumber);
        
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    

    
}
