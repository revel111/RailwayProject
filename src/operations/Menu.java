package operations;

import customVariables.Locomotive;
import customVariables.Rail;
import customVariables.Station;
import customVariables.Trainset;
import customVariables.customCars.Car;
import customVariables.customExtra.TooManyCarsException;
import customVariables.customExtra.TooManyException;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    boolean isAvailable = true;

    public static void menu() throws TooManyCarsException, TooManyException {
        while (true) {
            System.out.println("Choose what to do");
            System.out.println("Enter 1 if you want to create station");
            System.out.println("Enter 2 if you want to create rail");
            System.out.println("Enter 3 if you want to create locomotive");
            System.out.println("Enter 4 if you want to create car");
            System.out.println("Enter 5 if you want to create trainset");
            System.out.println("Enter 6 if you want to manage trainset");
            System.out.println("Enter 7 if you want to launch trainset");
            System.out.println("Enter 8 if you want to delete station");
            System.out.println("Enter 9 if you want to delete rail");
            System.out.println("Enter 10 if you want to delete locomotive");
            System.out.println("Enter 11 if you want to delete car");
            System.out.println("Enter 12 if you want to delete trainset");
            System.out.println("Enter 13 if you want to print all stations");
            System.out.println("Enter 14 if you want to print all rails");
            System.out.println("Enter 15 if you want to print all locomotives");
            System.out.println("Enter 16 if you want to print all cars");
            System.out.println("Enter 17 if you want to print all trainsets");
            System.out.println("Enter 0 if you want to quit");
            String ch = scanner.nextLine();

            switch (ch) {
                case "1":
                    Station.createStation();
                    break;
                case "2":
                    Rail.createRail();
                    break;
                case "3":
                    Locomotive.createLocomotive();
                    break;
                case "4":
                    Car.createCar();
                    break;
                case "5":
                    Trainset.createTrainset();
                    break;
                case "6":
                    Trainset.manageTrainset();
                    break;
                case "7":
                    Trainset.launchTrainset();
                    break;
                case "8":
                    Station.deleteStation();
                    break;
                case "9":
                    Rail.deleteRail();
                    break;
                case "10":
                    Locomotive.deleteLocomotive();
                    break;
                case "11":
                    Car.deleteCar();
                    break;
                case "12":
                    Trainset.deleteTrainset();
                    break;
                case "13":
                    DataLists.printData(DataLists.getStations());
                    break;
                case "14":
                    DataLists.printData(DataLists.getRails());
                    DataLists.printData(DataLists.getRailsReversed());
                    break;
                case "15":
                    DataLists.printData(DataLists.getLocomotives());
                    break;
                case "16":
                    DataLists.printData(DataLists.getCars());
                    break;
                case "17":
                    DataLists.printData(DataLists.getTrainsets());
                    break;
                case "0":
                    for (int i = 0; i < DataLists.getThreads().size(); i++)
                        DataLists.getThreads().get(i).interrupt();
                    return;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
}