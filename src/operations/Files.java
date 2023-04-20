package operations;

import customVariables.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class Files extends Thread {
    public Files() {
    }

    public static String ReadFile(String type, int number) {
        String fileName = "src/txtfiles/" + type;
        Random random = new Random();
        int randomNumber = random.nextInt(number) + 1;
        String name = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int currentLine = 1;
            while ((name = br.readLine()) != null) {
                if (currentLine == randomNumber)
                    break;
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String ReadFileStations(Set<String> set) {
        String fileName = "src/txtfiles/stationnames.txt";
        Random random = new Random();
        int randomNumber = random.nextInt(121) + 1;
        String name = "";
        Station station = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int currentLine = 1;
            while ((name = br.readLine()) != null) {
                if (currentLine == randomNumber) {
                    if (set.contains(name))
                        Files.ReadFileStations(set);
                    else {
                        station = new Station(name);
                        set.add(name);
                    }
                    break;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public void run() {
        while (true) {
            try (FileWriter bw = new FileWriter("src/txtfiles/AppState.txt", true)) {
                DataLists.sortTrainsetsByDistance();
                for (int i = 0; i < DataLists.getTrainsets().size(); i++) {
                    bw.write(DataLists.getTrainsets().get(i) + "\n");
                }
                bw.write("\n");
            } catch (IOException e) {
                System.out.println("...");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("...");
            }
        }
    }
}