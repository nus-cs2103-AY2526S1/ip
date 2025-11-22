package watermelon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import watermelon.exception.InvalidDateTimeException;
import watermelon.exception.InvalidInputException;
import watermelon.exception.StorageOperationException;
import watermelon.task.Deadline;
import watermelon.task.Event;
import watermelon.task.Task;
import watermelon.task.Todo;

/**
 * Handles saving and loading of tasks to/from disk storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor that accepts a file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An {@link ArrayList} of tasks loaded from file, empty list if file doesn't exist.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTaskFromFile(line.trim());
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (InvalidDateTimeException e) {
                    System.out.println("Skipping task entry with invalid date/time format: " + line);
                } catch (InvalidInputException e) {
                    System.out.println("Skipping task entry with invalid date/time: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasklist The list of tasks to save.
     * @throws StorageOperationException If there's an error writing to file.
     *
     * Written with the help of Claude.
     */
    @SuppressWarnings("checkstyle:JavadocTagContinuationIndentation")
    public void saveTasks(TaskList tasklist) throws StorageOperationException {
        createDirectory();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            tasklist.saveToFile(writer);
        } catch (IOException e) {
            throw new StorageOperationException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Creates the parent directory if it doesn't exist.
     *
     * @throws StorageOperationException If directory creation fails.
     *
     * Written with the help of Claude.
     */
    @SuppressWarnings("checkstyle:JavadocTagContinuationIndentation")
    private void createDirectory() throws StorageOperationException {
        try {
            Path parentDir = Paths.get(filePath).getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error creating directory: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and creates the corresponding Task object.
     *
     * @param line The line to parse.
     * @return Task object created from the line, null if parsing fails.
     */
    private Task parseTaskFromFile(String line) throws InvalidDateTimeException, InvalidInputException {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            return null; // Invalid format
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            if (parts.length >= 4) {
                String by = parts[3].trim();
                task = new Deadline(description, by, isDone);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to, isDone);
            }
            break;
        default:
            // Skip over lines with unknown task type
            System.out.println("Skipping task entry with unknown task type: " + taskType);
        }

        return task;
    }

    /**
     * Checks if the storage file exists.
     *
     * @return true if the file exists, false otherwise.
     */
    public boolean fileExists() {
        return new File(filePath).exists();
    }

    public String getFilePath() {
        return filePath;
    }
}
