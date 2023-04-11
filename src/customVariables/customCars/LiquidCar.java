package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class LiquidCar extends BasicFreight {
    private static final Scanner scanner = new Scanner(System.in);

    public LiquidCar(String shipper) {
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

    public static Runnable createLiquidCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        LiquidCar liquidCar = new LiquidCar(shipper);
        DataLists.getCars().add(liquidCar);
        return null;
    }

    public static Car createLiquidCarReturn(String shipper) { // function for cars auto generation
        LiquidCar liquidCar = new LiquidCar(shipper);

        return liquidCar;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
