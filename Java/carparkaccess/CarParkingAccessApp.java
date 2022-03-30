package isp.lab8.carparkaccess;


import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CarParkingAccessApp {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("It works!");
        ObjectMapper objectMapper = new ObjectMapper();
        CarParkingController control = new CarParkingController();
        Car c1 = new Car("1");
        Car c2 = new Car("2");
        Car c3 = new Car("3");
        Car c4 = new Car("4");

        List<Car> list = control.getParkedCarsList();
        System.out.println(control.EnterCarPark(c1));
        System.out.println(control.EnterCarPark(c2));
        System.out.println(control.EnterCarPark(c3));
        // System.out.println(control.EnterCarPark(c4));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(control.ExitCarPark(c2));
        List<Action> List1 = control.getLogList();
        for (Action a : List1) {
            // System.out.println(a.c.getPlateNumber()+"\n"+a.message+"\n"+a.entryTime+"\n"+a.periodInSeconds);
        }


    }
}
