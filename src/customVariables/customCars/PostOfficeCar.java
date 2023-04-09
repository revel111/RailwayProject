package customVariables.customCars;

import customVariables.customExtra.TooManyException;

import java.util.Scanner;

public class PostOfficeCar extends Car {
    private static Scanner scanner = new Scanner(System.in);
    private final double weightNetto = 300;
    private double weightBrutto = weightNetto;
    private final int maxAmountOfPeople = 20;
    private int people;

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    public PostOfficeCar() {
        this.setGridConnection(true);
        this.setType("Post office car");
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

    @Override
    public String toString() {
        return super.toString() + "; Current amount of people in office: " + people;
    }

}
