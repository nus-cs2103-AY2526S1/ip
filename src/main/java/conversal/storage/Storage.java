package conversal.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import conversal.task.Deadline;
import conversal.task.DoWithinPeriodTask;
import conversal.task.Event;
import conversal.task.Task;
import conversal.task.Todo;

/**
 * Handles the saving and loading of tasks to and from the hard disk.
 *
 * The Storage class reads and writes task data to a text file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage object that will load from and save to
     * the given file path.
     *
     * @param filePath the path to the file used for storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into memory.
     *
     * If the file does not exist, an empty list is returned.
     * Each line in the file is expected to be in the format:
     *
     * T | isDone | description
     * D | isDone | description | yyyy-MM-dd
     * E | isDone | description | start-end
     * W | isDone | description | period
     *
     * @return an ArrayList of tasks reconstructed from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            assert loadedTasks != null : "loadedTasks must not be null after reading";
        } catch (IOException e) {
            System.out.println("Unable to load task: " + e.getMessage());
        }

        return loadedTasks;
    }

    /** Converts each line into a task. */
    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Each data line must have at least 3 parts";

        boolean isDone = parts[1].equals("1");
        Task task = createTaskFromParts(parts);

        if (task != null && isDone) {
            task.markAsComplete();
        }
        return task;
    }

    /** Constructs a Task instance from the parts of each line */
    private Task createTaskFromParts(String[] parts) {
        switch (parts[0]) {
        case "D":
            assert parts.length >= 4 : "deadlines must include a date";
            return new Deadline(parts[2], LocalDate.parse(parts[3]));
        case "E":
            assert parts.length >= 4 : "events must include schedule";
            String[] subParts = parts[3].split("-", 2);
            assert subParts.length == 2 : "event schedule must be 'start-end'";
            return new Event(parts[2], subParts[0], subParts[1]);
        case "W":
            assert parts.length >= 4 : "DoWithinPeriodTask must include a period";
            return new DoWithinPeriodTask(parts[2], parts[3]);
        default:
            return new Todo(parts[2]);
        }
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * The file will be created if it does not exist.
     * Each task will be added in the following format:
     *
     * T | isDone | description
     * D | isDone | description | yyyy-MM-dd
     * E | isDone | description | start-end
     * W | isDone | description | period
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "tasks list to save must not be null";
        File file = new File(this.filePath);
        ensureParentDir(file);

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                assert task != null : "task to save must not be null";
                fw.write(serialise(task));
            }
        } catch (IOException e) {
            System.out.println("Unable to save task: " + e.getMessage());
        }
    }

    /** Ensures the parent directory of the storage file exists. */
    private void ensureParentDir(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /** Converts a Task to its line representation inside hard disc (with newline). */
    private String serialise(Task task) {
        String type = taskType(task);
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String extra = extraDescription(task);

        return type + " | " + isDone + " | " + description + extra + System.lineSeparator();
    }

    /** Determines the single-letter task type representation. */
    private String taskType(Task task) {
        if (task instanceof Deadline) {
            return "D";
        }
        if (task instanceof Event) {
            return "E";
        }
        if (task instanceof DoWithinPeriodTask) {
            return "W";
        }
        return "T";
    }

    /** Returns the extra description for types that need it, or empty string. */
    private String extraDescription(Task task) {
        if (task instanceof Deadline) {
            return " | " + ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            return " | " + ((Event) task).getSchedule();
        } else if (task instanceof DoWithinPeriodTask) {
            return " | " + ((DoWithinPeriodTask) task).getPeriod();
        }
        return "";
    }
}
