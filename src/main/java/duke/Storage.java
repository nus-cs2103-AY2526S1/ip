package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles storing and loading of data for the Duke application.
 */
public class Storage {

    private final File file;
    private final String filePath = "./data/UserInputs.txt";

    /**
     * Creates a new Storage object and ensures the data file exists.
     */
    public Storage() {
        this.file = new File(filePath).getAbsoluteFile();
        try {
            file.getParentFile().mkdirs();

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("     New storage file created");
            } else {
                System.out.println("     Using previously saved data");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to set up storage file", e);
        }
    }

    /**
     * Stores a line of user input into the data file.
     *
     * @param input the input to store into the file
     */
    public void storeData(String input) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(input);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store data", e);
        }
    }

    /**
     * Loads all previously saved inputs from the data file.
     *
     * @return list of saved input strings
     */
    public List<String> loadAll() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data", e);
        }
        return lines;
    }

    /**
     * Checks if there is existing data in the storage file.
     *
     * @return true if the data file exists and is not empty
     */
    public boolean hasData() {
        return file.exists() && file.length() > 0;
    }
}
