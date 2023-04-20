package customVariables.customCars;

import customVariables.Rail;
import customVariables.customExtra.TooManyException;
import operations.DataLists;

import java.util.Scanner;

public class RestaurantCar extends Car {
    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 555;
    private double weightBrutto = weightNetto;
    private final int maxAmountOfTables = 40;
    private int tables;

    public RestaurantCar(String shipper) {
        this.setGridConnection(true);
        this.setShipper(shipper);
        this.setCurrentId("car" + Car.id++);
        this.setWeightNetto(this.weightNetto);
        this.setWeightBrutto(this.weightBrutto);
    }

    @Override
    public void fillCar() throws TooManyException {
        System.out.println("Enter amount of visitors to add");
        int visitorsIn = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.tables + visitorsIn > maxAmountOfTables)
                throw new TooManyException("Too many visitors");
            else {
                this.tables += visitorsIn;
                this.setWeightBrutto(this.getWeightBrutto() + (visitorsIn * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to add visitors again");
            System.out.println("Enter something else if you don't want to add visitors");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                fillCar();
        }
    }

    @Override
    public void emptyCar() throws TooManyException {
        System.out.println("Enter amount of visitors to delete");
        int tablesOut = scanner.nextInt();
        scanner.nextLine();

        try {
            if (this.tables - tablesOut < 0)
                throw new TooManyException("There are no such amount of visitors");
            else {
                this.tables -= tablesOut;
                this.setWeightBrutto(this.getWeightBrutto() - (tablesOut * 2));
            }
        } catch (TooManyException e) {
            System.out.println("Enter 1 if you want to try to delete visitors again");
            System.out.println("Enter something else if you don't want to delete visitors");
            String ch = scanner.nextLine();

            if (ch.equals("1"))
                emptyCar();
        }
    }

    public static Runnable createRestaurantCar() {
        System.out.println("Enter name of a shipper");
        String shipper = scanner.nextLine();
        RestaurantCar refrigeratedCar= new RestaurantCar(shipper);
        DataLists.getCars().add(refrigeratedCar);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current amount of people in restaurant: " + tables;
    }
}
