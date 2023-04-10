package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class GaseousCar extends BasicFreight{
    private static final Scanner scanner = new Scanner(System.in);
    public GaseousCar(String shipper) {
        this.setGridConnection(false);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.getWeightNetto());
        this.setWeightBrutto(this.getWeightBrutto());
    }

    @Override
    public void fillCar() throws TooManyException {

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