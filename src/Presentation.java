import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;
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

//        Station.createIntersection(station0, station1);
//        Station.createIntersection(station0, station2);
//        Station.createIntersection(station0, station3);
//        Station.createIntersection(station1, station5);
//        Station.createIntersection(station5, station6);
//        Station.createIntersection(station6, station4);

        Rail.createRail(station0, station1, 100);
//        Rail.createRail(station0, station2, 200);
//        Rail.createRail(station0, station3, 200);
//        Rail.createRail(station1, station5, 200);
//        Rail.createRail(station5, station6, 200);
//        Rail.createRail(station6, station4, 200);

        Locomotive locomotive1 = new Locomotive("da", station1, station0, station1);
        Locomotive locomotive2 = new Locomotive("da", station1, station0, station1);
        ArrayList<Car> cars = Car.generateCarRandomly("shippingnames.txt", 100);
        Trainset trainset1 = new Trainset("fuck", locomotive1, cars);
        Trainset trainset2 = new Trainset("blya", locomotive2, cars);
//        DataLists.getTrainsets().add(trainset1);
//        Rail.createRailsRandomly();
//        trainset.createRail();
//        trainset.printRouteSet();
//        Rail.createRailsRandomly();
//        DataLists.printData(DataLists.getRails());
//        System.out.println("######################");
//        DataLists.printData(DataLists.getRailsReversed());
        Thread t1 = new Thread(trainset1);
//        Thread t2 = new Thread(trainset2);
        t1.start();
//        t2.start();

//        Trainset.createTrainset();
//        DataLists.printData(DataLists.getTrainsets());
//        Trainset.createTrainset();
//        System.out.println(trainset1);
//        Trainset.createTrainset();
//        DataLists.printData(DataLists.getTrainsets());
//        trainset1.createRail();
//        trainset1.printRouteSet();
//        Rail.createRailsRandomly();
//        DataLists.printData(DataLists.getRails());
//        trainset2.run();
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
