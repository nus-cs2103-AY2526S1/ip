package cate.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cate.task.Deadline;
import cate.task.Event;
import cate.task.Task;
import cate.task.TaskList;
import cate.task.Todo;

/**
 * Handles persistent storage of tasks for the Cate application.
 */
public class Storage {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from the storage file.
     *
     * @return a list of tasks from the file, empty if file is missing or empty
     */
    public List<Task> load() {
        ensureFileExists();
        List<String> lines = readAllLines();
        return parseTasks(lines);
    }

    /**
     * Saves a single task to the file.
     *
     * @param task the task to save
     */
    public void saveTask(Task task) {
        ensureFileExists();
        appendLine(task.toFileString());
    }

    /**
     * Saves the entire task list, overwriting the existing file.
     *
     * @param tasks the TaskList to save
     */
    public void save(TaskList tasks) {
        ensureFileExists();
        List<String> lines = tasksToLines(tasks);
        writeAllLines(lines);
    }

    /** Reads all lines from the file. */
    private List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine().trim());
            }
        } catch (IOException ignored) {
            // ignore
        }
        return lines;
    }

    /** Parses lines from file into Task objects. */
    private List<Task> parseTasks(List<String> lines) {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /** Parses a single line into a Task object. */
    private Task parseTask(String line) {
        String[] parts = line.split(",");
        String type = parts[0];
        boolean done = parts[1].equals("1");
        String desc = parts[2];

        Task task = switch (type) {
        case "T" -> new Todo(desc);
        case "D" -> new Deadline(desc, LocalDateTime.parse(parts[3], formatter));
        case "E" -> new Event(desc, parts[3], parts[4]);
        default -> null;
        };

        if (task != null && done) {
            task.markDone();
        }
        return task;
    }

    /** Converts TaskList to list of strings for file writing. */
    private List<String> tasksToLines(TaskList tasks) {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks.getList()) {
            lines.add(t.toFileString());
        }
        return lines;
    }

    /** Writes all lines to the file, overwriting existing content. */
    private void writeAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException ignored) {
            // ignore
        }
    }

    /** Appends a single line to the file. */
    private void appendLine(String line) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(line + System.lineSeparator());
        } catch (IOException ignored) {
            // ignore
        }
    }

    /** Ensures the file and parent directories exist. */
    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException ignored) {
            // ignore
        }
    }
}
