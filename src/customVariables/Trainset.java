package customVariables;

import customVariables.customCars.Car;
import customVariables.customExtra.TooManyCarsException;
import operations.DataLists;
import operations.Files;

import java.util.*;

public class Trainset {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private static int id = 0;
    private String currentId;
    private Locomotive locomotive;
    private ArrayList<Car> cars; //10
    private ArrayList<Station> routeStations;
    private ArrayList<Rail> routeRails;
    private double weight = 1000;
    private final double maxWeight = 10000;
    private double distance = 0;

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public double getWeight() {
        return weight;
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

//    public static void createTrainset() throws TooManyCarsException {
//        System.out.println("Enter 1 if you want to create new locomotive for trainset");
//        System.out.println("Enter 2 if you want to choose particular locomotive for trainset");
//        System.out.println("Enter 0 if you want to stop creating trainset");
//        Locomotive locomotive = null;
//        ArrayList<Car> cars = new ArrayList<Car>();
//
//        int ch = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (ch) {
//            case 1:
//                locomotive = Locomotive.createLocomotive(true);
//                Locomotive.deleteLocomotiveById(locomotive.getCurrentId());
//                break;
//            case 2:
//                System.out.println("Enter locomotive's id");
//                DataLists.printData(DataLists.getLocomotives());
//                String id = scanner.nextLine();
//
//                locomotive = Locomotive.findLocomotiveById(id);
//                Locomotive.deleteLocomotiveById(locomotive.getCurrentId());
//                break;
//            default:
//                System.out.println("You stopped creating trainset");
//                return;
//        }
//
//        System.out.println("Enter name: ");
//        String name = scanner.nextLine();
//
//        Trainset trainset = new Trainset(name, locomotive, cars);
//
//        while (true) {
//            System.out.println("Enter 1 if you want to create new car");
//            System.out.println("Enter 2 if you want to choose particular car");
//            System.out.println("Enter 0 if you want to stop creating trainset");
//            Car car = null;
//            ch = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (ch) {
//                case 1:
//                    car = Car.createCar(true);
//                    Car.deleteCarById(car.getCurrentId());
//                    break;
//                case 2:
//                    System.out.println("Enter car's id");
//                    DataLists.printData(DataLists.getCars());
//                    String id = scanner.nextLine();
//
//                    car = Car.findCarById(id);
//                    Car.deleteCarById(car.getCurrentId());
//                    break;
//                case 0:
//                    System.out.println("You stopped creating trainset");
//                    return;
//                default:
//                    System.out.println("Wrong input");
//            }
//
//            try {
//                if (trainset.getWeight() + car.getWeightBrutto() >= trainset.getMaxWeight() || locomotive.getCurElecRailRoad() == locomotive.getMaxElectGrid() || cars.size() == 2)
//                    throw new TooManyCarsException();
//                else {
//                    trainset.setWeight(car.getWeightBrutto() + trainset.getWeight());
//                    if (car.isGridConnection())
//                        locomotive.setCurElecRailRoad(locomotive.getCurElecRailRoad() + 1);
//                    cars.add(car);
//                }
//            } catch (TooManyCarsException e) {
//                System.out.println(e.getMessage());
//                DataLists.getTrainsets().add(trainset);
//                return;
//            }
//
//            System.out.println("Enter 1 if you want to add one more car");
//            System.out.println("Enter 0 if you want to end creating trainset");
//            ch = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (ch) {
//                case 1:
//                    break;
//                case 0:
//                    DataLists.getTrainsets().add(trainset);
//                    return;
//                default:
//                    System.out.println("Wrong input");
//            }
//        }
//    }

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
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return Double.compare(car1.getWeightBrutto(), car2.getWeightBrutto());
            }
        });
    }

    public void connectCarToTrainset(Trainset trainset, Car car) {
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
        }
    }

    public static void generateRoute(Trainset trainset) {
        Set<Station> visited = new HashSet<>();
        ArrayList<Station> route = new ArrayList<>();

        Station start = trainset.getLocomotive().getSourceStation();
        Station end = trainset.getLocomotive().getDestinationStation();

        if (generateRouteFind(start, end, visited, route))
            trainset.setRouteStations(route);
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

    public static void generateTrainsetRandomly() {
        String string = Files.ReadFile("trainsetnames.txt", 50);
        Locomotive locomotive = Locomotive.generateLocomotiveRandomly("locomotivenames.txt", 50);
        ArrayList<Car> cars = Car.generateCarRandomly("shippingnames.txt", 100);
        DataLists.getTrainsets().add(new Trainset(string, locomotive, cars));
    }

    @Override
    public String toString() {
        String string = DataLists.printCarsT(this);
        return "Name: " + name + "; Id: " + currentId + "; Speed: " + locomotive.getSpeed() + "; Weight: " + weight + "\nLocomotive: " + "\n" + locomotive + "\nCars: " + "\n" + string;
    }
}