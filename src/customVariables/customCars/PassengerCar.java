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
    private String shipper;

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
        this.shipper = shipper;
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    public void fillCar() throws TooManyException {
        System.out.println("Enter amount of passengers to add");
        scanner = new Scanner(System.in);
        int passengersIn = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.passengers + passengersIn > maxAmountOfPassengers)
                throw new TooManyException("Too many passenger");
            else {
                this.passengers += passengersIn;
                this.setWeightBrutto(this.weightNetto + (passengersIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to add passengers again");
            System.out.println("Enter 0 if you don't want to add passengers");
            int ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 0:
                    return;
                case 1:
                    fillCar();
            }
        }
    }

    public void emptyCar() {
        System.out.println("Enter amount of passengers to delete");
        scanner = new Scanner(System.in);
        int passengersOut = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.passengers - passengersOut < 0)
                throw new TooManyException("There are no such amount of passengers");
            else {
                this.passengers -= passengersOut;
                this.setWeightBrutto(this.weightNetto - (passengersOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete passengers again");
            System.out.println("Enter 0 if you don't want to delete passengers");
            int ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 0:
                    return;
                case 1:
                    emptyCar();
            }
        }
    }

//    public static void createPassengerCar() {
//        PassengerCar passengerCar = new PassengerCar();
//        DataLists.getCars().add(passengerCar);
//    }

    public static Runnable createPassengerCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        PassengerCar passengerCar = new PassengerCar(shipper);
        DataLists.getCars().add(passengerCar);
        return null;
    }

//    public static Car createPassengerCar(boolean f) {
//        PassengerCar passengerCar = new PassengerCar();
//        DataLists.getCars().add(passengerCar);
//        return passengerCar;
//    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of passengers: " + passengers;
    }
}
