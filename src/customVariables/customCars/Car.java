package customVariables.customCars;

import customVariables.Station;
import customVariables.Trainset;
import customVariables.customExtra.TooManyException;
import operations.DataLists;
import operations.Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

abstract public class Car {
    private static final Scanner scanner = new Scanner(System.in);
    private String shipper;
    private boolean gridConnection; //5
    private double weightNetto;
    private double weightBrutto;
    protected static int id = 0;
    private String currentId;

    public String getShipper() {
        return shipper;
    }

    public double getWeightBrutto() {
        return weightBrutto;
    }

    public double getWeightNetto() {
        return weightNetto;
    }

    public String getCurrentId() {
        return currentId;
    }

    public static int getId() {
        return id;
    }

    public boolean isGridConnection() {
        return gridConnection;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public void setGridConnection(boolean gridConnection) {
        this.gridConnection = gridConnection;
    }

    public void setWeightBrutto(double weightBrutto) {
        this.weightBrutto = weightBrutto;
    }

    public void setWeightNetto(double weightNetto) {
        this.weightNetto = weightNetto;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public static void setId(int id) {
        Car.id = id;
    }

    public abstract void fillCar() throws TooManyException;

    public abstract void emptyCar() throws TooManyException;

    public static void createCar() {
        HashMap<String, Runnable> menu = new HashMap<>();

        menu.put("passenger", PassengerCar::createPassengerCar);
        menu.put("baggage", BaggageCar::createBaggageCar);
        menu.put("explosive", ExplosiveCar::createExposiveCar);
        menu.put("gaseous", GaseousCar::createGaseousCar);
        menu.put("liquid", LiquidCar::createLiquidCar);
        menu.put("mail", MailCar::createMailCar);
        menu.put("post", PostOfficeCar::createPostOfficeCar);
        menu.put("refrigerated", RefrigeratedCar::createRefrigeratedCar);
        menu.put("restaurant", RestaurantCar::createRestaurantCar);
        menu.put("toxic", ToxicCar::createToxicCar);
        menu.put("toxicliquid", ToxicLiquidCar::createToxicLiquid);

        System.out.println("Choose car type to create");

        for (String option : menu.keySet())
            System.out.println(option);

        String type = scanner.nextLine();
        Runnable action = menu.get(type);
        if (type.equals("exit"))
            return;

        if (action != null)
            action.run();
        else
            System.out.println("Invalid choice.");
    }

    public static Car switchForCars(int ch, String shipper) {
        Car car = null;

        switch (ch) {
            case 1:
                car = new PassengerCar(shipper);
                break;
            case 2:
                car = new BaggageCar(shipper);
                break;
            case 3:
                car = new ExplosiveCar(shipper);
                break;
            case 4:
                car = new GaseousCar(shipper);
                break;
            case 5:
                car = new LiquidCar(shipper);
                break;
            case 6:
                car = new MailCar(shipper);
                break;
            case 7:
                car = new PostOfficeCar(shipper);
                break;
            case 8:
                car = new RefrigeratedCar(shipper);
                break;
            case 9:
                car = new RestaurantCar(shipper);
                break;
            case 10:
                car = new ToxicCar(shipper);
                break;
            case 11:
                car = new ToxicLiquidCar(shipper);
                break;
        }

        return car;
    }

    public static ArrayList<Car> generateCarRandomly(String name, int number) {
        ArrayList<Car> arrayList = new ArrayList<>();
        Car car = null;
        Random random = new Random();
        int randomAmount = random.nextInt(11 - 5 - 1) + 5;

        for (int i = 0; i < randomAmount; i++) {
            int randomNumber = random.nextInt(10 - 1 - 1) + 1;
            String string = Files.ReadFile(name, number);
            car = Car.switchForCars(randomNumber, string);
            arrayList.add(car);
        }

        return arrayList;
    }

    public static Car createCarForTrainest() {
        System.out.println("Choose type of a car");
        Car.printTypesOfCars();
        String type = scanner.nextLine();
        System.out.println("Enter shipper's name");
        String shipper = scanner.nextLine();
        Car car = null;

        switch (type) {
            case "passenger":
                car = new PassengerCar(shipper);
                break;
            case "postoffice":
                car = new PostOfficeCar(shipper);
                break;
            case "baggage":
                car = new BaggageCar(shipper);
                break;
            case "mail":
                car = new MailCar(shipper);
                break;
            case "restaurant":
                car = new RestaurantCar(shipper);
                break;
            case "refrigerated":
                car = new RefrigeratedCar(shipper);
                break;
            case "liquid":
                car = new LiquidCar(shipper);
                break;
            case "gaseous":
                car = new GaseousCar(shipper);
                break;
            case "explosive":
                car = new ExplosiveCar(shipper);
                break;
            case "toxic":
                car = new ToxicCar(shipper);
                break;
            case "toxicliquid":
                car = new ToxicLiquidCar(shipper);
                break;
            default:
                System.out.println("Wrong input");
                System.out.println("Enter 1 if you want to try to create car again");
                System.out.println("Enter something else if you want to stop creating car");
                String ch1 = scanner.nextLine();

                if (ch1.equals("1"))
                    Car.createCarForTrainest();
                return null;
        }
        return car;
    }

    public static void deleteCar() {
        System.out.println("Enter id of a car");
        DataLists.printData(DataLists.getCars());
        String id = scanner.nextLine();
        Car car = findCarById(id);

        if (car == null) {
            System.out.println("There is no car with id " + id);
            System.out.println("Enter 1 if you want to try to delete car again");
            System.out.println("Enter something else if you want to stop deleting car");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                deleteCar();
            return;
        }

        DataLists.getCars().remove(car);
    }

    public static void deleteCarById(String id) {
        for (int i = 0; i < DataLists.getCars().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getCars().get(i).getCurrentId()))
                DataLists.getCars().remove(i);
    }

    public static Car findCarById(String id) {
        for (int i = 0; i < DataLists.getCars().size(); i++)
            if (id.equalsIgnoreCase(DataLists.getCars().get(i).getCurrentId()))
                return DataLists.getCars().get(i);
        return null;
    }

    public static void printTypesOfCars() {
        System.out.println();
        System.out.println("Enter \"passenger\" to create passenger car");
        System.out.println("Enter \"postoffice\" to create post office car");
        System.out.println("Enter \"baggage\" to create baggage car");
        System.out.println("Enter \"mail\" to create mail car");
        System.out.println("Enter \"restaurant\" to create restaurant car");
        System.out.println("Enter \"refrigerated\" to create refrigerated car");
        System.out.println("Enter \"liquid\" to create car for liquid material");
        System.out.println("Enter \"gaseous\" to create car for gaseous material");
        System.out.println("Enter \"explosive\" to create car for explosive material");
        System.out.println("Enter \"toxic\" to create car for toxic material");
        System.out.println("Enter \"toxicliquid\" to create car for toxic and liquid material");
        System.out.println();
    }

    @Override
    public String toString() {
        String string = "Shipper: " + shipper + "; Id: " + currentId + "; Weight: " + weightBrutto;

        if (!gridConnection)
            string += "; No grid locomotive’s electrical grid required";
        else
            string += "; Grid locomotive’s electrical grid required";

        return string;
    }
}