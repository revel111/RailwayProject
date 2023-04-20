import customVariables.Rail;
import customVariables.Trainset;
import customVariables.customExtra.TooManyCarsException;
import customVariables.customExtra.TooManyException;
import operations.DataLists;
import operations.Menu;


public class Presentation {
    public static void main(String[] args) throws TooManyException, TooManyCarsException {
        boot();
    }

    public static void boot() throws TooManyCarsException, TooManyException {
        DataLists.initializeLists();
        Rail.createRailsRandomly();
        Trainset.generateTrainsetsRandomly();
        Thread file = new Thread(DataLists.getFiles());
        file.start();
        Menu.menu();
    }
}
