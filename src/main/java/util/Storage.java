package util; // src/main/java/util.Storage.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import core.Task;
import core.TaskList;

/**
 * Handles file I/O operations for persistent storage of tasks.
 * Manages saving and loading tasks to/from a text file.
 */
public class Storage {
    private static final String DIR = "data";
    private static final String FILE = "tronalddump.txt";
    private final Path filePath;

    /**
     * Constructor for Storage class.
     * Initializes the file path and creates the storage file if it doesn't exist.
     */
    public Storage() {
        this.filePath = Paths.get(DIR, FILE);
        createFileIfMissing();
    }

    private void createFileIfMissing() {
        try {
            if (!Files.exists(Paths.get(DIR))) {
                Files.createDirectory(Paths.get(DIR));
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     * @return TaskList containing all loaded tasks
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        Task task = Task.fromStorageString(line);
                        tasks.addTask(task);
                    } catch (Exception e) {
                        System.err.println("Corrupted line skipped: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     * Data format: TYPE | isDone | description (includes all information aka deadlines, event times)
     * @param tasks List of tasks to save
     */
    public void save(List<Task> tasks) {
        if (tasks == null) {
            System.err.println("Cannot save null task list");
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                if (task != null) {
                    writer.write(task.toStorageString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public boolean isFileAccessible() {
        return Files.exists(filePath) && Files.isReadable(filePath) && Files.isWritable(filePath);
    }
}
