package operations;

import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;

import java.util.*;
//import java.util.HashMap;

public class DataLists {
    private static ArrayList<Locomotive> locomotives;
    private static ArrayList<Station> stations;
    private static ArrayList<Car> cars;
    private static ArrayList<Trainset> trainsets;
//    private static ArrayList<Rail> rails = new ArrayList<>();

//    public static ArrayList<Rail> getRails() {
//        return rails;
//    }

    public static Set<Rail> rails = new HashSet<>();

    public static Set<Rail> getRails() {
        return rails;
    }

    public static ArrayList<Locomotive> getLocomotives() {
        return locomotives;
    }

    public static ArrayList<Station> getStations() {
        return stations;
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public static ArrayList<Trainset> getTrainsets() {
        return trainsets;
    }

    public static void initializeLists() {
        locomotives = new ArrayList<>();
        stations = new ArrayList<>();
        cars = new ArrayList<>();
        trainsets = new ArrayList<>();
        rails = new HashSet<>();
    }

    public static String printCarsT(Trainset trainset) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getCars().size(); i++)
            string.append(trainset.getCars().get(i).toString()).append("\n");

        return string.toString();
    }

    public static String printRoute(Trainset trainset) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getRouteStations().size(); i++)
            string.append(trainset.getRouteStations().get(i).toString()).append("\n");

        return string.toString();
    }

    public static String printRouteSet(Trainset trainset) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getRouteRails().size(); i++)
            string.append(trainset.getRouteRails().get(i).toString()).append("\n");

        return string.toString();
    }

//    public static void printInteresections(Station station) {
//
//        for(Station s : stations)
//            if (s == station)
//                System.out.println(station.getStations());
//
//    }

    public static <T> void printData(ArrayList<T> arrayList) {
        for (T t : arrayList)
            System.out.println(t.toString());
        System.out.println();
    }

//    public static void sortTrainsetsByDistance() {
//        Collections.sort(DataLists.getTrainsets(), new Comparator<Trainset>() {
//            @Override
//            public int compare(Trainset trainset1, Trainset trainset2) {
//                return Double.compare(trainset1.get);
//            }
//        });
//    }

    public static void printTypesOfCars() {
        System.out.println();
        System.out.println("1)  Passenger car;");
        System.out.println("2)  Post office car;");
        System.out.println("3)  Baggage car;");
        System.out.println("4)  Mail car;");
        System.out.println("5)  Restaurant car;");
        System.out.println("6)  Refrigerated railroad car;");
        System.out.println("7)  Car for liquid materials,;");
        System.out.println("8)  Car for gaseous materials;");
        System.out.println("9)  Car for explosives;");
        System.out.println("10) Car for toxic materials;");
        System.out.println("11) Car for liquid, toxic material;");
        System.out.println();
    }
}