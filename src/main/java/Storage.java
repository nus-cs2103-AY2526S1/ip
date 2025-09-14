import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.Todo;

/**
 * Handles the storage and retrieval of tasks from a file.
 * This class provides methods to load tasks from a file and save tasks to a file,
 * supporting persistence of the task list between application sessions.
 */
public class Storage {
    private final Path filePath;

    // Constants for parsing task data
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_EXTRA_INFO_INDEX = 3;
    private static final int TASK_SECOND_EXTRA_INFO_INDEX = 4;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored
     */
    public Storage(final String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * If the file doesn't exist, it creates the file and returns an empty task list.
     *
     * @return An ArrayList containing all loaded tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            createFileAndParent();
            return tasks;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current task list to the storage file.
     * This method ensures the file and its parent directory exist before writing.
     *
     * @param tasks The ArrayList of tasks to be saved
     */
    public void save(final ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        createFileAndParent();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                assert task != null : "Individual tasks cannot be null";
                writer.write(formatTask(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Saves multiple task lists to the storage file using varargs.
     * This method combines all tasks from multiple lists and saves them.
     * 
     * @param taskLists Variable number of task lists to be saved
     */
    @SafeVarargs
    public final void saveAll(final ArrayList<Task>... taskLists) {
        createFileAndParent();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (ArrayList<Task> tasks : taskLists) {
                for (Task task : tasks) {
                    writer.write(formatTask(task));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private void createFileAndParent() {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file/folder: " + e.getMessage());
        }
    }

    private Task parseTask(final String line) {
        // Example: T | 1 | read book
        // Implement parsing logic based on your Task/Deadline/Event/Todo classes
        assert line != null : "Line cannot be null";
        String[] parts = line.split(" \\| ");
        try {
            assert parts.length >= TASK_TYPE_INDEX + 1 : "Line must contain task type";
            switch (parts[TASK_TYPE_INDEX]) {
                case "T":
                    assert parts.length >= TASK_DESCRIPTION_INDEX + 1 : "Todo must have description";
                    return new Todo(parts[TASK_DESCRIPTION_INDEX], parts[TASK_STATUS_INDEX].equals("1"));
                case "D":
                    assert parts.length >= TASK_EXTRA_INFO_INDEX + 1 : "Deadline must have due date";
                    return new Deadline(parts[TASK_DESCRIPTION_INDEX], parts[TASK_STATUS_INDEX].equals("1"), parts[TASK_EXTRA_INFO_INDEX]);
                case "E":
                    // For Event, expect: E | 1 | description | from | to
                    assert parts.length >= TASK_SECOND_EXTRA_INFO_INDEX + 1 : "Event must have start and end times";
                    return new Event(parts[TASK_DESCRIPTION_INDEX], parts[TASK_STATUS_INDEX].equals("1"), parts[TASK_EXTRA_INFO_INDEX], parts[TASK_SECOND_EXTRA_INFO_INDEX]);
                default:
                    return null;
            }
        } catch (Exception e) {
            // Handle corrupted line
            return null;
        }
    }

    private String formatTask(final Task task) {
        assert task != null : "Task cannot be null";
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.toString().substring(task.toString().indexOf(" ") + 1);
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.toString().substring(d.toString().indexOf(" ") + 1, d.toString().indexOf(" (by:"));
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.toString().substring(e.toString().indexOf(" ") + 1, e.toString().indexOf(" (from:"));
        } else {
            return "";
        }
    }
}
