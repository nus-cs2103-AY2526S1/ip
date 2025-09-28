package alpaca;

import alpaca.task.Deadline;
import alpaca.task.Event;
import alpaca.task.Task;
import alpaca.task.Todo;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {

    /** Default directory for storing task files. */
    private static final String DIR = "data";

    /** Path to the specific file for storing tasks. */
    private final String filePath;

    /**
     * Constructs a Storage instance with a given file path.
     *
     * @param filePath Path to the task file.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file. If the file or directory does not exist, they will be created.
     * Invalid or missing data will be skipped with a warning.
     *
     * @return ArrayList of tasks loaded from file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Ensure directory exists
            Path dirPath = Paths.get(DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                return tasks;
            }

            // Use try-with-resources for automatic closing
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) continue; // Skip invalid lines

                    Task t = null;
                    switch (parts[0]) {
                    case "T":
                        t = new Todo(parts[2]);
                        break;
                    case "D":
                        if (parts.length >= 4) t = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        if (parts.length >= 4) {
                            String endTime = parts.length > 4 ? parts[4] : "";
                            t = new Event(parts[2], parts[3], endTime);
                        }
                        break;
                    }

                    if (t != null && "1".equals(parts[1])) {
                        t.markAsDone();
                    }

                    if (t != null) tasks.add(t);
                }
            }

        } catch (Exception e) {
            System.out.println("Warning: Could not load tasks. File may be missing or corrupted.");
        }

        return tasks;
    }

    /**
     * Saves the given tasks to the file, creating directories if necessary.
     *
     * @param tasks ArrayList of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";

        try {
            // Ensure directory exists
            Path dirPath = Paths.get(DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Use try-with-resources for automatic closing
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (Task t : tasks) {
                    bw.write(t.toSaveFormat());
                    bw.newLine();
                }
            }

        } catch (Exception e) {
            System.out.println("Warning: Could not save tasks.");
        }
    }
}
