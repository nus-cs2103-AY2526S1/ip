package pagrobot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pagrobot.tasklist.TaskList;
import pagrobot.tasks.Deadline;
import pagrobot.tasks.Event;
import pagrobot.tasks.Task;
import pagrobot.tasks.ToDo;

/**
 * Handles reading from and writing to the storage file for tasks.
 */
public class Storage {
    /** Path to the storage file. */
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current TaskList to the storage file.
     *
     * Returns true if the operation succeeds, false otherwise.
     *
     * @param stored the TaskList to save.
     * @return true if saved successfully, false if an error occurred.
     */
    public boolean toMemory(TaskList stored) {
        File file = new File(this.filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task t : stored) {
                writer.write(t.toMemory());
                writer.write(System.lineSeparator());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads tasks from the storage file into memory.
     *
     * Returns a TaskList containing all tasks found in the file.
     * If the file does not exist, a new empty TaskList is created.
     * Returns null if an error occurs while reading the file.
     *
     * @return a TaskList loaded from the file, or null if an error occurs.
     */
    public TaskList getMemory() {
        File file = new File(this.filePath);

        try {
            TaskList stored = new TaskList();
            if (!file.exists()) {
                file.createNewFile();
                return stored;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stored.add(this.parseMemory(line));
            }

            scanner.close();
            return stored;
        } catch (IOException e) {
            System.out.println("Error handling file: " + e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * Returns the corresponding Task (ToDo, Deadline, Event) based on the line
     * prefix.
     * Returns null if the task type is unrecognized.
     *
     * @param input the line from the storage file.
     * @return a Task object parsed from the line, or null if invalid.
     */
    public Task parseMemory(String input) {
        String[] parts = input.split("\\|");
        return switch (parts[0]) {
        case "T" -> ToDo.fromMemory(input);
        case "D" -> Deadline.fromMemory(input);
        case "E" -> Event.fromMemory(input);
        default -> null;
        };
    }
}
