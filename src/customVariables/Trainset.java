package customVariables;

import customVariables.customCars.Car;
import customVariables.customExtra.RailroadHazardException;
import customVariables.customExtra.TooManyCarsException;
import customVariables.customExtra.TooManyException;
import operations.DataLists;
import operations.Files;

import java.util.*;

public class Trainset extends Thread {
    private static final Scanner scanner = new Scanner(System.in);
    private String nameT;
    private static int id = 0;
    private String currentId;
    private Locomotive locomotive;
    private ArrayList<Car> cars;
    private ArrayList<Station> routeStations;
    private ArrayList<Rail> routeRails;
    private double weight = 0;
    private boolean isAvailable = true;
    private Rail currentRail;
    private final double maxWeight = 15000;
    private double wholeDistance = 0;
    private double currentDistance = 0;

    public Trainset(String nameT, Locomotive locomotive, ArrayList<Car> cars) {
        this.locomotive = locomotive;
        this.cars = cars;
        this.nameT = nameT;
        currentId = "trset" + id++;
    }

    public String getNameT() {
        return nameT;
    }

    public String getCurrentId() {
        return currentId;
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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
        Locomotive locomotive;
        ArrayList<Car> cars = new ArrayList<>();

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
        trainset.setWeight(locomotive.getWeight());

        while (true) {
            System.out.println("Enter 1 if you want to create new car");
            System.out.println("Enter 2 if you want to choose particular car");
            System.out.println("Enter 3 if you want to randomly generate cars");
            System.out.println("Enter 0 if you want to stop creating trainset");
            Car car;
            ch = scanner.nextLine();

            switch (ch) {
                case "1" -> {
                    car = Car.createCarForTrainest();
                    if (car == null)
                        return;
                    try {
                        if (trainset.getWeight() + car.getWeightBrutto() >= trainset.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 10)
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
                            trainset.sortCarsByWeight();
                            DataLists.getTrainsets().add(trainset);
                            return;
                        }
                    }
                }
                case "3" -> {
                    cars = null;
                    cars = Car.generateCarRandomly("shippingnames.txt", 100);
                    Trainset trainset1 = new Trainset(name, locomotive, cars);
                    trainset1.setWeight(locomotive.getWeight());
                    for (int i = 0; i < cars.size(); i++) {
                        trainset1.setWeight(trainset1.getWeight() + cars.get(i).getWeightBrutto());
                        if (cars.get(i).isGridConnection())
                            locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
                    }
                    trainset1.sortCarsByWeight();
                    DataLists.getTrainsets().add(trainset1);
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
                    trainset.sortCarsByWeight();
                    DataLists.getTrainsets().add(trainset);
                    return;
                default:
                    System.out.println("Wrong input");
                    System.out.println("Enter 1 if you want to try to create trainset again");
                    System.out.println("Enter something else if you want to stop creating trainset");
                    String ch1 = scanner.nextLine();

                    if (ch1.equals("1"))
                        Trainset.createTrainset();
                    return;
            }
        }
    }

    public static void launchTrainset() { // launch trainset
        DataLists.printData(DataLists.getTrainsets());
        System.out.println("Enter id of a trainset");
        String id = scanner.nextLine();
        Trainset trainset = findTrainsetById(id);

        if (trainset == null) {
            System.out.println("Wrong input");
            System.out.println("Enter 1 if you want to try to launch trainset again");
            System.out.println("Enter something else if you want to stop launching trainset");
            String ch1 = scanner.nextLine();
            if (ch1.equals("1"))
                launchTrainset();
            return;
        }

        if (trainset.getIsAvailable()) {
            System.out.println("Train is already moving");
            System.out.println("Enter 1 if you want to try to launch trainset again");
            System.out.println("Enter something else if you want to stop launching trainset");
            String ch1 = scanner.nextLine();
            if (ch1.equals("1"))
                launchTrainset();
            return;
        }

        trainset.setAvailable(false);
        DataLists.getThreads().add(trainset);
        trainset.start();
    }

