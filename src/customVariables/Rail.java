package customVariables;

import operations.DataLists;

import java.util.ArrayList;

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
        if (!(o instanceof Rail))
            return false;

        Rail otherRail = (Rail) o;
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
            double distance = Math.floor(Math.random() * 951) + 50;
            Station station1 = trainset.getRouteStations().get(i);

            if (station1 == trainset.getLocomotive().getDestinationStation()) {
                trainset.setRouteRails(rails);
                return;
            }

            Station station2 = trainset.getRouteStations().get(i + 1);

            Rail rail = new Rail(station1, station2, distance);

            Rail toFind = ifContains(rail);
            if (toFind != null) {
                rail = toFind;
                rails.add(rail);
            } else {
                rails.add(rail);
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

    @Override
    public String toString() {
        return "Station 1: " + station1 + " Station 2: " + station2 + " Distance: " + distance;
    }
}