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

    public static void createRail() {
        DataLists.printData(DataLists.getStations());
        System.out.println("Enter id of a first station");
        String id1 = scanner.nextLine();
        Station station1 = Station.findStationByID(id1);
        System.out.println("Enter id of a second station");
        String id2 = scanner.nextLine();
        Station station2 = Station.findStationByID(id2);
        System.out.println("Enter distance between stations");
        double distance = scanner.nextDouble();
        scanner.nextLine();

        if (station1 == null || station2 == null || distance < 0) {
            System.out.println("Wrong input");
            System.out.println("Enter 1 if you want to try to create rail again");
            System.out.println("Enter 0 if you want to stop creating rail");
            String ch = scanner.nextLine();
            if (ch.equals("1"))
                createRail();
            return;
        }

        Rail rail = new Rail(station1, station2, distance);
        Rail railReversed = new Rail(station2, station1, distance);

        Rail toFind = ifContainsv2(rail);
        Rail toFindReversed = ifContainsReversedv2(railReversed);

        if (toFind != null || toFindReversed != null) {
            System.out.println("This rail already exist");
            return;
        }

        rail.station1.getIntersections().add(rail.station2);
        rail.station2.getIntersections().add(rail.station1);
        DataLists.getRails().add(rail);
        DataLists.getRailsReversed().add(railReversed);
    }

    public static Rail findRailById(String id) {
        for (int i = 0; i < DataLists.getRails().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getRails().get(i).getCurrentId()))
                return DataLists.getRails().get(i);
        return null;
    }

    public static void deleteRail() {
        System.out.println("Enter id of a rail to delete");
        DataLists.printData(DataLists.getRails());
        String id = scanner.nextLine();
        Rail rail = findRailById(id);

        if (rail == null) {
            System.out.println("There is no rail with id " + id);
            System.out.println("Enter 1 if you want to try to delete rail again");
            System.out.println("Enter something else if you want to stop deleting rail");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                findRailById(id);
            return;
        }

        for (int i = 0; i < DataLists.getTrainsets().size(); i++) {
            if (DataLists.getTrainsets().get(i).getRouteRails() == null)
                continue;
            for (int j = 0; j < DataLists.getTrainsets().get(i).getRouteRails().size(); j++) {
                if (DataLists.getTrainsets().get(i).getRouteRails().get(j) == rail) {
                    System.out.println("We can not delete rail because it is in a route, we stopped the trainset, so try again later to delete");
                    DataLists.getTrainsets().get(i).setAvailable(false);
                    return;
                }
            }
        }

        Rail railReversed = new Rail(rail.getStation2(), rail.getStation1(), 0);
        railReversed = Rail.ifContainsReversed(railReversed);
        Station temp = rail.getStation1();
        rail.getStation1().getIntersections().remove(rail.getStation2());
        rail.getStation2().getIntersections().remove(temp);

        DataLists.getRailsReversed().remove(railReversed);
        DataLists.getRails().remove(rail);
    }

    public static void deleteRails(Station station) {
        for (int i = 0; i < DataLists.getRails().size(); i++)
            if (DataLists.getRails().get(i).getStation1().getName().equals(station.getName()) || DataLists.getRails().get(i).getStation2().getName().equals(station.getName()))
                DataLists.getRails().remove(i);

        for (int i = 0; i < DataLists.getRailsReversed().size(); i++)
            if (DataLists.getRailsReversed().get(i).getStation1().getName().equals(station.getName()) || DataLists.getRailsReversed().get(i).getStation2().getName().equals(station.getName()))
                DataLists.getRailsReversed().remove(i);
    }


    public static Rail ifContains(Rail rail) { // extra function for finding rails by names
        for (Rail railI : DataLists.getRails())
            if (railI.isEqual(rail))
                return railI;
        return null;
    }

    public static Rail ifContainsReversed(Rail rail) { // extra function for finding reversed rails by names
        for (Rail railI : DataLists.getRailsReversed())
            if (railI.isEqual(rail))
                return railI;
        return null;
    }

    public static Rail ifContainsv2(Rail rail) { //overridden function to fix bug
        for (Rail railI : DataLists.getRails())
            if (railI.isEquiality(rail))
                return railI;
        return null;
    }

    public static Rail ifContainsReversedv2(Rail rail) { //overridden function to fix bug
        for (Rail railI : DataLists.getRailsReversed())
            if (railI.isEquiality(rail))
                return railI;
        return null;
    }

    public static void createRailsRandomly() { // random connections generation
        Station.generateRandomStation();
        for (int i = 0; i < DataLists.getStations().size(); i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(6) + 5;
            int maxConnections = randomNumber;

            for (int j = 0; j < randomNumber; j++) {
                int randomStation = random.nextInt(DataLists.getStations().size()) + 0;
                double randomValue = 50 + (700 - 50) * random.nextDouble();
                if (DataLists.getStations().get(i).getConnection() == maxConnections)
                    break;
                else {
                    Rail rail = new Rail(DataLists.getStations().get(i), DataLists.getStations().get(randomStation), randomValue);
                    Rail railReversed = new Rail(DataLists.getStations().get(randomStation), DataLists.getStations().get(i), randomValue);
                    Rail toFind = ifContains(rail);
                    Rail toFindReversed = ifContainsReversed(railReversed);
                    if (DataLists.getStations().get(i) == DataLists.getStations().get(randomStation) || toFind != null || toFindReversed != null || DataLists.getStations().get(randomStation).getIntersections().size() > 10)
                        randomNumber++;
                    else {
                        rail.station1.getIntersections().add(rail.station2);
                        rail.station2.getIntersections().add(rail.station1);
                        DataLists.getRails().add(rail);
                        DataLists.getRailsReversed().add(railReversed);
                        DataLists.getStations().get(i).setConnection(DataLists.getStations().get(i).getConnection() + 1);
                        DataLists.getStations().get(randomStation).setConnection(DataLists.getStations().get(randomStation).getConnection() + 1);
                    }
                }
            }
        }
    }

    public boolean isEquiality(Rail rail) { //check if rails are the same
        if ((this.getStation1() == rail.getStation1() && this.getStation2() == rail.getStation2()) || (this.getStation1() == rail.getStation2() && this.getStation2() == rail.getStation1()))
            return true;
        return false;
    }

    public boolean isEqual(Rail rail) { //check if rails are the same
        if ((rail.getStation1().getName().equals(this.getStation1().getName()) && rail.getStation2().getName().equals(this.getStation2().getName())))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Id: " + currentId + " Station 1: " + station1 + " Station 2: " + station2 + " Distance: " + distance;
    }
}