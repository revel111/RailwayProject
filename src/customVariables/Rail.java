package customVariables;

import operations.DataLists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Rail {
    private double leftDistance;
    private Station station1;
    private Station station2;
    private final double distance;

    public Rail(Station station1, Station station2, double distance) {
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
    }

    public double getLeftDistance() {
        return leftDistance;
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

//    @Override
//    public boolean equals(Object o) {
//        if (o == this)
//            return true;
//        if (!(o instanceof Rail otherRail))
//            return false;
//
//        //        Rail otherRail = (Rail) o;
//        return this.station1.equals(otherRail.station1) && this.station2 == otherRail.station2;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + station1.hashCode();
//        result = 31 * result + station2.hashCode();
//        return result;
//    }

//    public static Rail ifContains(Rail rail) {
//        for (Rail railI : DataLists.getRails())
//            if (railI.equals(rail))
//                return railI;
//        return null;
//    }

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
            if (i == 100)
                return;
            else {
                for (int j = 0; j < randomNumber; j++) {
                    int randomStation = random.nextInt(100) + 0;
                    double randomValue = 50 + (1000 - 50) * random.nextDouble();
                    if (DataLists.getStations().get(i).getConnection() == maxConnections)
                        break;
                    else {
                        Rail rail = new Rail(DataLists.getStations().get(i), DataLists.getStations().get(randomStation), randomValue);
                        Rail toFind = ifContains(rail);
                        Rail toFindReversed = ifContainsReversed(rail);
                        if (toFind != null)
                            randomNumber++;
                        else if (toFindReversed != null)
                            randomNumber++;
                        else {
                            Rail railReversed = new Rail(DataLists.getStations().get(randomStation), DataLists.getStations().get(i), randomValue);
                            DataLists.getRails().add(rail);
                            DataLists.getRailsReversed().add(railReversed);
                            DataLists.getStations().get(i).setConnection(DataLists.getStations().get(i).getConnection() + 1);
                        }
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