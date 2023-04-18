package customVariables;

import operations.DataLists;
import operations.Files;

import java.util.*;

public class Station {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private static int id = 0;
    private ArrayList<Station> intersections = new ArrayList<>();
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

    public ArrayList<Station> getIntersections() {
        return intersections;
    }

    public void setIntersections(ArrayList<Station> intersections) {
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
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        Station station = new Station(name);
    }

//    public static void createIntersection(Station stationTo, Station station) {
//        stationTo.getIntersections().add(station);
//        station.getIntersections().add(stationTo);
//    }

    public static void deleteStation() {
        System.out.println("Deleting statiob");
        System.out.println("Enter id of a station");
        DataLists.printData(DataLists.getStations());
        String id = scanner.nextLine();
        Station station = findStationByID(id);

        if (station == null) {
            System.out.println("There is no station with id " + id);
            System.out.println("Enter 1 if you want to try to delete station again");
            System.out.println("Enter something else if you want to stop deleting station");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                deleteStation();
            else
                return;
        }

        for (int i = 0; i < DataLists.getTrainsets().size(); i++) {
            for (int j = 0; j < DataLists.getTrainsets().get(i).getRouteStations().size(); j++) {
                if (DataLists.getTrainsets().get(i).getRouteStations().get(j) == station) {
                    System.out.println("We can not delete station because it is in a route");
                    return;
                }
            }
        }

        station.removeConnections();
//        for (int i = 0; i < station.getIntersections().size(); i++) {
//            Rail rail = new Rail(station, station.getIntersections().get(i), 0);
//            Rail railReversed = new Rail(station.getIntersections().get(i), station, 0);
//            Rail toFind = Rail.ifContains(rail);
//            Rail toFindReversed = Rail.ifContainsReversed(railReversed);
//            if (toFind != null) {
//                DataLists.getRails().remove(toFind);
//                DataLists.getRailsReversed().remove(toFindReversed);
//            }
//        }

        DataLists.getStations().remove(station);
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

    public void removeConnections() {
        for (int i = 0; i < this.getIntersections().size(); i++)
            this.getIntersections().get(i).getIntersections().remove(this);
        this.setIntersections(null);
    }

    public static Station createStationIfNull(String stringName) {
        System.out.println("Enter 1 if you want to create station with name " + stringName);
        System.out.println("Enter 0 if you want to stop creating station");
        String ch = scanner.nextLine();
        Station station = null;

        switch (ch) {
            case "1":
                station = new Station(stringName);
                DataLists.getStations().add(station);
                break;
            case "0":
                System.out.println("You stopped creating station");
                return null;
            default:
                System.out.println("Wrong input");
                System.out.println("Enter 1 if you want to try to create station again");
                System.out.println("Enter something else if you want to stop creating station");
                String ch1 = scanner.nextLine();

                if (ch1.equals("1"))
                    Station.createStationIfNull(stringName);
                else
                    return null;
        }
        return station;
    }

    public static void generateRandomStation() {
        Station station = null;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String string = Files.ReadFileStations(set);
        }
    }

    public static Station chooseRandomStation() {
        Random random = new Random();
        Station station = null;
        int randomValue = random.nextInt(DataLists.getStations().size()) + 0;

        return DataLists.getStations().get(randomValue);
    }

    @Override
    public String toString() {
        return "Name: " + name + " ID: " + currentId;
    }
}
