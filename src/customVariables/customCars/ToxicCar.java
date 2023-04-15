package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class ToxicCar extends HeavyFreight {
    private static Scanner scanner = new Scanner(System.in);

    public ToxicCar(String shipper) {
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

    public static Runnable createToxicCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        ToxicCar toxicCar = new ToxicCar(shipper);
        DataLists.getCars().add(toxicCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current fillness: " + getFill();
    }
}