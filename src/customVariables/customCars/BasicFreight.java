package customVariables.customCars;

abstract public class BasicFreight extends Car {
    private final double weightNetto = 500;
    private double weightBrutto = weightNetto;
    private final double maxFill = 1000;
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

    public void setFill(double fill) {
        this.fill = fill;
    }

    @Override
    public String toString() {
        return super.toString() + "; Current filness: " + getFill();
    }
}