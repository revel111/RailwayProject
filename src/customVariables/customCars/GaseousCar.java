package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class GaseousCar extends BasicFreight {
    private static Scanner scanner = new Scanner(System.in);

    private final double maxFill = 1000;
    private double fill = 0;

    public GaseousCar(String shipper) {
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

    @Override
    public void setFill(double fill) {
        this.fill = fill;
    }

    public double getMaxFill() {
        return maxFill;
    }

    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter weight of gas to add");
        double gasIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + gasIn > this.getMaxFill())
                throw new TooManyException("Too many gas");
            else {
                this.setFill(fill += gasIn);
                this.setWeightNetto(this.getWeightBrutto() + gasIn);
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add gas again");
            System.out.println("Enter exit if you don't want to add gas");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
        }
    }


    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of passengers to delete");
        double gasOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.fill - gasOut < 0)
                throw new TooManyException("There are no such amount of gas");
            else {
                this.fill -= gasOut;
                this.setWeightBrutto(this.getWeightBrutto() - (gasOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete gas again");
            System.out.println("Enter something else if you don't want to delete gas");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createGaseousCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        GaseousCar gaseousCar = new GaseousCar(shipper);
        DataLists.getCars().add(gaseousCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}