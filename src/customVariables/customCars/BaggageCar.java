package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
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
        System.out.println("Enter weight of baggage to add");
        double baggageIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.baggageWeight + baggageIn > maxBaggageWeight)
                throw new TooManyException("Too many baggage");
            else {
                this.baggageWeight += baggageIn;
                this.setWeightBrutto(this.weightNetto + (baggageIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete baggage again");
            System.out.println("Enter something else if you don't want to delete baggage");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                fillCar();
            else
                return;
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter weight of baggage to delete");
        double baggageOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.baggageWeight - baggageOut < 0)
                throw new TooManyException("There are no such weight of baggage");
            else {
                this.baggageWeight -= baggageOut;
                this.setWeightBrutto(this.getWeightBrutto() - (baggageOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete baggage again");
            System.out.println("Enter something else if you don't want to delete baggage");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
            else
                return;
        }
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