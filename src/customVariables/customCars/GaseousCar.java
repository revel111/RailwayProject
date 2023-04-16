package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Objects;
import java.util.Scanner;

public class GaseousCar extends BasicFreight{
    private static Scanner scanner = new Scanner(System.in);
    public GaseousCar(String shipper) {
        this.setGridConnection(false);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.getWeightNetto());
        this.setWeightBrutto(this.getWeightBrutto());
    }

    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter weight of baggage to add");
        scanner = new Scanner(System.in);
        double gasIn = scanner.nextDouble();
        scanner.nextLine();

        try {
            if (this.getWeightBrutto() + gasIn > getFill())
                throw new TooManyException("Too many baggage");
            else {
                this.setWeightNetto(this.getWeightNetto() + gasIn);
                this.setWeightBrutto(0+ (gasIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter try if you want to try to add gas again");
            System.out.println("Enter exit if you don't want to add gas");
            String ch = scanner.nextLine();

            if (Objects.equals(ch, "try"))
                fillCar();
            else
                return;
        }
    }

    @Override
    public void emptyCar() throws TooManyException {

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