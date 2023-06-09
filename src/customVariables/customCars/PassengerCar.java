package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class PassengerCar extends Car {
    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 500;
    private double weightBrutto = weightNetto;
    private final int maxAmountOfPassengers = 50;
    private int passengers;

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    public PassengerCar(String shipper) {
        this.setGridConnection(true);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    public void fillCar() throws TooManyException {
        System.out.println("Enter amount of passengers to add");
        int passengersIn = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.passengers + passengersIn > maxAmountOfPassengers)
                throw new TooManyException("Too many passenger");
            else {
                this.passengers += passengersIn;
                this.setWeightBrutto(this.getWeightBrutto() + (passengersIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to add passengers again");
            System.out.println("Enter something else if you don't want to add passengers");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                fillCar();
        }
    }

    public void emptyCar() {
        System.out.println("Enter amount of passengers to delete");
        int passengersOut = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.passengers - passengersOut < 0)
                throw new TooManyException("There are no such amount of passengers");
            else {
                this.passengers -= passengersOut;
                this.setWeightBrutto(this.getWeightBrutto() - (passengersOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete passengers again");
            System.out.println("Enter something else if you don't want to delete passengers");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createPassengerCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        PassengerCar passengerCar = new PassengerCar(shipper);
        DataLists.getCars().add(passengerCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of passengers: " + passengers;
    }
}
