package friday.storage;

import friday.exceptions.FridayTaskDecodeException;
import friday.tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FridayStorage {
    public static String filePath = "data/friday.txt";

    public FridayStorage(String fileAddress) {
        filePath = fileAddress;
    }

    /**
     * Updates the storage file whenever an alteration to the list.
     * has happened.
     * @param list is the ArrayList of tasks.
     */
    public static void writeListToFile(ArrayList<Task> list) {
        List<String> encoded = FridayEncoder.encodeTasks(list);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : encoded) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Decodes the file and returns an Array List.
     * @return an ArrayList of tasks.
     * @throws FridayTaskDecodeException if the file does not exist.
     */
    public static ArrayList<Task> readFileToList() throws FridayTaskDecodeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create file if missing
        try {
            file.getParentFile().mkdirs(); // create folder if missing
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        // Read file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            tasks = new ArrayList<>(FridayDecoder.decodeTasks(lines));
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }

        return tasks;
    }

}
