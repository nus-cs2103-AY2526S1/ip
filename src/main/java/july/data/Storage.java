package july.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import july.error.InvalidDeadlineException;
import july.error.InvalidEventException;
import july.tasks.Deadline;
import july.tasks.Event;
import july.tasks.Task;
import july.tasks.Todo;
import july.tasks.Fix;

/**
 * Write and red data to and from the file system.
 * This class manages loading tasks from a file on startup and saving
 * tasks to a file for persistence across application sessions.
 */
public class Storage {
    private final File f;

    /**
     * Constructs a Storage object with the specified file path.
     * Creates the file and its parent directories if they don't exist.
     *
     * @param filePath the path to the file used for storing task data
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
        if (!f.getParentFile().mkdirs() && !f.getParentFile().exists()) {
            System.out.println("Error creating directories for file: " + filePath);
        }
        try {
            if (!f.createNewFile() && !f.exists()) {
                System.out.println("Error creating file: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList of task.
     * Reads each line from the file, parses it into a Task object, and adds
     * valid tasks to the returned list. Corrupted or invalid lines are skipped.
     *
     * @return an ArrayList containing all successfully loaded tasks, or an empty list
     *         if the file doesn't exist or contains no valid tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            return tasks;
        }
        return tasks;
    }

    /**
     * Saves the provided task list to the storage file.
     * Writes each task's save representation to the file, with each task
     * on a separate line. Overwrites any existing file content.
     *
     * @param tasks the list of tasks to save to the file
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(f)) {
            for (Task task : tasks) {
                fw.write(task.toSave() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * Attempts to reconstruct a task from its saved format by parsing the
     * task type, completion status, and description. Handles corrupted lines
     * gracefully by returning null and logging error messages.
     *
     * @param line the line from the file to parse into a Task
     * @return the parsed Task object, or null if the line is corrupted or invalid
     */
    private Task parseTask(String line) {
        try {
            String[] argument = line.split("\\s+", 3);
            String keyword = argument[0].trim();
            boolean isDone = argument[1].trim().equals("1");
            String description = argument[2].trim();

            switch (keyword) {
            case "T":
                Task todoTask = new Todo(description);
                todoTask.setDone(isDone);
                return todoTask;
            case "D":
                try {
                    Task deadlineTask = Deadline.process(description);
                    deadlineTask.setDone(isDone);
                    return deadlineTask;
                } catch (InvalidDeadlineException e) {
                    System.out.println("Error reading Deadline task");
                }
                break;
            case "E":
                try {
                    Task eventTask = Event.process(description);
                    eventTask.setDone(isDone);
                    return eventTask;
                } catch (InvalidEventException e) {
                    System.out.println("Error reading Event task");
                }
                break;
            case "F":
                try {
                    Task fixTask = Fix.process(description);
                    fixTask.setDone(isDone);
                    return fixTask;
                } catch (Exception e) {
                    System.out.println("Error reading Fix task");
                }
                break;
            default:
                return null;
            }
        } catch (Exception e) {
            System.out.println("Found corrupted line: " + line);
            return null;
        }
        return null;
    }
}
