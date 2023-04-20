package customVariables.customExtra;


public interface HeavyFreightInter {
    void fillCar() throws TooManyException;

    void emptyCar();

    double getFill();
    double getMaxFill();

    static Runnable createToxicLiquid() {
        return null;
    }
}