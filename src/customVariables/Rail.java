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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rail otherRail))
            return false;

        //        Rail otherRail = (Rail) o;
        return this.station1.equals(otherRail.station1) && this.station2 == otherRail.station2;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + station1.hashCode();
        result = 31 * result + station2.hashCode();
        return result;
    }

    public static void createRail(Trainset trainset) {
        Trainset.generateRoute(trainset);
        ArrayList<Rail> rails = new ArrayList<>();

        for (int i = 0; i < trainset.getRouteStations().size(); i++) {
            Random random = new Random();
            double randomVal = Math.round(50 + (1000 - 50) * random.nextDouble() * 100.0) / 100.0;
            Station station1 = trainset.getRouteStations().get(i);

            if (station1 == trainset.getLocomotive().getDestinationStation()) {
                trainset.setRouteRails(rails);
                return;
            }

            Station station2 = trainset.getRouteStations().get(i + 1);
            Rail rail = new Rail(station1, station2, randomVal);

            Rail toFind = ifContains(rail);
            if (toFind != null) {
                rail = toFind;
                rails.add(rail);
                trainset.setDistance(trainset.getDistance() + rail.distance);
            } else {
                rails.add(rail);
                trainset.setDistance(trainset.getDistance() + rail.distance);
                DataLists.getRails().add(rail);
            }
        }
    }

    public static Rail ifContains(Rail rail) {
        for (Rail railI : DataLists.getRails())
            if (railI.equals(rail))
                return railI;
        return null;
    }

//    public static Rail ifContains(Rail rail) {
//        for (Rail railI : DataLists.getRails())
//            if (railI.isEqual(rail))
//                return railI;
//        return null;
//    }

    public static void createRailsRandomly() {
        Station.generateRandomStation();
        for (int i = 0; i < DataLists.getStations().size(); i++) {
            Random random = new Random();

            int randomNumber = random.nextInt(6) + 5;

            if (i == 100)
                return;
            else {
                for (int j = 0; j < randomNumber; j++) {
                    int randomStation = random.nextInt(100) + 0;
                    double randomValue = 50 + (1000 - 50) * random.nextDouble();
                    if (DataLists.getStations().get(i).getConnection() == randomNumber)
                        break;
                    else {
                        Rail rail = new Rail(DataLists.getStations().get(i), DataLists.getStations().get(randomStation), randomValue);
                        Rail toFind = ifContains(rail);
                        if (toFind != null)
                            j--;
                        else {
                            DataLists.getRails().add(rail);
                            DataLists.getStations().get(i).setConnection(DataLists.getStations().get(i).getConnection() + 1);
                        }
                    }
                }
            }
        }
    }

    public boolean isEqual(Rail rail) {
        if ((rail.getStation1().getName().equals(this.getStation1().getName()) && rail.getStation2().getName().equals(this.getStation2().getName())) || (rail.getStation1().getName().equals(this.getStation2().getName()) && rail.getStation2().getName().equals(this.getStation1().getName())))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Station 1: " + station1 + " Station 2: " + station2 + " Distance: " + distance;
    }
}