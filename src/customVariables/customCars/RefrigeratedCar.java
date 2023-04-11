package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class RefrigeratedCar extends BasicFreight {
    private static Scanner scanner = new Scanner(System.in);

    public RefrigeratedCar(String shipper) {
        this.setGridConnection(true);
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

    public static Runnable createRefrigeratedCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        RefrigeratedCar refrigeratedCar = new RefrigeratedCar(shipper);
        DataLists.getCars().add(refrigeratedCar);
        return null;
    }

    public static Car createRefrigeratedCarReturn(String shipper) { // function for cars auto generation
        RefrigeratedCar refrigeratedCar = new RefrigeratedCar(shipper);

        return refrigeratedCar;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}