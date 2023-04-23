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
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        for (int i = 0; i < DataLists.getStations().size(); i++) {
            if (DataLists.getStations().get(i).getName().equals(name)) {
                System.out.println("There is station with such name ");
                System.out.println("Enter 1 if you want to try to create station again");
                System.out.println("Enter something else if you want to stop creating station");
                String ch = scanner.nextLine();

                if (ch.equals("1"))
                    createStation();
                return;
            }
        }

        Station station = new Station(name);
    }

    public static void deleteStation() {
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
            return;
        }

        for (int i = 0; i < DataLists.getTrainsets().size(); i++) {
            if (DataLists.getTrainsets().get(i).getRouteStations() == null)
                continue;
            else if (DataLists.getTrainsets().get(i).getLocomotive().getDestinationStation() == station || DataLists.getTrainsets().get(i).getLocomotive().getSourceStation() == station) {
                System.out.println("We can not delete this station when it is source or destination station of a trainset");
                return;
            }
            for (int j = 0; j < DataLists.getTrainsets().get(i).getRouteStations().size(); j++) {
                if (DataLists.getTrainsets().get(i).getRouteStations().get(j) == station) {
                    System.out.println("We can not delete station because it is in a route, we stopped the trainset, so try again later to delete");
                    DataLists.getTrainsets().get(i).setAvailable(false);
                    return;
                }
            }
        }

        for (int i = 0; i < station.getIntersections().size(); i++)
            station.getIntersections().get(i).getIntersections().remove(station);
        station.setIntersections(null);

        Rail.deleteRails(station);
        DataLists.getStations().remove(station);
    }

    public static Station findStationByID(String id) {
        for (int i = 0; i < DataLists.getStations().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getStations().get(i).getCurrentId()))
                return DataLists.getStations().get(i);
        return null;
    }

//    public static Station createStationIfNull(String stringName) { //create station if it does not exist
//        System.out.println("Enter 1 if you want to create station with name " + stringName);
//        System.out.println("Enter 0 if you want to stop creating station");
//        String ch = scanner.nextLine();
//        Station station = null;
//
//        for (int i = 0; i < DataLists.getStations().size(); i++)
//            if (DataLists.getStations().get(i).getName().equalsIgnoreCase(stringName))
//                return null;
//
//        switch (ch) {
//            case "1" -> {
//                station = new Station(stringName);
//            }
//            case "0" -> {
//                System.out.println("You stopped creating station");
//                return null;
//            }
//            default -> {
//                System.out.println("Wrong input");
//                System.out.println("Enter 1 if you want to try to create station again");
//                System.out.println("Enter something else if you want to stop creating station");
//                String ch1 = scanner.nextLine();
//                if (ch1.equals("1"))
//                    Station.createStationIfNull(stringName);
//                return null;
//            }
//        }
//        return station;
//    }

    public static void generateRandomStation() { // random stations generation
        Station station = null;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Files.readFileStations(set);
        }
    }

    public static Station chooseRandomStation() { //choose random station for locomotive
        Random random = new Random();
        int randomValue = random.nextInt(DataLists.getStations().size()) + 0;

        return DataLists.getStations().get(randomValue);
    }

    @Override
    public String toString() {
        return "Name: " + name + " ID: " + currentId;
    }
}