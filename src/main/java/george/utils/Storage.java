package george.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import george.exceptions.GeorgeException;
import george.task.DeadlineTask;
import george.task.EventTask;
import george.task.Task;
import george.task.ToDoTask;

/**
 * Handles file storage operations for tasks, including loading and saving tasks
 * to a persistent data file. Manages file creation and directory structure.
 */
public class Storage {
    private static final String FILE_DIRECTORY = "./data/";
    private final String fileName;
    private final Path dataPath;

    /**
     * Constructs a Storage object with the specified file name.
     *
     * @param fileName The name of the file to store tasks data
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.dataPath = Paths.get(FILE_DIRECTORY + fileName);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file
     * @throws IOException If an I/O error occurs during file reading
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(dataPath)) {
            System.out.println("File does not exist yet!");
            System.out.println("Creating new file");
            ensureDirectoryExists();
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Task task = parseTaskFromLine(line);
                    tasks.add(task);
                } catch (GeorgeException e) {
                    System.out.println("Failed to parse this line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved
     * @throws IOException If an I/O error occurs during file writing
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        ensureDirectoryExists();

        try {
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(task.toString());
            }

            Files.write(dataPath, lines);
            System.out.println("Saved " + tasks.size() + " tasks to " + dataPath);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     *
     * @param line The line from the storage file
     * @return The parsed Task object
     * @throws GeorgeException If the line format is invalid or cannot be parsed
     */
    private Task parseTaskFromLine(String line) throws GeorgeException {
        String[] parts = line.split(" \\| ");
        String type = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");

        switch (type) {
        case "D":
            // For deadlines, parts[3] is the date string like "Apr 25 2025"
            LocalDateTime deadlineDate = DateTimeParser.parseStoredDate(parts[3].trim());
            return new DeadlineTask(parts[2], deadlineDate, isCompleted);
        case "E":
            // For events, parts[3] is start datetime and parts[4] is end datetime
            // Both are in format like "Dec 05 2025 15:00"
            LocalDateTime startDateTime = DateTimeParser.parseStoredDateTime(parts[3].trim());
            LocalDateTime endDateTime = DateTimeParser.parseStoredDateTime(parts[4].trim());
            return new EventTask(parts[2], startDateTime, endDateTime, isCompleted);
        default:
            return new ToDoTask(parts[2], isCompleted);
        }
    }

    /**
     * Ensures that the data directory and file exist, creating them if necessary.
     *
     * @throws IOException If an I/O error occurs during directory/file creation
     */
    private void ensureDirectoryExists() throws IOException {
        if (!Files.exists(dataPath.getParent())) {
            Files.createDirectories(dataPath.getParent());
        }
        if (!Files.exists(dataPath)) {
            Files.createFile(dataPath);
            System.out.println("Created file at: " + dataPath);
        }
    }
}
