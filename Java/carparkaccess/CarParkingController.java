
package isp.lab8.carparkaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarParkingController {
    CarParking parking;
    List<CarParkingEventRecord> log;
    
    public CarParkingController() throws IOException, ClassNotFoundException{
        ReadToFile();
    }
 
    public boolean  EnterCar(Car c) throws IOException{
        if(parking.getEmptyParkingSpots()>0 && !parking.hasCar(c)){
            parking.addCar(c);
            log.add(new CarParkingEventRecord(c, new Date(), true));
            WriteToFile();
            return true;   
    }
        else return false;
        
    }
    
    public double ExitCar(Car c){
        double cost = 0;
        if(parking.getEmptyParkingSpots() == 0 && parking.hasCar(c)){
            parking.removeCar(c);
           // log.remove(CarParkingEventRecord(c, Date()));
            cost = parking.CalculateUsageCost();
        }
        return  cost;
    }
    
    public List<Car> getCurrentParkedCars(Car c){
        List<CarParkingEventRecord> result = new ArrayList<CarParkingEventRecord>();
        for(CarParkingEventRecord cars:  log)
        if(parking.hasCar(c) == true){
        } else {
            try {
                WriteToFile();
            } catch (IOException ex) {
                Logger.getLogger(CarParkingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }  
        return null;
        
    }
    
    public List<Car> getPastEntriesCars(Car c){
        List<CarParkingController> result = new ArrayList<CarParkingController>();
        if(parking.hasCar(c) == false){
            try {
                WriteToFile();
            } catch (IOException ex) {
                Logger.getLogger(CarParkingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  
        
        
        return null;
        
    }
    
    public List<Car> getAllUniquePastEntries(){
        
        return null;
        
    }

    private void WriteToFile() throws IOException {
         ObjectOutputStream o = null;
         try{
             o =  new ObjectOutputStream(
                new FileOutputStream("C:\\carparkinglog.txt"));
 
            o.writeObject(log);
            o.close();
         } catch(IOException ex){
             Logger.getLogger(CarParkingController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    private void ReadToFile() throws IOException, ClassNotFoundException {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\carparkinglog.txt"));
             log = (List<CarParkingEventRecord>)in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarParkingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
             Logger.getLogger(CarParkingController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}


class CarParkingEventRecord implements Serializable{
    Car car;
    Date enterTime;
    Date leaveTime;
    
    public CarParkingEventRecord(Car c, Date enterTime, boolean isEnterTime){
        this.car = c;
        this.enterTime = enterTime;
    }
    
      
}