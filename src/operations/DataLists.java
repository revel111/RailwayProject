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
    public static ArrayList<Rail> rails = new ArrayList<>();
    public static ArrayList<Rail> railsReversed = new ArrayList<>();

    public static ArrayList<Rail> getRails() {
        return rails;
    }

    public static ArrayList<Rail> getRailsReversed() {
        return railsReversed;
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
        rails = new ArrayList<>();
        railsReversed = new ArrayList<>();
    }

    public static String printCarsT(Trainset trainset) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getCars().size(); i++)
            string.append(trainset.getCars().get(i).toString()).append("\n");

        return string.toString();
    }

    public static <T> void printData(ArrayList<T> arrayList) {
        System.out.println();
        for (T t : arrayList)
            System.out.println(t.toString());
        System.out.println();
    }

    public static void sortTrainsetsByDistance() {
        DataLists.getTrainsets().sort(new Comparator<Trainset>() {
            @Override
            public int compare(Trainset trainset1, Trainset trainset2) {
                return Double.compare(trainset1.getWholeDistance(), trainset2.getWholeDistance());
            }
        });
    }
}