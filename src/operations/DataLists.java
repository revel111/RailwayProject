package operations;

import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import java.util.HashMap;

public class DataLists {
    private static ArrayList<Locomotive> locomotives;
    private static ArrayList<Station> stations;
    private static ArrayList<Car> cars;
    private static ArrayList<Trainset> trainsets;

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

//    public static String printRoute(Trainset trainset) {
//        StringBuilder string = new StringBuilder();
//
//        for (int i = 0; i < trainset.getRouteStations().size(); i++)
//            string.append(trainset.getRouteStations().get(i).toString()).append("\n");
//
//        return string.toString();
//    }

    public static String printRouteSet(Trainset trainset) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < trainset.getRouteRails().size(); i++)
            string.append(trainset.getRouteRails().get(i).toString()).append("\n");

        return string.toString();
    }

    public static <T> void printData(ArrayList<T> arrayList) {
        for (T t : arrayList)
            System.out.println(t.toString());
        System.out.println();
    }

    public static void sortTrainsetsByDistance() {
        Collections.sort(DataLists.getTrainsets(), new Comparator<Trainset>() {
            @Override
            public int compare(Trainset trainset1, Trainset trainset2) {
                return Double.compare(trainset1.getDistance(), trainset2.getDistance());
            }
        });
    }

    public static String ReadFile(String type, int number) {
        String fileName = "src/txtfiles" + type;
        int randomNum = (int) (Math.random() * (number - 0 + 1) + 0);//     121 station
        String name = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int currentLine = 1;
            while ((name = br.readLine()) != null) {
                if (currentLine == randomNum) {
                    System.out.println(name);
                    break;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }
}