package customVariables;

import customVariables.customCars.Car;
import operations.DataLists;
import operations.Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Station {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private static int id = 0;
    private Set<Station> intersections = new HashSet<>();
    private String currentId;
    private int connection = 0;

    public Station(String name) {
        this.name = name;
        currentId = "st" + id++;
        DataLists.getStations().add(this);
    }

    public String getCurrentId() {
        return currentId;
    }

    public String getName() {
        return name;
    }

    public Set<Station> getIntersections() {
        return intersections;
    }

    public void setIntersections(Set<Station> intersections) {
        this.intersections = intersections;
    }

    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void createStation() {
        System.out.println("Adding new station");
        System.out.println("Type name: ");
        String name = scanner.nextLine();

        Station station = new Station(name);
    }

    public static void createInterestion(Station stationTo, Station station) {
        stationTo.getIntersections().add(station);
    }

    public static void deleteStationById(String id) {
        for (int i = 0; i < DataLists.getStations().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getStations().get(i).getCurrentId()))
                DataLists.getStations().remove(i);
    }

    public static Station findStationByID(String id) {
        for (int i = 0; i < DataLists.getStations().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getStations().get(i).getCurrentId()))
                return DataLists.getStations().get(i);
        return null;
    }

    public static Station createStationIfNull(String stringName) {
        System.out.println("Enter 1 if you want to create station with name " + stringName);
        System.out.println("Enter 0 if you want to stop creating locomotive");
        String ch = scanner.nextLine();
        Station station = null;

        switch (ch) {
            case "1":
                station = new Station(stringName);
                DataLists.getStations().add(station);
                break;
            case "0":
                System.out.println("You stopped creating locomotive");
                return null;
            default:
                System.out.println("You entered wrong value");
                System.out.println("Enter 1 if you want to try to create station again");
                System.out.println("Enter 0 if you want to stop creating locomotive");
                ch = scanner.nextLine();
                switch (ch) {
                    case "1":
                        createStationIfNull(stringName);
                        break;
                    case "0":
                        return null;
                }
        }
        return station;
    }

    public static void generateRandomStation() {
        Station station = null;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            String string = Files.ReadFileStations(set);
        }
    }

    public static Station chooseRandomStation() {
        Random random = new Random();
        Station station = null;
        int randomValue = random.nextInt(100) + 0;

        return DataLists.getStations().get(randomValue);
    }

    @Override
    public String toString() {
        return "Name: " + name + " ID: " + currentId;
    }
}
