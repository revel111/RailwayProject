package operations;

import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;

import java.util.*;

public class DataLists {
    private static ArrayList<Locomotive> locomotives;
    private static ArrayList<Station> stations;
    private static ArrayList<Car> cars;
    private static ArrayList<Trainset> trainsets;
    private static ArrayList<Rail> rails = new ArrayList<>();
    private static ArrayList<Rail> railsReversed = new ArrayList<>();
    private static ArrayList<Trainset> threads = new ArrayList<>();

    public static Files files = new Files();

    public static Files getFiles() {
        return files;
    }

    public static ArrayList<Rail> getRails() {
        return rails;
    }

    public static ArrayList<Rail> getRailsReversed() {
        return railsReversed;
    }

    public static ArrayList<Locomotive> getLocomotives() {
        return locomotives;
    }

    public static ArrayList<Trainset> getThreads() {
        return threads;
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


    public static void initializeLists() { // function for lists initialization
        locomotives = new ArrayList<>();
        stations = new ArrayList<>();
        cars = new ArrayList<>();
        trainsets = new ArrayList<>();
        rails = new ArrayList<>();
        railsReversed = new ArrayList<>();
        threads = new ArrayList<>();
        Thread file = new Thread(files);
    }

    public static String printCarsT(Trainset trainset) { //function for creating list of cars
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getCars().size(); i++)
            string.append(trainset.getCars().get(i).toString()).append("\n");

        return string.toString();
    }

    public static <T> void printData(ArrayList<T> arrayList) { //function for printing arraylists of all types
        System.out.println();
        for (T t : arrayList)
            System.out.println(t.toString());
        System.out.println();
    }

    public static void sortTrainsetsByDistance() {
        DataLists.getTrainsets().sort(Comparator.comparingDouble(Trainset::getWholeDistance));
    }
}