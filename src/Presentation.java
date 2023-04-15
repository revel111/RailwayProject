import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;
import customVariables.customCars.PassengerCar;
import customVariables.customExtra.TooManyCarsException;
import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.*;

public class Presentation {
    public static void main(String[] args) throws TooManyException, TooManyCarsException {
        boot();

        Station station0 = new Station("A");
        Station station1 = new Station("B");
        Station station2 = new Station("C");
        Station station3 = new Station("D");
        Station station4 = new Station("E");
        Station station5 = new Station("F");
        Station station6 = new Station("G");

        Station.createInterestion(station0, station1);
        Station.createInterestion(station0, station2);
        Station.createInterestion(station0, station3);
        Station.createInterestion(station1, station5);
        Station.createInterestion(station5, station6);
        Station.createInterestion(station6, station4);

        Locomotive locomotive = new Locomotive("da", station1, station0, station4);
        ArrayList<Car> cars = Car.generateCarRandomly("shippingnames.txt", 100);
        Trainset trainset = new Trainset("fuck", locomotive, cars);
        DataLists.getTrainsets().add(trainset);
        DataLists.printData(DataLists.getTrainsets());

        trainset.createRail();
        trainset.printRouteSet();

//        PassengerCar car1 = new PassengerCar("A");
//        PassengerCar car2 = new PassengerCar("B");
//        PassengerCar car3 = new PassengerCar("B");
//
////        cars.add(car1);
////        cars.add(car2);
////        cars.add(car3);
//
////        Trainset.generateRoute(trainset);
////        String string = DataLists.printRoute(trainset);
////        System.out.println(string);
//        Rail rail = new Rail(station0, station1, 500);
//        DataLists.getRails().add(rail);
//        Rail.createRail(trainset);
//        Rail.createRailsRandomly();
//        DataLists.printRails();
////        DataLists.getRails().add(rail);
////        Rail.createRail(trainset);
////        String string = DataLists.printRouteSet(trainset);
////        System.out.println(string);
////        Car.createCars();
////        Car.createCars();
////        DataLists.printData(DataLists.getCars());
////        System.out.println(trainset.getDistance());
//        Station.generateRandomStation();
//        Trainset.generateTrainsetRandomly();
//        DataLists.printData(DataLists.getTrainsets());

    }

    public static void boot() {
        DataLists.initializeLists();
    }
}
