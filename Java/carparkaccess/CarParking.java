
package isp.lab8.carparkaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class CarParking {
    private List<ParkingSpot>parkingSpots;
    public CarParking(int numberOfParkingSpots){
        parkingSpots = new ArrayList();
        for(int i=0;i<numberOfParkingSpots;i++){
            parkingSpots.add(new ParkingSpot());
        }
    }
    public int getEmptyParkingSpots(){
        int numberOfEmptyParkingSpots = 0;
        for(ParkingSpot spot : parkingSpots){
            if(spot.isFree())
                numberOfEmptyParkingSpots++;
        }
     return numberOfEmptyParkingSpots;   
    }
    
    public void addCar(Car c){
        for(ParkingSpot spot : parkingSpots){
            if(spot.isFree()){
                spot.putCar(c);
                return;
            }
            
        }
    }
    
    public void removeCar(Car c){
        for(ParkingSpot spot : parkingSpots){
            if(!spot.isFree()){
                spot.deleteCar(c);
                return;
            }
            }
        
    }
    
    public boolean hasCar(Car c){
        for(ParkingSpot spot : parkingSpots){
            if(spot.carOnThisSpot != null && spot.carOnThisSpot.equals(c))
                return true;
        }
        return false;
    }
    
    public double CalculateUsageCost(){
        double second = 0;
       
        return second;
    }
    
}

class ParkingSpot{
    
    boolean isFree;
    Car carOnThisSpot;
    
    public void putCar(Car c) {
        this.carOnThisSpot = c;
    }
    
    public void deleteCar(Car c){
       
    }
    
    public boolean isFree(){
        return this.carOnThisSpot == null;
    }
}
