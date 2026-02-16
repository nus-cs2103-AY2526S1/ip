package jinbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jinbot.JinBotException;
import jinbot.task.Deadline;
import jinbot.task.Event;
import jinbot.task.Priority;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.task.Todo;

/**
 * Handles loading and saving tasks to disk using a simple line format.
 */
public class Storage {
    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "jinbot.txt";

    /**
     * Parses a single saved line into a Task.
     *
     * @param line A line from the save file.
     * @return The corresponding Task represented by the line.
     * @throws IllegalArgumentException If the line is corrupted or cannot be parsed.
     */
    private Task parseLineToTask(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Corrupted line: " + line);
        }

        String type = parts[0];
        boolean done = "1".equals(parts[1]);
        Task task;

        switch (type) {
        case "T":
            task = new Todo(parts[2]);
            task.setPriority(Priority.valueOf(parts[3]));
            break;

        case "D":
            if (parts.length != 5) {
                throw new IllegalArgumentException("Corrupted deadline: " + line);
            }

            try {
                LocalDate by = LocalDate.parse(parts[3]);
                task = new Deadline(parts[2], by);
                task.setPriority(Priority.valueOf(parts[4]));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Corrupted deadline date: " + parts[3]);
            }
            break;

        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Corrupted event: " + line);
            }
            String[] fromTo = parts[3].split("\\s*~\\s*");

            try {
                LocalDate from = LocalDate.parse(fromTo[0].trim());
                LocalDate to = LocalDate.parse(fromTo[1].trim());
                assert !to.isBefore(from) : "to should not be before from";
                task = new Event(parts[2], from, to);
                task.setPriority(Priority.valueOf(parts[4]));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Corrupted event dates: " + parts[3]);
            }
            break;

        default:
            throw new IllegalArgumentException("Bad type: " + type);
        }

        if (done) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads tasks from disk. If no save file exists, creates an empty one and returns
     * an empty list. Invalid lines in the file are skipped with an error message.
     *
     * @return A list of tasks loaded from disk.
     */
    public List<Task> loadTasks() {
        List<Task> loadedTasks = new ArrayList<>();

        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(DATA_DIR, FILE_NAME);
        try {
            if (file.createNewFile()) {
                return loadedTasks;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    try {
                        Task loadedTask = parseLineToTask(line);
                        assert loadedTask != null : "task should not be null";

                        int beforeSize = loadedTasks.size();
                        loadedTasks.add(loadedTask);
                        assert beforeSize + 1 == loadedTasks.size()
                            : "Size of loaded tasks should be increased by 1.";
                    } catch (IllegalArgumentException e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error! Failed to load tasks from file: " + file.getAbsolutePath());
        }
        return loadedTasks;
    }

    /**
     * Saves all tasks in the given task list to disk, overwriting the existing save file.
     *
     * @param tasks The task list whose tasks are to be saved.
     * @throws JinBotException if IOException is caught.
     */
    public void saveTasks(TaskList tasks) throws JinBotException {
        File file = new File(DATA_DIR, FILE_NAME);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                fileWriter.write(task.toSaveFormat());
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new JinBotException("Error! Failed to save tasks. Please try again.");
        }
    }
}
