/**
 * Deals with reading and writing tasks from the hard disk.
 * Ensures tasks are loaded at startup and saved after changes.
 */

package salah;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides static utility methods for saving and loading tasks
 * to and from a persistent data file.
 */
public class Storage {
    /** Directory where the data file is stored. */
    private static final Path DATA_DIR = Paths.get("data");
    /** File used to store serialized tasks. */
    private static final Path DATA_FILE = DATA_DIR.resolve("duke.txt");

    /**
     * Saves the given list of tasks to disk.
     * Each task is serialized into a line of text using {@link Task#serialize()}.
     *
     * @param tasks the list of tasks to save
     */
    public static void save(List<Task> tasks) {
        try {
            Files.createDirectories(DATA_DIR);
            try (FileWriter fw = new FileWriter(DATA_FILE.toFile());
                 BufferedWriter bw = new BufferedWriter(fw)) {
                for (Task t : tasks) {
                    bw.write(t.serialize());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from disk into memory.
     * Each line in the file is deserialized into a {@link Task}.
     * If the file does not exist (e.g., first run), an empty list is returned.
     *
     * @return an {@code ArrayList<Task>} containing all loaded tasks
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(DATA_FILE)) {
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = Task.deserialize(line);
                if (t != null) tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}