package customVariables;

import customVariables.customCars.Car;
import customVariables.customExtra.TooManyCarsException;
import operations.DataLists;

import java.util.*;

public class Trainset {
    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private static int id = 0;
    private String currentId;
    private Locomotive locomotive;
    private ArrayList<Car> cars; //10
    private ArrayList<Station> route;
    private double weight = 1000;
    private final double maxWeight = 10000;

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


    public Locomotive getLocomotive() {
        return locomotive;
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ArrayList<Station> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Station> route) {
        this.route = route;
    }

    public static void createTrainset() throws TooManyCarsException {
        System.out.println("Enter 1 if you want to create new locomotive for trainset");
        System.out.println("Enter 2 if you want to choose particular locomotive for trainset");
        System.out.println("Enter 0 if you want to stop creating trainset");
        Locomotive locomotive = null;
        ArrayList<Car> cars = new ArrayList<Car>();

        int ch = scanner.nextInt();
        scanner.nextLine();

        switch (ch) {
            case 1:
                locomotive = Locomotive.createLocomotive(true);
                Locomotive.deleteLocomotiveById(locomotive.getCurrentId());
                break;
            case 2:
                System.out.println("Enter locomotive's id");
                DataLists.printData(DataLists.getLocomotives());
                String id = scanner.nextLine();

                locomotive = Locomotive.findLocomotiveById(id);
                Locomotive.deleteLocomotiveById(locomotive.getCurrentId());
                break;
            default:
                System.out.println("You stopped creating trainset");
                return;
        }

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        Trainset trainset = new Trainset(name, locomotive, cars);

        while (true) {
            System.out.println("Enter 1 if you want to create new car");
            System.out.println("Enter 2 if you want to choose particular car");
            System.out.println("Enter 0 if you want to stop creating trainset");
            Car car = null;
            ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 1:
                    car = Car.createCar(true);
                    Car.deleteCarById(car.getCurrentId());
                    break;
                case 2:
                    System.out.println("Enter car's id");
                    DataLists.printData(DataLists.getCars());
                    String id = scanner.nextLine();

                    car = Car.findCarById(id);
                    Car.deleteCarById(car.getCurrentId());
                    break;
                case 0:
                    System.out.println("You stopped creating trainset");
                    return;
                default:
                    System.out.println("Wrong input");
            }

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

            System.out.println("Enter 1 if you want to add one more car");
            System.out.println("Enter 0 if you want to end creating trainset");
            ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 1:
                    break;
                case 0:
                    DataLists.getTrainsets().add(trainset);
                    return;
                default:
                    System.out.println("Wrong input");
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

    public ArrayList<Car> generateRandomlyCars(Trainset trainset) {
        ArrayList<Car> cars = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            int randomNumber = (int) (Math.random() * 12.0);

        }

        return cars;
    }

//    public static void generateRoute(Trainset trainset) {
//        Station destinationStation = trainset.getLocomotive().getDestinationStation();
//        Station currentStation = trainset.getLocomotive().getSourceStation();
//        ArrayList<Station> route = new ArrayList<>();
//
////        while (currentStation != destinationStation) {
////
////            while (true) {
////                for (int i = 0; i < currentStation.getIntersections().size(); i++) {
////                    if (currentStation.getIntersections().get(i) == destinationStation)
////
////                }
////            }
////            Station tempStation = currentStation;
////        }
//
//        route = generateHelpful(currentStation, destinationStation, route, null);
//
//        trainset.setRoute(route);
//    }
//
//    public static ArrayList<Station> generateHelpful(Station currentStation, Station destinationStation, ArrayList<Station> route, Station tempStation) {
//        ArrayList<Station> visited = new ArrayList<>();
//        for (int i = 0; i < currentStation.getIntersections().size(); i++) {
//            if (currentStation.getIntersections().get(i) == destinationStation) {
//                System.out.println("success");
//                return route;
//            } else if (currentStation.getIntersections().size() == 0) {
//                visited.add(currentStation);
//                currentStation = tempStation;
////                generateHelpful(currentStation, destinationStation, route, null);
//            } else {
//                tempStation = currentStation;
//                currentStation = currentStation.getIntersections().get(i);
//                generateHelpful(currentStation, destinationStation, route, tempStation);
//            }
//        }
//
//        return route;
//    }
//
//    public static boolean checkIf(ArrayList<Station> visited, Station currentStation) {
//        for (int i = 0; i < visited.size(); i++)
//            if (currentStation == visited.get(i))
//                return true;
//
//        return false;
//    }

    public static void generateRoute(Trainset trainset) {
        Set<Station> visited = new HashSet<>();
        ArrayList<Station> route = new ArrayList<>();

        Station start = trainset.getLocomotive().getSourceStation();
        Station end = trainset.getLocomotive().getDestinationStation();

        if(generateRouteFind(start,end,visited,route))
            trainset.setRoute(route);
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

    @Override
    public String toString() {
        String string = DataLists.printCarsT(this);
        return "Name: " + name + "; Id: " + currentId + "; Speed: " + locomotive.getSpeed() + "; Weight: " + weight + "\nLocomotive: " + "\n" + locomotive + "\nCars: " + "\n" + string;
    }
}