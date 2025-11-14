package storage;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import exception.BaymaxException;

import task.*;

/**
 * storage.Storage class handles reading and writing task.Task objects to a file.
 * Tasks are persisted in a text file so that they can be loaded on
 * program start and saved when modified.
 */
public class Storage {
    private final Path filePath;


    /**
     * Constructs a storage.Storage object using the default file path.
     * The default file path is "./data/baymax.txt".
     */
    public Storage() {
        this.filePath = Paths.get("data", "baymax.txt");
    }

    /**
     * Constructs a storage.Storage object using the given test file path.
     * This constructor is used for testing purposes.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads tasks from the storage file.
     * If the file or its parent directories do not exist, they are created
     * and an empty list is returned.
     *
     * @return an ArrayList containing all tasks read from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (Files.notExists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return tasks;
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                try {
                    Task task = parse(line);
                    if (task != null) tasks.add(task);
                } catch (BaymaxException e) {
                    System.err.println("Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the list of tasks to save
     */
    public void save(List<Task> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            List<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(format(t));
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file and converts it into a task.Task object.
     *
     * @param line a single line from the file representing a task
     * @return a task.Task object corresponding to the line
     * @throws IllegalArgumentException if the task type is unknown
     */
    private Task parse(String line) throws BaymaxException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) throw new BaymaxException("Corrupted line: " + line);

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        switch (type) {
        case "T":
            return new Todo(desc, TaskType.TODO, isDone);
        case "D":
            if (parts.length < 4) throw new BaymaxException("Deadline missing date: " + line);
            return new Deadline(desc, TaskType.DEADLINE, parts[3], isDone);
        case "E":
            if (parts.length < 5) throw new BaymaxException("Event missing dates: " + line);
            return new Event(desc, TaskType.EVENT, parts[3], parts[4], isDone);
        default:
            throw new BaymaxException("Unknown task type: " + type);
        }
    }


    /**
     * Converts a task.Task object into a string that can be saved in the storage file.
     *
     * @param t the task to format
     * @return a string representation of the task for file storage
     */
    private String format(Task t) {
        String done = t.getStatus() ? "1" : "0";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy"); // matches constructor

        if (t instanceof Todo td) {
            return String.format("T | %s | %s", done, td.getDescription());
        } else if (t instanceof Deadline d) {
            return String.format("D | %s | %s | %s", done, d.getDescription(), d.getBy().format(formatter));
        } else if (t instanceof Event e) {
            return String.format("E | %s | %s | %s | %s", done, e.getDescription(),
                    e.getFrom().format(formatter), e.getTo().format(formatter));
        } else {
            throw new IllegalStateException("Unknown task subclass");
        }
    }


}
