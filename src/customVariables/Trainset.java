package customVariables;

import customVariables.customCars.Car;
import customVariables.customExtra.RailroadHazardException;
import customVariables.customExtra.TooManyCarsException;
import operations.DataLists;
import operations.Files;

import java.util.*;

public class Trainset implements Runnable {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private static int id = 0;
    private String currentId;
    private Locomotive locomotive;
    private ArrayList<Car> cars; //10
    private ArrayList<Station> routeStations;
    private ArrayList<Rail> routeRails;
    private double weight = 1000;
    private Rail currentRail;
    private final double maxWeight = 10000;
    private double wholeDistance = 0;
    private double currentDistance = 0;

    public Trainset(String name, Locomotive locomotive, ArrayList<Car> cars) {
        this.locomotive = locomotive;
        this.cars = cars;
        this.name = name;
        currentId = "trset" + id++;
    }

    public String getCurrentId() {
        return currentId;
    }

    public String getName() {
        return name;
    }

    public double getWholeDistance() {
        return wholeDistance;
    }

    public void setWholeDistance(double wholeDistance) {
        this.wholeDistance = wholeDistance;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public double getWeight() {
        return weight;
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(double currentDistance) {
        this.currentDistance = currentDistance;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Rail> getRouteRails() {
        return routeRails;
    }

    public Rail getCurrentRail() {
        return currentRail;
    }

    public void setCurrentRail(Rail currentRail) {
        this.currentRail = currentRail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ArrayList<Station> getRouteStations() {
        return routeStations;
    }

    public void setRouteStations(ArrayList<Station> routeStations) {
        this.routeStations = routeStations;
    }

    public void setRouteRails(ArrayList<Rail> routeRails) {
        this.routeRails = routeRails;
    }

    public static void createTrainset() throws TooManyCarsException {
        System.out.println("Enter 1 if you want to create new locomotive for trainset");
        System.out.println("Enter 2 if you want to choose particular locomotive for trainset");
        System.out.println("Enter 3 if you want to generate random locomotive for trainset");
        System.out.println("Enter 0 if you want to stop creating trainset");
        Locomotive locomotive = null;
        ArrayList<Car> cars = new ArrayList<Car>();

        String ch = scanner.nextLine();

        switch (ch) {
            case "1" -> {
                locomotive = Locomotive.createLocomotive(true);
                if (locomotive == null)
                    return;
            }
            case "2" -> {
                System.out.println("Enter locomotive's id");
                DataLists.printData(DataLists.getLocomotives());
                String id = scanner.nextLine();
                locomotive = Locomotive.findLocomotiveById(id);
                if (locomotive == null) {
                    System.out.println("Wrong input");
                    System.out.println("Enter 1 if you want to try to create trainset again");
                    System.out.println("Enter something else if you want to stop creating trainset");
                    String ch1 = scanner.nextLine();

                    if (ch1.equals("1"))
                        Trainset.createTrainset();
                    else
                        return;
                } else
                    Locomotive.deleteLocomotiveById(locomotive.getCurrentId());
            }
            case "3" -> locomotive = Locomotive.generateLocomotiveRandomly("locomotivenames.txt", 50);
            default -> {
                System.out.println("You stopped creating trainset");
                return;
            }
        }

        System.out.println("Enter name of a trainset: ");
        String name = scanner.nextLine();

        Trainset trainset = new Trainset(name, locomotive, cars);

        while (true) {
            System.out.println("Enter 1 if you want to create new car");
            System.out.println("Enter 2 if you want to choose particular car");
            System.out.println("Enter 3 if you want to randomly generate cars");
            System.out.println("Enter 0 if you want to stop creating trainset");
            Car car = null;
            ch = scanner.nextLine();

            switch (ch) {
                case "1" -> {
                    car = Car.createCarForTrainest();
                    if (car == null)
                        return;
                    try {
                        if (trainset.getWeight() + car.getWeightBrutto() >= trainset.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 2)
                            throw new TooManyCarsException();
                        else {
                            trainset.setWeight(car.getWeightBrutto() + trainset.getWeight());
                            if (car.isGridConnection())
                                locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
                            cars.add(car);
                        }
                    } catch (TooManyCarsException e) {
                        System.out.println(e.getMessage());
                        DataLists.getTrainsets().add(trainset);
                        return;
                    }
                }
                case "2" -> {
                    System.out.println("Enter car's id");
                    DataLists.printData(DataLists.getCars());
                    String id = scanner.nextLine();
                    car = Car.findCarById(id);
                    if (car == null) {
                        System.out.println("Wrong input");
                        System.out.println("Enter 1 if you want to try to create trainset again");
                        System.out.println("Enter something else if you want to stop creating trainset");
                        String ch1 = scanner.nextLine();

                        if (ch1.equals("1"))
                            Trainset.createTrainset();
                        else
                            return;
                    } else {
                        Car.deleteCarById(car.getCurrentId());
                        try {
                            if (trainset.getWeight() + car.getWeightBrutto() >= trainset.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 2)
                                throw new TooManyCarsException();
                            else {
                                trainset.setWeight(car.getWeightBrutto() + trainset.getWeight());
                                if (car.isGridConnection())
                                    locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
                                cars.add(car);
                            }
                        } catch (TooManyCarsException e) {
                            System.out.println(e.getMessage());
                            DataLists.getTrainsets().add(trainset);
                            return;
                        }
                    }
                }
                case "3" -> {
                    cars = null;
                    cars = Car.generateCarRandomly("shippingnames.txt", 100);
                    DataLists.getTrainsets().add(new Trainset(name, locomotive, cars));
                    return;
                }
                case "0" -> {
                    System.out.println("You stopped creating trainset");
                    return;
                }
                default -> {
                    System.out.println("Wrong input");
                    System.out.println("Enter 1 if you want to try to create trainset again");
                    System.out.println("Enter something else if you want to stop creating trainset");
                    String ch1 = scanner.nextLine();
                    if (ch1.equals("1"))
                        Trainset.createTrainset();
                    else
                        return;
                }
            }

            System.out.println("Enter 1 if you want to add one more car");
            System.out.println("Enter 0 if you want to create trainset");
            ch = scanner.nextLine();

            switch (ch) {
                case "1":
                    break;
                case "0":
                    DataLists.getTrainsets().add(trainset);
                    return;
                default:
                    System.out.println("Wrong input");
                    System.out.println("Enter 1 if you want to try to create trainset again");
                    System.out.println("Enter something else if you want to stop creating trainset");
                    String ch1 = scanner.nextLine();

                    if (ch1.equals("1"))
                        Trainset.createTrainset();
                    else
                        return;
            }
        }
    }

    public static void deleteTrainsetById(String id) {
        for (int i = 0; i < DataLists.getTrainsets().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getTrainsets().get(i).getCurrentId()))
                DataLists.getTrainsets().remove(i);
    }

    public static Trainset findTrainsetById(String id) {
        for (int i = 0; i < DataLists.getTrainsets().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getTrainsets().get(i).getCurrentId()))
                return DataLists.getTrainsets().get(i);
        return null;
    }

    public void sortCarsByWeight() {
        cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return Double.compare(car1.getWeightBrutto(), car2.getWeightBrutto());
            }
        });
    }

    public void generateRoute() {
        Set<Station> visited = new HashSet<>();
        ArrayList<Station> route = new ArrayList<>();

        Station start = this.getLocomotive().getSourceStation();
        Station end = this.getLocomotive().getDestinationStation();

        if (generateRouteFind(start, end, visited, route))
            this.setRouteStations(route);
    }

    public static boolean generateRouteFind(Station current, Station end, Set<Station> visited, ArrayList<Station> route) {
        visited.add(current);
        route.add(current);
        if (current == end)
            return true;
        for (Station station : current.getIntersections())
            if (!visited.contains(station))
                if (generateRouteFind(station, end, visited, route))
                    return true;

        route.remove(current);
        return false;
    }

    public static void generateTrainsetsRandomly() {
        for (int i = 0; i < 25; i++) {
            String string = Files.ReadFile("trainsetnames.txt", 50);
            Locomotive locomotive = Locomotive.generateLocomotiveRandomly("locomotivenames.txt", 50);
            ArrayList<Car> cars = Car.generateCarRandomly("shippingnames.txt", 100);
            DataLists.getTrainsets().add(new Trainset(string, locomotive, cars));
        }
    }

    public void createRail() {
        this.generateRoute();
        ArrayList<Rail> rails = new ArrayList<>();

        if (this.getRouteStations() == null) {
//            this.generateStationsForTrainset();
            return;
        }

        for (int i = 0; i < this.getRouteStations().size(); i++) {
            Random random = new Random();
            double randomVal = Math.round(50 + (1000 - 50) * random.nextDouble() * 100.0) / 100.0;
            Station station1 = this.getRouteStations().get(i);

            if (station1 == this.getLocomotive().getDestinationStation()) {
                this.setRouteRails(rails);
                return;
            }

            Station station2 = this.getRouteStations().get(i + 1);
            Rail rail = new Rail(station1, station2, randomVal);

            Rail toFind = Rail.ifContains(rail);
            Rail toFindReversed = Rail.ifContainsReversed(rail);
            if (toFind != null) {
                rail = toFind;
//                trainset.setDistance(trainset.getDistance() + rail.distance);
            } else if (toFindReversed != null) {
                rail = toFindReversed;
//                trainset.setDistance(trainset.getDistance() + rail.distance);
            } else {
//                trainset.setDistance(trainset.getDistance() + rail.distance);
                DataLists.getRails().add(rail);
                rail.getStation1().getIntersections().add(rail.getStation2()); // ready
                rail.getStation2().getIntersections().add(rail.getStation1());
                Rail railRev = new Rail(station2, station1, randomVal);
                DataLists.getRailsReversed().add(railRev);
            }
            rails.add(rail);
            this.setWholeDistance(this.getWholeDistance() + rail.getDistance());
        }
    }

    public void printRouteSet() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < getRouteRails().size(); i++)
            string.append(getRouteRails().get(i).toString()).append("\n");

        System.out.println(string);
    }

