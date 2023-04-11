package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class ExplosiveCar extends HeavyFreight {
    private static final Scanner scanner = new Scanner(System.in);

    public ExplosiveCar(String shipper) {
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

    public static Runnable createExposiveCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        ExplosiveCar explosiveCar = new ExplosiveCar(shipper);
        DataLists.getCars().add(explosiveCar);
        return null;
    }

    public static Car createExplosiveCarReturn(String shipper) { // function for cars auto generation
        ExplosiveCar explosiveCar = new ExplosiveCar(shipper);

        return explosiveCar;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current fillness: " + getFill();
    }
}