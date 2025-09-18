package joobot.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import joobot.task.Deadline;
import joobot.task.Event;
import joobot.task.Task;
import joobot.task.ToDo;

/**
 * Handles saving and loading tasks from the local file system.
 * Provides persistent storage for tasks between sessions.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a Storage object tied to a specific file path.
     *
     * @param filePath path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads all tasks from the storage file.
     * If the file does not exist, it is created and an empty list is returned.
     *
     * @return list of tasks loaded from file, or empty list if file does not exist
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent()); // create ./data if missing
                Files.createFile(filePath); // create joobot.txt if missing
                return tasks; // empty list
            }

            try (Scanner scanner = new Scanner(new File(filePath.toString()))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves all tasks into the storage file.
     * Overwrites any existing content.
     *
     * @param tasks list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath.toFile(), false)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file into a {@link Task} object.
     * Supports {@link ToDo}, {@link Deadline}, and {@link Event} tasks.
     *
     * @param line line from storage file
     * @return Task object if successfully parsed, or {@code null} if corrupted
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String desc = parts[2];

            switch (type) {
            case "T":
                Task todo = new ToDo(desc);
                if (isDone) {
                    todo.markDone();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(desc, parts[3]);
                if (isDone) {
                    deadline.markDone();
                }
                return deadline;
            case "E":
                Task event = new Event(desc, parts[3], parts[4]);
                if (isDone) {
                    event.markDone();
                }
                return event;
            default:
                return null; // error line
            }
        } catch (Exception e) {
            System.out.println("Corrupted line ignored: " + line);
            return null;
        }
    }
}