//    public void generateStationsForTrainset() {
//        Random random = new Random();
//        int randomStation1 = random.nextInt(DataLists.getStations().size() - 0) + 0;
//        if (this.getLocomotive().getDestinationStation() == null) {
//            if (this.getLocomotive().getSourceStation() == DataLists.getStations().get(randomStation1) || DataLists.getStations().get(randomStation1).getIntersections().size() == 0)
//                generateStationsForTrainset();
//            else
//                this.getLocomotive().setDestinationStation(DataLists.getStations().get(randomStation1));
//        } else {
//            int randomStation2 = random.nextInt(DataLists.getStations().size() - 0) + 0;
//            if (randomStation1 == randomStation2 || DataLists.getStations().get(randomStation2).getIntersections().size() == 0)
//                generateStationsForTrainset();
//            else {
//                this.getLocomotive().setSourceStation(DataLists.getStations().get(randomStation1));
//                this.getLocomotive().setDestinationStation(DataLists.getStations().get(randomStation2));
//            }
//        }
//    }

    @Override
    public void run() {
        Random random = new Random();
        if (routeRails == null) {
            this.createRail();
            if (this.getRouteRails() == null) {
                System.out.println("Stations are not connected");
                return;
            }
        }
        getLocomotive().setSpeed(200.0);

        for (int i = 0; i < routeRails.size(); i++) {
//            Rail temp = routeRails.get(i).findRailByRail();
            while (!routeRails.get(i).getisAvailable()) {
                try {
                    System.out.println(this.getName() + " is waiting...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.setCurrentRail(routeRails.get(i));
            routeRails.get(i).setAvailable(false);
//            Trainset.setRailIsAvailable(routeRails.get(i), false);
            double distanceBetween = routeRails.get(i).getDistance();
            double distanceTmp = distanceBetween;
            double distanceWhole = this.getWholeDistance();


            while (distanceBetween > 0) {
                distanceBetween -= this.getLocomotive().getSpeed();
//                this.setWholeDistance(distanceWhole - distanceBetween * 100 / this.getWholeDistance());
//                this.setCurrentDistance(distanceBetween * 100 / distanceTmp);
//
//                if(this.getWholeDistance() < 0)
//                    this.setWholeDistance(0);
////                if(this.getCurrentDistance() < 0)
////                    this.setCurrentDistance(0);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int randomNumber = random.nextInt(2) + 1;

                if (randomNumber == 1)
                    this.getLocomotive().setSpeed(this.getLocomotive().getSpeed() + this.getLocomotive().getSpeed() * 0.03);
                else
                    this.getLocomotive().setSpeed(this.getLocomotive().getSpeed() - this.getLocomotive().getSpeed() * 0.03);

                if (this.getLocomotive().getSpeed() > 200)
                    try {
                        throw new RailroadHazardException();
                    } catch (RailroadHazardException e) {
                        System.out.println(e.getMessage());
                        this.getLocomotive().setSpeed(100);
                        System.out.println(this);
                    }
            }
            routeRails.get(i).setAvailable(true);
//            Trainset.setRailIsAvailable(routeRails.get(i), true);
            if (this.getLocomotive().getDestinationStation() == routeRails.get(i).getStation2())
                System.out.println("Trainset " + this.getName() + " is on destination station " + this.getLocomotive().getDestinationStation().getName());
            else
                System.out.println("Trainset " + this.getName() + " is on station " + routeRails.get(i).getStation2().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        routeRails = null;
        routeStations = null;
        Station temp = this.getLocomotive().getSourceStation();
        this.getLocomotive().setSourceStation(this.getLocomotive().getDestinationStation());
        this.getLocomotive().setDestinationStation(temp);
//        this.generateStationsForTrainset();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.run();
    }

//    public static void setRailIsAvailable(Rail rail, boolean bool) {
//        for (int i = 0; i < DataLists.getRails().size(); i++)
//            if (rail == DataLists.getRails().get(i))
//                DataLists.getRails().get(i).setAvailable(bool);
//
//        for (int i = 0; i < DataLists.getRailsReversed().size(); i++)
//            if (rail == DataLists.getRailsReversed().get(i))
//                DataLists.getRailsReversed().get(i).setAvailable(bool);
//    }

    @Override
    public String toString() {
        String string = DataLists.printCarsT(this);
        return "Name: " + name + "; Id: " + currentId + "; Speed: " + locomotive.getSpeed() + "; Whole Distance %: " + wholeDistance + "; Current Distance " + currentDistance + "; Weight: " + weight + "\nLocomotive: " + "\n" + locomotive + "\nCars: " + "\n" + string;
    }
}