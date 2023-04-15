package customVariables.customCars;

import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class MailCar extends Car {

    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 400;
    private double weightBrutto = weightNetto;
    private final double maxMailsWeight = 100;
    private double mailsWeight;

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    public MailCar(String shipper) {
        this.setGridConnection(false);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    @Override
    public void fillCar() throws TooManyException {

    }

    @Override
    public void emptyCar() throws TooManyException {

    }

    public static Runnable createMailCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        MailCar mailCar = new MailCar(shipper);
        DataLists.getCars().add(mailCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current weight of mails: " + mailsWeight;
    }
}
