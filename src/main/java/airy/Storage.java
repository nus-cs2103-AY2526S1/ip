package airy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store the data to the files and fetch data from files
 */
public class Storage {
    private static final Path DATA_FOLDER = Paths.get("data");
    private static final Path DATA_FILE = DATA_FOLDER.resolve("Airy.txt"); // Goes to data/Airy.txt path

    /**
     * Creates the file if it does not exist
     *
     * @throws AiryException if the directory or file cannot be created
     */
    private static void fileExists() {
        try {
            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectories(DATA_FOLDER);
            }
            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
            }
        } catch (IOException e) {
            throw new AiryException("Error creating data file"); // Throws an error
        }
    }

    /**
     * Loads tasks from ./data/Airy.txt.
     * Create file if it doesn't exist it and returns empty list.
     *
     * @return an ArrayList containing all loaded tasks, or an empty list if no tasks exist or errors occur
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            fileExists();
            // Guard clause to exit early if file is empty
            if (Files.size(DATA_FILE) == 0) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(DATA_FILE); // If file is empty, it throws an error
            for (String line : lines) {
                Task t = createTask(line); // Creates the task
                if (t != null) {
                    tasks.add(t); // Stores the task in the ArrayList
                }
            }
        } catch (Exception e) {
            // Log but do not throw
            System.out.println("Warning, could not load tasks, starting with empty list instead.");
        }
        return tasks;
    }

    /**
     * Creates task from the raw data fetched from the file
     *
     * @param line a single line from the storage file
     * @return the created Task object, or null if the line format is invalid
     */
    private static Task createTask(String line) {
        // Split into at most 4 pieces
        String[] parts = line.split("\\s*\\|\\s*", 5);
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String name = parts[2].trim();

        Task task = null;
        // Switch statement to create respective tasks based on their data
        switch (type) {
        case "T": // To do Type
            task = new Todo(name);
            break;
        case "D": // Deadline Type
            assert parts.length == 4 : "Deadline type must have 5 parts";
            if (parts.length == 4) {
                String dueDate = parts[3].trim();
                task = new Deadline(name, dueDate);
            }
            break;
        case "E": // Event Type
            assert parts.length == 5 : "Event type must have 5 parts";
            if (parts.length == 5) {
                String startDate = parts[3].trim();
                String endDate = parts[4].trim();
                task = new Event(name, startDate, endDate);
            }
            break;
        default:
            return null;
        }

        if (task != null && isCompleted) {
            task.markCompleted();
        }

        return task;
    }

    /**
     * Saves the current tasks list to ./data/Airy.txt
     * Note it overwrites the file
     *
     * @param tasks the list of tasks to be saved to storage
     */
    public static void save(ArrayList<Task> tasks) {
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(taskFormat(task)); // Change formatting for every Task inside ArrayList tasks to a String
        }
        try {
            fileExists();
            // Truncate file before writing
            Files.write(DATA_FILE, data,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("Could not save tasks to storage.");
        }
    }

    /**
     * Converts a Task object into a string with formatting for storage.
     *
     * @param t the Task object to be serialized
     * @return a string representation of the task in storage format
     */
    private static String taskFormat(Task t) {
        String isCompleted = t.getStatus().equals("X") ? "1" : "0";

        if (t instanceof Todo) {
            return String.format("T | %s | %s", isCompleted, t.getTaskName());
        } else if (t instanceof Deadline) {
            return String.format("D | %s | %s | %s", isCompleted, t.getTaskName(), t.getExtraDetailsForStorage());
        } else {
            assert t instanceof Event : "Unknown task type encountered";
            return String.format("E | %s | %s | %s", isCompleted, t.getTaskName(), t.getExtraDetailsForStorage());
        }
    }
}
