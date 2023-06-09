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
        System.out.println("Enter weight of mails to add");
        double weightTo = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.mailsWeight + weightTo > maxMailsWeight)
                throw new TooManyException("Too many mails");
            else {
                this.mailsWeight += weightTo;
                this.setWeightBrutto(this.getWeightBrutto() + (weightTo * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to add mails again");
            System.out.println("Enter something else if you don't want to add mails");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of mails to delete");
        double mailsOut = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.mailsWeight - mailsOut < 0)
                throw new TooManyException("There are no such amount of mails");
            else {
                this.mailsWeight -= mailsOut;
                this.setWeightBrutto(this.getWeightBrutto() - (mailsOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete mails again");
            System.out.println("Enter something else if you don't want to delete mails");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
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
