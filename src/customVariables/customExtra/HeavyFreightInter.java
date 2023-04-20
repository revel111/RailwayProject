package customVariables.customExtra;


public interface HeavyFreightInter {
    public void fillCar() throws TooManyException;

    public void emptyCar();

    public double getFill();
    public double getMaxFill();

    public static Runnable createToxicLiquid() {
        return null;
    }
}