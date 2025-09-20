package paul.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import paul.exception.PaulException;
import paul.task.Deadline;
import paul.task.Event;
import paul.task.Task;
import paul.task.TaskList;
import paul.task.ToDo;

/**
 * Handles reading from and writing to the storage file,
 * which keeps track of tasks.
 */
public class Storage {
    private static final String SEPARATOR = " \\| ";
    private final String filePath; // Path to the storage file

    /**
     * Creates a Storage instance with the given file path.
     * Creates the directory if it does not exist.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path should not be null or blank";
        this.filePath = filePath;

        // create directory if it does not exist
        createFile(filePath);
    }

    private static void createFile(String filePath) {
        File parentFile = new File(filePath).getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs()) {
                System.out.println("Error: Could not create folder.");
            }
        }
    }

    /**
     * Loads tasks from the file into a TaskList.
     *
     * @return Tasklist containing the tasks from the storage.
     * @throws PaulException if the file is not found.
     */
    public TaskList loadTasks() throws PaulException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                Task task = getTaskFromFile(sc.nextLine().trim());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new PaulException("File not found: " + e.getMessage());
        }

        return tasks;
    }

    private Task getTaskFromFile(String input) throws PaulException {
        String[] parts = input.split(SEPARATOR);
        assert parts.length >= 3 : "Corrupted storage line: " + input;
        String type = parts[0]; // T, D, E
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        //CHECKSTYLE.OFF: Indentation
        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> new Deadline(description, LocalDate.parse(parts[3]));
            case "E" -> new Event(
                    description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
            default -> throw new PaulException("Unknown task type in file: " + type);
        };
        //CHECKSTYLE.ON: Indentation

        if (isDone) {
            task.markTask();
        }
        return task;
    }

    /**
     * Saves the tasks from the TaskList into the storage file.
     *
     * @param tasks TaskList containing the tasks to save.
     */
    public void saveTasks(TaskList tasks) throws PaulException {
        assert tasks != null : "Tasks to save should not be null";
        StringBuilder tasksSaveString = getTasksSaveString(tasks);
        saveTasksToFile(tasksSaveString);
    }

    private StringBuilder getTasksSaveString(TaskList tasks) {
        StringBuilder tasksSaveString = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            tasksSaveString.append(tasks.get(i).toSaveString()).append(System.lineSeparator());
        }
        return tasksSaveString;
    }

    private void saveTasksToFile(StringBuilder taskSaveString) throws PaulException {
        try {
            File file = new File(filePath);

            // Write to file
            FileWriter fw = new FileWriter(file);
            fw.write(taskSaveString.toString());
            fw.close();
        } catch (IOException e) {
            throw new PaulException("Unable to save tasks: " + e.getMessage());
        }
    }
}
