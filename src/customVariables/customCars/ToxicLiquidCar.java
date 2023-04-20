package customVariables.customCars;

import customVariables.customExtra.HeavyFreightInter;
import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class ToxicLiquidCar extends Car implements HeavyFreightInter {
    private static final Scanner scanner = new Scanner(System.in);
    private final double maxFill = 2000;
    private double fill = 0;

    public ToxicLiquidCar(String shipper) {
        this.setGridConnection(false);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.getWeightNetto());
        this.setWeightBrutto(this.getWeightBrutto());
    }

    @Override
    public double getFill() {
        return 0;
    }

    @Override
    public double getMaxFill() {
        return 0;
    }

    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter weight of toxic liquid to add");
        double toxicIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + toxicIn > getMaxFill())
                throw new TooManyException("Too many toxic liquid");
            else {
                this.fill += toxicIn;
                this.setWeightBrutto(this.getWeightBrutto() + (toxicIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add toxic liquid again");
            System.out.println("Enter exit if you don't want to add toxic liquid");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() {
        System.out.println("Enter amount of toxic liquid to delete");
        double weightOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getFill() - weightOut < 0)
                throw new TooManyException("There are no such weight of toxic liquid");
            else {
                this.fill -= weightOut;
                this.setWeightBrutto(this.getWeightBrutto() - (weightOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete toxic liquid again");
            System.out.println("Enter something else if you don't want to delete toxic liquid");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createToxicLiquid() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        ToxicLiquidCar toxicLiquidCar = new ToxicLiquidCar(shipper);
        DataLists.getCars().add(toxicLiquidCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of weight " + getFill();
    }
}