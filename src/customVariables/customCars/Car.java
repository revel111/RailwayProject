package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static void createCars() {
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

    public static ArrayList<Car> generateCarRandomly(String name, int number) {
        ArrayList<Car> arrayList = new ArrayList<>();
        HashMap<String, Runnable> menu = new HashMap<>();

        menu.put("1", PassengerCar::createPassengerCar);
        menu.put("2", BaggageCar::createBaggageCar);
        menu.put("3", ExplosiveCar::createExposiveCar);
        menu.put("4", GaseousCar::createGaseousCar);
        menu.put("5", LiquidCar::createLiquidCar);
        menu.put("6", MailCar::createMailCar);
        menu.put("7", PostOfficeCar::createPostOfficeCar);
        menu.put("8", RefrigeratedCar::createRefrigeratedCar);
        menu.put("9", RestaurantCar::createRestaurantCar);
        menu.put("10", ToxicCar::createToxicCar);

        String string = DataLists.ReadFile(name, number);

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * (10 - 1)) + 1;

            Runnable action = menu.get(random);


        }

        return arrayList;
    }

    public static Car createCar(boolean t) { // redefined
        System.out.println("Choose car type to create");
        System.out.println("Enter 0 if you want to stop creating car");
//        DataLists.printTypesOfCars();
        int ch = scanner.nextInt();

        Car car = null;

        switch (ch) {
            case 1:
//                car = PassengerCar.createPassengerCar(true);
                break;
            case 0:
                System.out.println("You stopped creating car");
                return null;
            default:
                System.out.println("You entered a wrong number");
        }

        return car;
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