package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class BaggageCar extends Car {

    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 900;
    private double weightBrutto = weightNetto;
    private final double maxBaggageWeight = 300;
    private double baggageWeight;

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    public BaggageCar(String shipper) {
        this.setGridConnection(false);
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

    public static Runnable createBaggageCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        BaggageCar baggageCar = new BaggageCar(shipper);
        DataLists.getCars().add(baggageCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current weight of baggage: " + baggageWeight;
    }
}