package customVariables.customExtra;


public interface HeavyFreightInter {
    public void fillCar() throws TooManyException;

    public void emptyCar();

    public double gerFill();

    public double getMaxFill();
}