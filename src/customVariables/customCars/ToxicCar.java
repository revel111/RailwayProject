package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class ToxicCar extends HeavyFreight {
    private static Scanner scanner = new Scanner(System.in);
    private final double maxFill = 2000;
    private double fill = 0;

    public ToxicCar(String shipper) {
        this.setGridConnection(false);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.getWeightNetto());
        this.setWeightBrutto(this.getWeightBrutto());
    }

    @Override
    public double getFill() {
        return fill;
    }

    public double getMaxFill() {
        return maxFill;
    }


    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter weight of toxic to add");
        double toxicIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + toxicIn > getMaxFill())
                throw new TooManyException("Too many toxic");
            else {
                this.fill += toxicIn;
                this.setWeightBrutto(this.getWeightBrutto() + (toxicIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add toxic again");
            System.out.println("Enter exit if you don't want to add toxic");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of toxic to delete");
        double toxicOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.fill - toxicOut < 0)
                throw new TooManyException("There are no such toxic of gas");
            else {
                this.fill -= toxicOut;
                this.setWeightBrutto(this.getWeightBrutto() - (toxicOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete toxic again");
            System.out.println("Enter something else if you don't want to delete toxic");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createToxicCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        ToxicCar toxicCar = new ToxicCar(shipper);
        DataLists.getCars().add(toxicCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}