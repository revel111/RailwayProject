package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class LiquidCar extends BasicFreight {
    private static final Scanner scanner = new Scanner(System.in);

    private final double maxFill = 1000;
    private double fill = 0;

    public LiquidCar(String shipper) {
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
        System.out.println("Enter weight of liquid to add");
        double gasIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + gasIn > this.getMaxFill())
                throw new TooManyException("Too many liquid");
            else {
                this.setFill(fill += gasIn);
                this.setWeightBrutto(this.getWeightBrutto() + gasIn);
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add liquid again");
            System.out.println("Enter exit if you don't want to add liquid");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter weight of liquid to delete");
        double liquidOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.fill - liquidOut < 0)
                throw new TooManyException("There are no such weight of liquid");
            else {
                this.fill -= liquidOut;
                this.setWeightBrutto(this.getWeightBrutto() - (liquidOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete liquid again");
            System.out.println("Enter something else if you don't want to delete liquid");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createLiquidCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        LiquidCar liquidCar = new LiquidCar(shipper);
        DataLists.getCars().add(liquidCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
