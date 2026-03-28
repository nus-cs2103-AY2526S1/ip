package clippy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import clippy.ClippyException;
import clippy.task.DeadlineTask;
import clippy.task.EventTask;
import clippy.task.Task;
import clippy.task.ToDoTask;

/**
 * Manages the storage of tasks to and from a file.
 * It handles loading tasks from the file at startup and saving tasks to the file when they are modified.
 */
public class Storage {
    private static final String DIR = "data";
    private static final String FILE_PATH = "data/tasks.txt";

    private final Path filePath;

    /**
     * Constructs a Storage object and ensures the storage file exists.
     */
    public Storage() {
        this.filePath = Paths.get(FILE_PATH);
        ensureFile();
    }

    /**
     * Constructs a Storage object with a custom file path and ensures the storage file exists.
     * For testing purposes only ******
     *
     * @param filePath The custom file path for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFile();
    }

    /**
     * Ensures that the storage directory and file exist.
     * If they do not exist, they are created.
     */
    private void ensureFile() {
        try {
            Path dirPath = Paths.get(DIR);
            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error storing file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException | ClippyException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line The line to parse.
     * @return The corresponding Task object.
     * @throws ClippyException If there is an error in parsing the task.
     */
    private Task parseTask(String line) throws ClippyException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        switch (type) {
        case "T":
            Task todo = new ToDoTask(parts[2]);
            if (isDone) {
                todo.markAsCompleted();
            }
            return todo;
        case "D":
            Task deadline = new DeadlineTask(parts[2], parts[3]);
            if (isDone) {
                deadline.markAsCompleted();
            }
            return deadline;
        case "E":
            Task event = new EventTask(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markAsCompleted();
            }
            return event;
        default:
            throw new ClippyException("Unknown task type in file.");
        }
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(serializeTask(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Serializes a Task object into a string for storage.
     *
     * @param task The task to serialize.
     * @return The serialized string representation of the task.
     */
    private String serializeTask(Task task) {
        String status = task.isCompleted() ? "1" : "0";
        if (task instanceof ToDoTask) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof DeadlineTask) {
            return "D | " + status + " | " + task.getDescription() + " | "
                    + ((DeadlineTask) task).getBy().toStorageString();
        } else if (task instanceof EventTask) {
            return "E | " + status + " | " + task.getDescription() + " | "
                    + ((EventTask) task).getFrom() + " | " + ((EventTask) task).getTo();
        }
        return "";
    }
}
