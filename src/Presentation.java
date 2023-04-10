import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.BaggageCar;
import customVariables.customCars.Car;
import customVariables.customCars.PassengerCar;
import customVariables.customExtra.TooManyCarsException;
import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Presentation {
    public static void main(String[] args) throws TooManyException, TooManyCarsException {
        boot();
//        Station station = new Station("Fuck");
//        Station station1 = new Station("Lmao");
//        Station station2 = new Station("Cringe");
//        Station station3 = new Station("u fukked");
//
//        Locomotive locomotive = new Locomotive("First one", 1002.4, 100000, 30, 5, station, station1, station2);
//        System.out.println(locomotive);
//        System.out.println(station);
//        System.out.println(station1);
//        ArrayList<Locomotive> locomotives = new ArrayList<Locomotive>();
//        locomotives.add(locomotive);
//        System.out.println(locomotives);

//        PassengerCar car = new PassengerCar()
//        ConsoleInput.addStation();
//        ConsoleInput.addStation();
//
//        DataLists.printStations();
//
//        ConsoleInput.deleteStation("st1");
//        ConsoleInput.addStation();
//
//        DataLists.printStations();
//        PassengerCar car = new PassengerCar(false, 100,100);
//        System.out.println(car);
//        PassengerCar car1 = new PassengerCar(false, 100,100);
//        System.out.println(car1);

//        ConsoleInput.createLocomotive();
//        DataLists.printLocomotives();
//        DataLists.printStations();

//        ConsoleInput.createPassengerCar();
//        ConsoleInput.createPassengerCar();
//        ConsoleInput.createPassengerCar();

//        ConsoleInput.createCar();
//        DataLists.printCars();

//        ConsoleInput.createLocomotive();
//        DataLists.printLocomotives();

//        ConsoleInput.createTrainset();
//        DataLists.printTrainsets();

//        ConsoleInput.createCar();
//        ConsoleInput.createCar();
//        ConsoleInput.createCar();
//        ConsoleInput.createCar();
//        DataLists.printData(DataLists.getCars());
//        Trainset.createTrainset();
//        DataLists.printData(DataLists.getTrainsets());
//        DataLists.printData(DataLists.getCars());
//        DataLists.printData(DataLists.getLocomotives());
//        Trainset trainset = DataLists.getTrainsets().get(0);
//        trainset.getCars().get(0).addPassengers();
//        DataLists.printData(DataLists.getTrainsets());
//        DataLists.printData(DataLists.getCars());

//        Station station1 = new Station("A");
//        Station station2 = new Station("B");
//        DataLists.getStations().add(station1);
//        DataLists.getStations().add(station2);
//
////        DataLists.getStations().get(0).getIntersections().add(station1);
//        DataLists.getStations().get(0).getIntersections().add(station2);
//
//
//        System.out.println(Arrays.toString(DataLists.getStations().get(0).getIntersections().toArray()));
//        DataLists.printData(DataLists.getStations());
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
        ArrayList<Car> cars = new ArrayList<>();
        PassengerCar car1 = new PassengerCar("A");
        PassengerCar car2 = new PassengerCar("B");
        PassengerCar car3 = new PassengerCar("B");

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        Trainset trainset = new Trainset("fuck", locomotive, cars);
//        Trainset.generateRoute(trainset);
//        String string = DataLists.printRoute(trainset);
//        System.out.println(string);
        Rail rail = new Rail(station0, station1, 500);
        DataLists.getRails().add(rail);
        Rail.createRail(trainset);
        String string = DataLists.printRouteSet(trainset);
        System.out.println(string);
//        Car.createCars();
//        Car.createCars();
//        DataLists.printData(DataLists.getCars());
    }

    public static void boot() {
        DataLists.initializeLists();
    }
}
