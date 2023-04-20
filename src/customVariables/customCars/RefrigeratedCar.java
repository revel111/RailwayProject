package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class RefrigeratedCar extends BasicFreight {
    private static Scanner scanner = new Scanner(System.in);
    private final double maxFill = 1000;
    private double fill = 0;
    public RefrigeratedCar(String shipper) {
        this.setGridConnection(true);
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
        System.out.println("Enter weight of meat to add");
        double meatIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + meatIn > this.getMaxFill())
                throw new TooManyException("Too many meat");
            else {
                this.setFill(fill += meatIn);
                this.setWeightBrutto(this.getWeightBrutto() + meatIn);
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add meat again");
            System.out.println("Enter exit if you don't want to add meat");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of meat to delete");
        double meatOut = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.fill - meatOut < 0)
                throw new TooManyException("There are no such amount of meat");
            else {
                this.fill -= meatOut;
                this.setWeightBrutto(this.getWeightBrutto() - (meatOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete meat again");
            System.out.println("Enter something else if you don't want to delete meat");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createRefrigeratedCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        RefrigeratedCar refrigeratedCar = new RefrigeratedCar(shipper);
        DataLists.getCars().add(refrigeratedCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}