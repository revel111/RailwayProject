package customVariables;

import operations.DataLists;

public class Rail {
    private double leftDistance;
    private Station station1;
    private Station station2;
    private final double distance;

    public Rail(Station station1, Station station2, double distance) {
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
        DataLists.getRails().add(this);
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

    public static void createRail(Trainset trainset) {
        Trainset.generateRoute(trainset);

        for (int i = 0; i < trainset.getRouteStations().size(); i++) {

        }
    }

    @Override
    public String toString() {
        return "Station 1: " + station1 + " Station 2: " + station2 + " Distance: " + distance;
    }
}