    public void attachCar() {
        System.out.println("Enter 1 if you want to create new car to attach");
        System.out.println("Enter 2 if you want to choose particular car");
        System.out.println("Enter 0 if you want to exit");
        String ch = scanner.nextLine();
        Car car = null;

        switch (ch) {
            case "1":
                car = Car.createCarForTrainest();
                if (car == null)
                    return;
                try {
                    if (this.getWeight() + car.getWeightBrutto() >= this.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 10)
                        throw new TooManyCarsException();
                    else {
                        this.setWeight(car.getWeightBrutto() + this.getWeight());
                        if (car.isGridConnection())
                            locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
                        cars.add(car);
                    }
                } catch (TooManyCarsException e) {
                    System.out.println(e.getMessage());
                    DataLists.getTrainsets().add(this);
                    return;
                }
            case "2":
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
                        this.attachCar();
                    return;
                } else {
                    Car.deleteCarById(car.getCurrentId());
                    try {
                        if (this.getWeight() + car.getWeightBrutto() >= this.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 10)
                            throw new TooManyCarsException();
                        else {
                            this.setWeight(car.getWeightBrutto() + this.getWeight());
                            if (car.isGridConnection())
                                locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
                            cars.add(car);
                        }
                    } catch (TooManyCarsException e) {
                        System.out.println(e.getMessage());
                        this.sortCarsByWeight();
                        DataLists.getTrainsets().add(this);
                        return;
                    }
                }
            case "0":
                System.out.println("You stopped attaching car");
                return;
            default:
                System.out.println("Wrong input");
                System.out.println("Enter 1 if you want to try to attach car again");
                System.out.println("Enter something else if you want to stop attaching car");
                String ch1 = scanner.nextLine();
                if (ch1.equals("1"))
                    this.attachCar();
        }
    }

    public void unhookCar() {
        System.out.println("Enter id of car which you want to unhook");
        String string = DataLists.printCarsT(this);
        System.out.println(string);
        String id = scanner.nextLine();
        Car car = null;

        for (int i = 0; i < this.getCars().size(); i++)
            if (this.getCars().get(i).getCurrentId().equals(id))
                car = getCars().get(i);

        if (car == null) {
            System.out.println("Wrong input");
            System.out.println("Enter 1 if you want to try to create trainset again");
            System.out.println("Enter something else if you want to stop creating trainset");
            String ch1 = scanner.nextLine();

            if (ch1.equals("1"))
                this.unhookCar();
            return;
        }

        this.setWeight(this.getWeight() - car.getWeightBrutto());
        if (car.isGridConnection())
            this.getLocomotive().setCurElecRailRoad(this.getLocomotive().getCurElecRailRoad() - 1);
        this.getCars().remove(car);
        this.sortCarsByWeight();
    }

    public static void deleteTrainset() {
        System.out.println("Enter id of a trainset you want to delete");
        DataLists.printData(DataLists.getTrainsets());
        String id = scanner.nextLine();
        Trainset trainset = findTrainsetById(id);

        if (trainset == null) {
            System.out.println("There is no trainset with id " + id);
            System.out.println("Enter 1 if you want to try to delete trainset again");
            System.out.println("Enter something else if you want to stop deleting trainset");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                deleteTrainset();
            return;
        }
        if (trainset.isAlive())
            trainset.setAvailable(false);

        DataLists.getThreads().remove(trainset);
        DataLists.getTrainsets().remove(trainset);
    }

    public static Trainset findTrainsetById(String id) {
        for (int i = 0; i < DataLists.getTrainsets().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getTrainsets().get(i).getCurrentId()))
                return DataLists.getTrainsets().get(i);
        return null;
    }

    public static void manageTrainset() throws TooManyException { // function which allows us to deal with cars
        System.out.println("Enter id of a trainset you want to manage");
        DataLists.printData(DataLists.getTrainsets());
        String id = scanner.nextLine();
        Trainset trainset = findTrainsetById(id);

        if (trainset == null) {
            System.out.println("There is no trainset with id " + id);
            System.out.println("Enter 1 if you want to try to manage trainset again");
            System.out.println("Enter something else if you want to stop managing trainset");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                manageTrainset();
            return;
        }

        if (trainset.getRouteRails() == null) {

            while (true) {
                System.out.println("Enter 1 if you want to attach car");
                System.out.println("Enter 2 if you want unhook car");
                System.out.println("Enter 3 if you want deal with car");
                System.out.println("Enter 0 if you want stop managing trainset");
                String ch = scanner.nextLine();
                switch (ch) {
                    case "1" -> trainset.attachCar();
                    case "2" -> trainset.unhookCar();
                    case "3" -> {
                        System.out.println("Enter id of a car you want to deal");
                        String string = DataLists.printCarsT(trainset);
                        System.out.println(string);
                        ch = scanner.nextLine();
                        Car car = null;
                        for (int i = 0; i < trainset.getCars().size(); i++)
                            if (trainset.getCars().get(i).getCurrentId().equals(ch))
                                car = trainset.getCars().get(i);
                        if (car == null) {
                            System.out.println("Wrong input");
                            System.out.println("Enter 1 if you want to try to manage trainset again");
                            System.out.println("Enter something else if you want to stop managing station");
                            String ch1 = scanner.nextLine();
                            if (ch1.equals("1"))
                                Trainset.manageTrainset();
                            return;
                        }
                        System.out.println("Choose what to do with car");
                        System.out.println("Enter 1 if you want to fill car");
                        System.out.println("Enter 2 if you want to empty car");
                        System.out.println("Enter 0 if you stop car");
                        ch = scanner.nextLine();
                        switch (ch) {
                            case "1" -> {
                                car.fillCar();
                                trainset.setWeight(trainset.getWeight() + car.getWeightBrutto());
                            }
                            case "2" -> {
                                car.emptyCar();
                                trainset.setWeight(trainset.getWeight() - car.getWeightBrutto());
                            }
                            case "0" -> {
                                System.out.println("You stopped managing trainset");
                                return;
                            }
                            default -> {
                                System.out.println("Wrong input");
                                System.out.println("Enter 1 if you want to try to manage trainset again");
                                System.out.println("Enter something else if you want to stop managing trainset");
                                ch = scanner.nextLine();
                                if (ch.equals("1"))
                                    Trainset.manageTrainset();
                                return;
                            }
                        }
                    }
                    case "0" -> {
                        System.out.println("You stopped managing trainset");
                        return;
                    }
                    default -> {
                        System.out.println("Wrong input");
                        System.out.println("Enter 1 if you want to try to manage trainset again");
                        System.out.println("Enter something else if you want to stop managing trainset");
                        ch = scanner.nextLine();
                        if (ch.equals("1"))
                            Trainset.manageTrainset();
                        return;
                    }
                }
            }
        } else
            System.out.println("We can not manage this trainset because it is moving");
    }

    public void sortCarsByWeight() {
        cars.sort(Comparator.comparingDouble(Car::getWeightBrutto));
    }

    public void generateRoute() { // creating path(stations) for trainset
        Set<Station> visited = new HashSet<>();
        ArrayList<Station> route = new ArrayList<>();

        Station start = this.getLocomotive().getSourceStation();
        Station end = this.getLocomotive().getDestinationStation();

        if (generateRouteFind(start, end, visited, route))
            this.setRouteStations(route);
    }

    public static boolean generateRouteFind(Station current, Station end, Set<Station> visited, ArrayList<Station> route) { // creating path(stations) for trainset
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
            String string = Files.readFile("trainsetnames.txt", 50);
            Locomotive locomotive = Locomotive.generateLocomotiveRandomly("locomotivenames.txt", 50);
            ArrayList<Car> cars = Car.generateCarRandomly("shippingnames.txt", 100);
            Trainset trainset = new Trainset(string, locomotive, cars);
            int counter = cars.size();

            for (int j = 0; j < counter; j++) {
                trainset.setWeight(trainset.getWeight() + cars.get(j).getWeightBrutto());
                if (cars.get(j).isGridConnection())
                    trainset.getLocomotive().setCurElecRailRoad(trainset.getLocomotive().getCurElecRailRoad() + 1);
            }
            if (trainset.getWeight() > trainset.getMaxWeight() || trainset.getLocomotive().getCurElecRailRoad() > trainset.getLocomotive().getMaxElectGrid())
                counter++;
            else {
                trainset.setWeight(locomotive.getWeight() + trainset.getWeight());
                trainset.sortCarsByWeight();
                DataLists.getTrainsets().add(trainset);
            }
        }
    }

    public void createRail() { // creating path(rails) for trainset
        this.generateRoute();
        ArrayList<Rail> rails = new ArrayList<>();

        if (this.getRouteStations() == null)
            return;

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
            if (toFind != null)
                rail = toFind;
            else if (toFindReversed != null)
                rail = toFindReversed;
            else {
                DataLists.getRails().add(rail);
                rail.getStation1().getIntersections().add(rail.getStation2());
                rail.getStation2().getIntersections().add(rail.getStation1());
                Rail railRev = new Rail(station2, station1, randomVal);
                DataLists.getRailsReversed().add(railRev);
            }
            rails.add(rail);
            this.setWholeDistance(this.getWholeDistance() + rail.getDistance());
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        if (routeRails == null) {
            this.createRail();
            if (this.getRouteRails() == null) {
                System.out.println("Stations are not connected");
                this.interrupt();
                return;
            }
        }
        getLocomotive().setSpeed(140.0);
        double distanceWtmp = this.getWholeDistance();

        for (int i = 0; i < routeRails.size(); i++) {
            while (!routeRails.get(i).getisAvailable()) {
                try {
                    System.out.println(this.getNameT() + " is waiting...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.setCurrentRail(routeRails.get(i));
            routeRails.get(i).setAvailable(false);
            double distanceBetween = routeRails.get(i).getDistance();
            double distanceTmp = distanceBetween;
            double distanceWhole = routeRails.get(i).getDistance();

            while (distanceBetween > 0) {
                distanceBetween -= this.getLocomotive().getSpeed();
                this.setWholeDistance(distanceWhole / distanceWtmp * 100);
                this.setCurrentDistance(distanceBetween / distanceTmp * 100);
                if (this.getWholeDistance() < 0)
                    this.setWholeDistance(0);
                if (this.getCurrentDistance() < 0)
                    this.setCurrentDistance(0);

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
            if (this.getLocomotive().getDestinationStation() == routeRails.get(i).getStation2())
                System.out.println("Trainset " + this.getNameT() + " is on destination station " + this.getLocomotive().getDestinationStation().getName());
            else
                System.out.println("Trainset " + this.getNameT() + " is on station " + routeRails.get(i).getStation2().getName());
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

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (this.isAvailable)
            run();
    }

    @Override
    public String toString() {
        String string = DataLists.printCarsT(this);
        return "Name: " + nameT + "; Id: " + currentId + "; Speed: " + locomotive.getSpeed() + "; Whole Distance %: " + wholeDistance + "; Current Distance " + currentDistance + "; Weight: " + weight + "\nLocomotive: " + "\n" + locomotive + "\nCars: " + "\n" + string;
    }
}