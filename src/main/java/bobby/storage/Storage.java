package bobby.storage;

import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles saving and loading tasks to and from a file for persistence.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath Path to the file for storing tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the file.
     * Creates parent directories if they do not exist.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Tasks list should not be null";
        Path path = Paths.get(filePath);
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Loads tasks from the file.
     * Returns an empty list if the file does not exist or lines are malformed.
     *
     * @return ArrayList of loaded tasks.
     * @throws IOException If an I/O error occurs during loading.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return tasks; // Return empty list if file doesn't exist
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
                try {
                    switch (type) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                        break;
                    default:
                        // Unknown type, skip
                        break;
                    }
                } catch (Exception e) {
                    // Skip malformed lines
                    continue;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Loads tasks from the file.
     * Returns an empty list if the file does not exist or lines are malformed.
     *
     * @return ArrayList of loaded tasks.
     * @throws IOException If an I/O error occurs during loading.
     */
    public ArrayList<Task> loadTasksWithWarnings(ArrayList<String> warnings) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return tasks; // Return empty list if file doesn't exist
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts.length > 1 && parts[1].equals("1");
                String description = parts.length > 2 ? parts[2] : "";
                Task task = null;
                try {
                    switch (type) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        if (parts.length < 4) throw new Exception("Malformed deadline line");
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        if (parts.length < 5) throw new Exception("Malformed event line");
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                        break;
                    default:
                        warnings.add("Line " + lineNum + ": Unknown task type, skipped.");
                        break;
                    }
                } catch (Exception e) {
                    warnings.add("Line " + lineNum + ": Malformed or corrupt, skipped. Reason: " + e.getMessage());
                    continue;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                if (task != null) {
                    tasks.add(task);
                }
                lineNum++;
            }
        } catch (IOException e) {
            throw new IOException("Could not read tasks file: " + e.getMessage(), e);
        }
        return tasks;
    }
}