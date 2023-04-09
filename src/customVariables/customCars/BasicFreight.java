package customVariables.customCars;

import java.util.Scanner;

abstract public class BasicFreight extends Car{
    private static final Scanner scanner = new Scanner(System.in);
    private String shipper;
    private boolean gridConnection; //5
    private double weightNetto;
    private double weightBrutto;
    protected static int id = 0;
    private String currentId;

    public String getShipper() {
        return shipper;
    }
}