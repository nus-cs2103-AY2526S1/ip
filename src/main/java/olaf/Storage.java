package olaf;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

import olaf.tasks.Deadline;
import olaf.tasks.Event;
import olaf.tasks.Task;
import olaf.tasks.ToDo;

/**
 * Handles loading and saving of tasks to and from a file.
 * Responsible for keeping task data between program runs.
 */
public class Storage {

    private final String FILEPATH;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filepath Path to the file used for saving and loading tasks
     */
    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Loads the list of tasks from the file.
     * If the file does not exist, an empty list is returned.
     *
     * @return The ArrayList of tasks fetched from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILEPATH);
        //If no such file, return empty task list
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                assert parts.length >= 3 : "Malformed task line in storage file";
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];
                Task task;
                if (type.equals("T")) {
                    task = new ToDo(desc);
                    assert task != null : "Task created from list cannot be null";
                } else if (type.equals("D")) {
                    task = new Deadline(desc, parts[3]);
                    assert task != null : "Task created from list cannot be null";
                } else if (type.equals("E")) {
                    task = new Event(desc, parts[3], parts[4]);
                    assert task != null : "Task created from list cannot be null";
                } else {
                    continue;
                }
                if (isDone) {
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Failed to load tasks");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks into the file.
     * Create directories and files if they do not exist.
     *
     * @param tasks The ArrayList of tasks to be saved into the file
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks to be saved should not be null";
        File file = new File(FILEPATH);
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    assert t.getDescription() != null : "Task desc cannot be null";
                    writer.println("T | " + (t.isDone() ? "1" : "0") + " | "
                            + t.getDescription());
                } else if (t instanceof Deadline) {
                    assert t.getDescription() != null : "Task desc cannot be null";
                    Deadline d = (Deadline)t;
                    assert d.getDeadline() != null : "Deadline task deadline cannot be null";
                    writer.println("D | " + (d.isDone() ? "1" : "0") + " | "
                            + d.getDescription() + " | " + d.getDeadline());
                } else if (t instanceof Event) {
                    assert t.getDescription() != null : "Task desc cannot be null";
                    Event e = (Event)t;
                    assert e.getFrom() != null : "Event task from cannot be null";
                    assert e.getTo() != null : "Event task to cannot be null";
                    writer.println("E | " + (e.isDone() ? "1" : "0") + " | "
                            + e.getDescription() + " | " + e.getFrom() + " | "
                            + e.getTo());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }
    }
}
