package customVariables.customCars;

import customVariables.Rail;
import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class RestaurantCar extends Car {
    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 555;
    private double weightBrutto = weightNetto;
    private final int maxAmountOfTables = 40;
    private int tables;

    public RestaurantCar(String shipper) {
        this.setGridConnection(true);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    @Override
    public void fillCar() throws TooManyException {

    }

    @Override
    public void emptyCar() throws TooManyException {

    }

    public static Runnable createRestaurantCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        RestaurantCar refrigeratedCar= new RestaurantCar(shipper);
        DataLists.getCars().add(refrigeratedCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of people in restaurant: " + tables;
    }
}
