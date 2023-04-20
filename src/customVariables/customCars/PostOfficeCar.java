package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class PostOfficeCar extends Car {
    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 300;
    private double weightBrutto = weightNetto;
    private final int maxAmountOfPeople = 20;
    private int people;

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    public PostOfficeCar(String shipper) {
        this.setGridConnection(true);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter amount of people to add");
        int peopleIn = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.people + peopleIn > maxAmountOfPeople)
                throw new TooManyException("Too many people");
            else {
                this.people += peopleIn;
                this.setWeightBrutto(this.getWeightBrutto() + (peopleIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to add people again");
            System.out.println("Enter something elss if you don't want to add people");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of people to delete");
        int peopleOut = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.people - peopleOut < 0)
                throw new TooManyException("There are no such amount of people");
            else {
                this.people -= peopleOut;
                this.setWeightBrutto(this.getWeightBrutto() - (peopleOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete people again");
            System.out.println("Enter something else if you don't want to delete people");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createPostOfficeCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        PostOfficeCar postOfficeCar = new PostOfficeCar(shipper);
        DataLists.getCars().add(postOfficeCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of people in office: " + people;
    }

}
