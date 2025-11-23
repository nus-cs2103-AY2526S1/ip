package stewie.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import stewie.task.DeadlineTask;
import stewie.task.EventTask;
import stewie.task.Task;
import stewie.task.TaskList;
import stewie.task.ToDoTask;
import stewie.util.Helper;


/**
 * Handles saving and loading of tasks to and from a file.
 */
public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private final Path filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param taskList The task list to save.
     */
    public void saveTasks(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        try {
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : taskList.getTasks()) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("\t Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and returns them as a TaskList.
     *
     * @return TaskList containing all loaded tasks, or empty list if file doesn't exist or has errors.
     */
    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();

        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("\t Warning: Could not create data directory");
            return taskList;
        }

        if (!Files.exists(filePath)) {
            System.out.println("\t No existing data file found. Starting with empty task list.");
            return taskList;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            int lineNumber = 0;
            int corruptedLines = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                try {
                    Task task = parseTaskFromLine(line);
                    if (task != null) {
                        taskList.addTask(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\t Warning: Skipping corrupted line " + lineNumber + ": "
                            + line + " (" + e.getMessage() + ")");
                    corruptedLines++;
                }
            }

            System.out.println("\t Loaded " + taskList.size() + " tasks from data file.");

            if (corruptedLines > 0) {
                System.out.println("\t Skipped " + corruptedLines + " corrupted lines. File will be cleaned up.");
                saveTasks(taskList);
            }

        } catch (IOException e) {
            System.out.println("\t Error reading data file: " + e.getMessage());
        }

        return taskList;
    }

    private Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid format");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case TODO_TYPE:
            task = new ToDoTask(description);
            break;
        case DEADLINE_TYPE:
            if (parts.length < 4) {
                throw new IllegalArgumentException("Missing deadline");
            }
            task = new DeadlineTask(description, Helper.parseDateTime(parts[3]));
            break;
        case EVENT_TYPE:
            if (parts.length < 5) {
                throw new IllegalArgumentException("Missing event time");
            }
            task = new EventTask(description, Helper.parseDateTime(parts[3]), Helper.parseDateTime(parts[4]));
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        assert task.getIsDone() == isDone : "Task done status should match parsed value";

        return task;
    }
}
