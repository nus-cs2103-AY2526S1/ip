package lyra.util;

import lyra.task.Task;
import lyra.task.Todo;
import lyra.task.Deadline;
import lyra.task.Event;
import lyra.task.TaskList;
import lyra.exception.LyraException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Handles persistent storage of tasks in the Lyra application.
 * Provides methods to load tasks from a file and save tasks to a file.
 * Supports automatic creation of data directory and file if they don't exist.
 */
public class Storage {
    private final Path dataFile;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath the path to the data file for storing tasks
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "filePath must not be null or empty";
        this.dataFile = Paths.get(filePath);
        assert this.dataFile != null : "dataFile path must be initialized";
    }

    /**
     * Loads tasks from the data file.
     * Creates the data directory and file if they don't exist.
     * Parses each line of the file to reconstruct Task objects.
     *
     * @return an ArrayList of loaded tasks
     * @throws LyraException if an error occurs during file operations
     */
    public ArrayList<Task> load() throws LyraException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path dataDir = dataFile.getParent();
        
        try {
            if (Files.exists(dataFile)) {
                for (String fileLine : Files.readAllLines(dataFile)) {
                    assert fileLine != null : "file line must not be null";
                    Task task = parseTaskFromFile(fileLine);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } else {
                // Create directory and file if they don't exist
                if (dataDir != null && !Files.exists(dataDir)) {
                    Files.createDirectories(dataDir);
                }
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            throw new LyraException("Error loading tasks from file: " + e.getMessage());
        }
        
        return tasks;
    }

    /**
     * Saves the current list of tasks to the data file.
     * Converts each task to its data string representation and writes to file.
     *
     * @param tasks the list of tasks to save
     * @throws LyraException if an error occurs during file writing
     */
    public void save(ArrayList<Task> tasks) throws LyraException {
        assert tasks != null : "tasks to save must not be null";
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null : "task item must not be null";
            if (task instanceof Todo) {
                lines.add(((Todo) task).toDataString());
            } else if (task instanceof Deadline) {
                lines.add(((Deadline) task).toDataString());
            } else if (task instanceof Event) {
                lines.add(((Event) task).toDataString());
            }
        }
        
        try {
            Files.write(dataFile, lines);
        } catch (IOException e) {
            throw new LyraException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file and reconstructs the corresponding Task object.
     * Supports the following formats:
     * - T | status | description (for Todo tasks)
     * - D | status | description | deadline (for Deadline tasks)
     * - E | status | description | from to to (for Event tasks)
     *
     * @param fileLine a single line from the data file
     * @return the reconstructed Task object, or null if parsing fails
     */
    private Task parseTaskFromFile(String fileLine) {
        String[] parts = fileLine.split("\\|", -1);
        // Expect formats:
        // T | 1 | description
        // D | 0 | description | by
        // E | 0 | description | from to to
        if (parts.length >= 3) {
            String type = parts[0].trim();
            String doneFlag = parts[1].trim();
            String description = parts[2].trim();
            Task task = null;
            
            if ("T".equalsIgnoreCase(type)) {
                task = new Todo(description);
            } else if ("D".equalsIgnoreCase(type) && parts.length >= 4) {
                String byStr = parts[3].trim();
                LocalDate by = LocalDate.parse(byStr, DateTimeUtil.STORAGE_DATE);
                task = new Deadline(description, by);
            } else if ("E".equalsIgnoreCase(type) && parts.length >= 4) {
                // For simplicity, we store the range as a single field "from to to"
                String range = parts[3].trim();
                String from;
                String to;
                int sepIdx = range.lastIndexOf(" to ");
                if (sepIdx >= 0) {
                    from = range.substring(0, sepIdx);
                    to = range.substring(sepIdx + 4);
                } else {
                    from = range;
                    to = "";
                }
                LocalDate fromDate = LocalDate.parse(from.trim(), DateTimeUtil.STORAGE_DATE);
                LocalDate toDate = LocalDate.parse(to.trim(), DateTimeUtil.STORAGE_DATE);
                task = new Event(description, fromDate, toDate);
            }
            
            if (task != null && "1".equals(doneFlag)) {
                task.markAsDone();
            }
            
            return task;
        }
        return null;
    }
}
