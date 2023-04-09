package customVariables;

import operations.DataLists;

import java.util.ArrayList;
import java.util.Scanner;

public class Locomotive {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private double speed = 100;
    private int curElecRailRoad;
    private Station homeStation;
    private Station sourceStation;
    private Station destinationStation;
    private static int id = 0;
    private String currentId;
    private final int maxElectGrid = 5;

    public Locomotive(String name, Station homeStation, Station sourceStation, Station destinationStation) {
        this.name = name;
        this.homeStation = homeStation;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        currentId = "loc" + id++;
    }

    public static int getId() {
        return id;
    }

    public String getCurrentId() {
        return currentId;
    }

    public String getName() {
        return name;
    }

    public int getMaxElectGrid() {
        return maxElectGrid;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCurElecRailRoad() {
        return curElecRailRoad;
    }

    public Station getHomeStation() {
        return homeStation;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setCurElecRailRoad(int curElecRailRoad) {
        this.curElecRailRoad = curElecRailRoad;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    public void setHomeStation(Station homeStation) {
        this.homeStation = homeStation;
    }

    public void setSourceStation(Station sourceStation) {
        this.sourceStation = sourceStation;
    }

    public static void createLocomotive() {
        System.out.println("Adding new Locomotive");
        System.out.println("Enter name: ");
        String name = scanner.nextLine();


        System.out.println("Enter locomotive's home station's id");
        String shomeStation = scanner.nextLine();

        Station homeStation;
        Station sourceStation;
        Station destinationStation;

        homeStation = Station.findStationByID(shomeStation);

        if (homeStation == null) {
            homeStation = Station.createStationIfNull(shomeStation);
            if (homeStation == null)
                return;
        }

        System.out.println("Enter locomotive's source station's id");
        String ssourceStation = scanner.nextLine();

        sourceStation = Station.findStationByID(ssourceStation);

        if (sourceStation == null) {
            sourceStation = Station.createStationIfNull(ssourceStation);
            if (sourceStation == null)
                return;
        }

        System.out.println("Enter locomotive's destination station's id");
        String sdestinationStation = scanner.nextLine();

        destinationStation = Station.findStationByID(sdestinationStation);

        if (destinationStation == null) {
            destinationStation = Station.createStationIfNull(sdestinationStation);
            if (destinationStation == null)
                return;
        }

        Locomotive locomotive = new Locomotive(name, homeStation, sourceStation, destinationStation);
        DataLists.getLocomotives().add(locomotive);
    }

    public static Locomotive createLocomotive(boolean f) { //redefined
        System.out.println("Adding new Locomotive");
        System.out.println("Enter name: ");
        String name = scanner.nextLine();


        System.out.println("Enter locomotive's home station's id");
        String shomeStation = scanner.nextLine();

        Station homeStation;
        Station sourceStation;
        Station destinationStation;

        homeStation = Station.findStationByID(shomeStation);

        if (homeStation == null) {
            homeStation = Station.createStationIfNull(shomeStation);
            if (homeStation == null)
                return null;
        }

        System.out.println("Enter locomotive's source station's id");
        String ssourceStation = scanner.nextLine();

        sourceStation = Station.findStationByID(ssourceStation);

        if (sourceStation == null) {
            sourceStation = Station.createStationIfNull(ssourceStation);
            if (sourceStation == null)
                return null;
        }

        System.out.println("Enter locomotive's destination station's id");
        String sdestinationStation = scanner.nextLine();

        destinationStation = Station.findStationByID(sdestinationStation);

        if (destinationStation == null) {
            destinationStation = Station.createStationIfNull(sdestinationStation);
            if (destinationStation == null)
                return null;
        }

        Locomotive locomotive = new Locomotive(name, homeStation, sourceStation, destinationStation);
        DataLists.getLocomotives().add(locomotive);

        return locomotive;
    }

    public static void deleteLocomotiveById(String id) {
        for (int i = 0; i < DataLists.getLocomotives().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getLocomotives().get(i).getCurrentId()))
                DataLists.getLocomotives().remove(i);
    }

    public static Locomotive findLocomotiveById(String id) {
        for (int i = 0; i < DataLists.getLocomotives().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getLocomotives().get(i).getCurrentId()))
                return DataLists.getLocomotives().get(i);
        return null;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Id: " + currentId + "; Current amount of electricity: " + curElecRailRoad + "; Home station: " + homeStation.getName();
    }
}