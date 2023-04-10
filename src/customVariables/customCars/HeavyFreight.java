package customVariables.customCars;

abstract public class HeavyFreight extends Car {
    private final double weightNetto = 1000;
    private double weightBrutto = weightNetto;
    private final double maxFill = 2000;
    private double fill;

    @Override
    public double getWeightNetto() {
        return weightNetto;
    }

    @Override
    public double getWeightBrutto() {
        return weightBrutto;
    }

    public double getFill() {
        return fill;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current fillness: " + getFill();
    }
}