package mon.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import mon.exception.MonException;
import mon.task.Deadline;
import mon.task.Event;
import mon.task.Task;
import mon.task.Todo;

/**
 * Handles loading and saving tasks to and from file storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     * 
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return the list of loaded tasks
     * @throws MonException if there's an error loading from file
     */
    public ArrayList<Task> loadTasks() throws MonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        createDirectoryIfNotExists(file);

        // Create file if it doesn't exist
        try {
            if (file.createNewFile()) {
                // File was created (didn't exist before), return empty list
                return loadedTasks;
            }
        } catch (IOException e) {
            throw new MonException("Error creating file " + file.getAbsolutePath() + ": " + e.getMessage());
        }

        return readTasksFromFile(file);
    }

    /**
     * Loads tasks that need to be reminded (due within 7 days).
     * 
     * @return the list of tasks that need reminders
     * @throws MonException if there's an error loading from file
     */
    public ArrayList<Task> loadReminderTasks() throws MonException {
        File file = new File(filePath);

        // Create directory if it doesn't exist
        createDirectoryIfNotExists(file);

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return new ArrayList<>();
        }

        return readTasksFromFile(file, true);
    }

    private ArrayList<Task> readTasksFromFile(File file) throws MonException {
        return readTasksFromFile(file, false);
    }

    /**
     * Reads tasks from the specified file.
     * 
     * @param file the file to read tasks from
     * @param isReminderFile indicates if the tasks needed to be reminded
     * @return the list of tasks read from the file
     * @throws MonException if there's an error reading the file
     */
    private ArrayList<Task> readTasksFromFile(File file, boolean isReminderFile) throws MonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    parseAndAddTask(loadedTasks, line, isReminderFile);
                }
            }
            return loadedTasks;
        } catch (IOException e) {
            throw new MonException("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and adds the resulting task to the list.
     * 
     * @param loadedTasks the list to add the parsed task to
     * @param line the line to parse
     */
    private void parseAndAddTask(ArrayList<Task> loadedTasks, String line, boolean isReminderFile) {
        try {
            Task task = convertStringToTask(line);
            if (task == null) {
                return;
            }

            if (isReminderFile) {
                // Check if task is due within 7 days
                if (task instanceof Deadline deadline) {
                    long daysUntilDeadline = deadline.getDeadline().toEpochDay() - LocalDate.now().toEpochDay();
                    if (daysUntilDeadline <= 7) {
                        loadedTasks.add(task);
                    }
                } else {
                    loadedTasks.add(task);
                }
                return;
            }

            loadedTasks.add(task);
        } catch (Exception e) {
            System.out.println("Warning: Unable to parse line: " + line + " - " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the storage file.
     * 
     * @param tasks the list of tasks to save
     * @throws MonException if there's an error writing to file
     */
    public void saveTasks(ArrayList<Task> tasks) throws MonException {
        assert tasks != null : "Tasks list cannot be null";
        
        File file = new File(filePath);

        // Create directory if it doesn't exist
        createDirectoryIfNotExists(file);

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                assert task != null : "Task in list cannot be null";
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new MonException("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Converts a string representation to a Task object.
     * 
     * @param taskString the string representation of a task
     * @return the Task object
     * @throws IllegalArgumentException if the task format is invalid
     */
    private Task convertStringToTask(String taskString) {
        assert taskString != null : "Task string cannot be null";
        assert !taskString.trim().isEmpty() : "Task string cannot be empty";
        
        String[] parts = taskString.split(" \\| ", 2);
        if (parts.length < 1) {
            throw new IllegalArgumentException("Invalid task format: " + taskString);
        }

        String taskType = parts[0];
        return switch (taskType) {
            case "T" -> Todo.toTodoTask(taskString);
            case "D" -> Deadline.toDeadlineTask(taskString);
            case "E" -> Event.toEventTask(taskString);
            default -> throw new IllegalArgumentException("Unknown task type: " + taskType);
        };
    }

    /**
     * Creates the parent directory if it doesn't exist.
     * 
     * @param file the file whose parent directory should be created
     */
    private void createDirectoryIfNotExists(File file) {
        File directory = file.getParentFile();
        boolean isDirectoryNotNull = directory != null;
        boolean doesDirectoryNotExist = directory != null && !directory.exists();
        boolean shouldCreateDirectory = isDirectoryNotNull && doesDirectoryNotExist;
        
        if (shouldCreateDirectory) {
            directory.mkdirs();
        }
    }
}
