package balloon.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import balloon.task.Deadline;
import balloon.task.Event;
import balloon.task.Task;
import balloon.task.Todo;

/**
 * Stores and maintains task data across and during application sessions.
 * <p>
 * This class reads the list of tasks from a data file at application startup,
 * and writes updates back to the file whenever the task list is modified.
 * It supports {@link Todo}, {@link Deadline}, and {@link Event} tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with a specified file path.
     *
     * @param filePath the path to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructs a {@code Storage} object with an empty file path.
     */
    public Storage() {
        filePath = "";
    }

    /**
     * Loads the saved tasks from the save file.
     * If the file does not exist, returns an empty task list.
     *
     * @return a list of tasks represented by the save file.
     */
    public ArrayList<Task> loadSavedTasks() {
        ArrayList<Task> tasks = new ArrayList<>(100);
        File file = new File(filePath);

        if (!file.exists()) {
            // No save file yet, return empty list
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseLineForTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        assert tasks != null : "Loaded tasks should not be a null";

        return tasks;
    }

    /**
     * Saves the current list of tasks to the save file.
     * <p>
     * Each task is represented as a single line in the file in a
     * specific format that can later be parsed
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure ./data/ directory exists

        try (FileWriter fw = new FileWriter(file)) {
            // Write the tasks currently in the task list
            for (Task t : tasks) {
                fw.write(t.toSaveFormat() + System.lineSeparator());
            }

            assert new File(filePath).exists() : "Save file should exist after saving";
            // the above line does not create a file
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Converts a line representing a task from the save file into a corresponding Task object.
     * <p>
     * Format for TODO: TODO | STATUS | DESCRIPTION
     * <p>
     * Format for DEADLINE: DEADLINE | STATUS | DESCRIPTION | BY
     * <p>
     * Format for EVENT: EVENT | STATUS | DESCRIPTION | FROM | TO
     * <p>
     * where STATUS is 1 if task is done; otherwise STATUS is 0.
     *
     * @param line  a line that represents a task from the save file.
     * @return      a Task object that the line represents.
     */
    public Task parseLineForTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        switch (type) {
        case "TODO":
            task = new Todo(description);
            break;
        case "DEADLINE":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "EVENT":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type in save file: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}
