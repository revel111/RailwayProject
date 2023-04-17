package customVariables;

import operations.DataLists;

import java.util.*;

public class Rail {
    private static final Scanner scanner = new Scanner(System.in);
    private double leftDistance;
    private Station station1;
    private Station station2;
    private boolean isAvailable;
    private final double distance;
    private static int id = 0;
    private String currentId;


    public Rail(Station station1, Station station2, double distance) {
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
        this.isAvailable = true;
        currentId = "ra" + id++;
        station1.getIntersections().add(station2);
        station2.getIntersections().add(station1);
    }

    public String getCurrentId() {
        return currentId;
    }

    public double getLeftDistance() {
        return leftDistance;
    }

    public boolean getisAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setLeftDistance(double leftDistance) {
        this.leftDistance = leftDistance;
    }

    public Station getStation1() {
        return station1;
    }

    public void setStation1(Station station1) {
        this.station1 = station1;
    }

    public Station getStation2() {
        return station2;
    }

    public void setStation2(Station station2) {
        this.station2 = station2;
    }

    public double getDistance() {
        return distance;
    }

//    public static Rail ifContains(Rail rail) {
//        for (Rail railI : DataLists.getRails())
//            if (railI.equals(rail))
//                return railI;
//        return null;
//    }

    public static void createRail() {
        System.out.println("Enter id of a first station");
        String id1 = scanner.nextLine();
        Station station1 = Station.findStationByID(id1);
        System.out.println("Enter id of a second station");
        String id2 = scanner.nextLine();
        Station station2 = Station.findStationByID(id1);
        System.out.println("Enter distance between stations");
        double distance = scanner.nextDouble();
        scanner.nextLine();

        if (station1 == null || station2 == null) {
            System.out.println("Wrong input");
            System.out.println("Enter 1 if you want to try to create rail again");
            System.out.println("Enter 0 if you want to stop creating rail");
            String ch = scanner.nextLine();
            if (ch.equals("1"))
                createRail();
            else
                return;
        }

        Rail rail = new Rail(station1, station2, distance);
        Rail railRev = new Rail(station2, station1, distance);
        DataLists.getRails().add(rail);
        DataLists.getRailsReversed().add(railRev);
    }

    public static Rail findRailById(String id) {
        for (int i = 0; i < DataLists.getRails().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getRails().get(i).getCurrentId()))
                return DataLists.getRails().get(i);
        return null;
    }

    public static void deleteRailById(String id) {
        for (int i = 0; i < DataLists.getRails().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getRails().get(i).getCurrentId()))
                DataLists.getRails().remove(i);
    }

    public static void deleteRail() {
        System.out.println("Deleting rail");
        System.out.println("Enter id of a rail");
        String id = scanner.nextLine();
        Rail rail = findRailById(id);

        if (rail == null) {
            System.out.println("There is no rail with id " + id);
            System.out.println("Enter 1 if you want to try to delete rail again");
            System.out.println("Enter something else if you want to stop deleting rail");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                findRailById(id);
            else
                return;
        } else {
//            for (int i = 0; i < station.getIntersections().size(); i++) {
//                Rail rail = new Rail(station, station.getIntersections().get(i), 0);
//                Rail railReversed = new Rail(station.getIntersections().get(i), station, 0);
//                Rail toFind = Rail.ifContains(rail);
//                Rail toFindReversed = Rail.ifContainsReversed(railReversed);
//                if (toFind != null) {
//                    DataLists.getRails().remove(toFind);
//                    DataLists.getRailsReversed().remove(toFindReversed);
//                }
//            }
            rail.getStation1().getIntersections().remove(rail.getStation2());
            DataLists.getRails().remove(rail);
        }
    }

    public static void createRail(Station station1, Station station2, double distance) {
//        System.out.println("Enter id of a first station");
//        String id1 = scanner.nextLine();
//        Station station1 = Station.findStationByID(id1);
//        System.out.println("Enter id of a second station");
//        String id2 = scanner.nextLine();
//        Station station2 = Station.findStationByID(id1);
//        System.out.println("Enter distance between stations");
//        double distance = scanner.nextDouble();
//        scanner.nextLine();
//
//        if (station1 == null || station2 == null) {
//            System.out.println("Wrong input");
//            System.out.println("Enter 1 if you want to try to create rail again");
//            System.out.println("Enter 0 if you want to stop creating");
//            String ch = scanner.nextLine();
//            if (ch.equals("1"))
//                createRail();
//            else
//                return;
//        }

        Rail rail = new Rail(station1, station2, distance);
        Rail railRev = new Rail(station2, station1, distance);
        DataLists.getRails().add(rail);
        DataLists.getRailsReversed().add(railRev);
    }

    public static Rail ifContains(Rail rail) {
        for (Rail railI : DataLists.getRails())
            if (railI.isEqual(rail))
                return railI;
        return null;
    }

    public static Rail ifContainsReversed(Rail rail) {
        for (Rail railI : DataLists.getRailsReversed())
            if (railI.isEqual(rail))
                return railI;
        return null;
    }

    public static void createRailsRandomly() {
        Station.generateRandomStation();
        for (int i = 0; i < DataLists.getStations().size(); i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(6) + 5;
            int maxConnections = randomNumber;
//            DataLists.getStations().get(i).setMaxConnection(maxConnections-DataLists.getStations().get(i).g);

            for (int j = 0; j < randomNumber; j++) {
                int randomStation = random.nextInt(DataLists.getStations().size()) + 0;
                double randomValue = 50 + (400 - 50) * random.nextDouble();
                if (DataLists.getStations().get(i).getConnection() == maxConnections)
                    break;
                else {
                    Rail rail = new Rail(DataLists.getStations().get(i), DataLists.getStations().get(randomStation), randomValue);
                    Rail railReversed = new Rail(DataLists.getStations().get(randomStation), DataLists.getStations().get(i), randomValue);
                    Rail toFind = ifContains(rail);
                    Rail toFindReversed = ifContainsReversed(railReversed);
                    if (DataLists.getStations().get(i) == DataLists.getStations().get(randomStation) || toFind != null || toFindReversed != null)
                        randomNumber++;
                    else {
//                        Rail railReversed = new Rail(DataLists.getStations().get(randomStation), DataLists.getStations().get(i), randomValue);
                        DataLists.getRails().add(rail);
                        DataLists.getRailsReversed().add(railReversed);
                        DataLists.getStations().get(i).setConnection(DataLists.getStations().get(i).getConnection() + 1); //???
//                        DataLists.getStations().get(randomStation).setConnection(DataLists.getStations().get(randomStation).getConnection() + 1);
                    }
                }
            }
        }
    }

    public boolean isEqual(Rail rail) {
        if (rail.getStation1().getName().equals(this.getStation1().getName()) && rail.getStation2().getName().equals(this.getStation2().getName()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Station 1: " + station1 + " Station 2: " + station2 + " Distance: " + distance;
    }
}