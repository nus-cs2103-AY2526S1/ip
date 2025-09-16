package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Handles loading and saving tasks to a persistent storage file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path of the file used to store tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return a list of tasks loaded from the file, or an empty list if file does not exist
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        ensureDataDirectory(file);
        ensureSaveFile(file);

        if (!file.exists()) {
            return tasks; // nothing to load yet
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("oh no!!! there is an error reading file..." + e.getMessage());
        }

        return tasks;
    }

    /**
     * Ensures the parent data directory exists.
     */
    private void ensureDataDirectory(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("oh no!!! i can't create data directory :c");
            }
        }
    }

    /**
     * Ensures the save file exists.
     */
    private void ensureSaveFile(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("oh no!!! i couldn't create save file.");
                }
            } catch (IOException e) {
                System.out.println("oh no!!! i couldn't create save file: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a single line from the save file into a Task.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                return buildTodo(description, isDone);
            case "D":
                return buildDeadline(description, parts, isDone, line);
            case "E":
                return buildEvent(description, parts, isDone, line);
            default:
                System.out.println("oh no!!! skipping corrupted line... " + line);
                return null;
            }
        } catch (Exception e) {
            System.out.println("oh no!!! failed to parse line: " + line);
            return null;
        }
    }

    private Task buildTodo(String description, boolean isDone) {
        Task todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    private Task buildDeadline(String description, String[] parts, boolean isDone, String line) {
        assert parts.length == 4 : "Deadline line should have 4 parts: " + line;
        LocalDateTime by = LocalDateTime.parse(parts[3]);
        Task deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private Task buildEvent(String description, String[] parts, boolean isDone, String line) {
        assert parts.length == 5 : "Event line should have 5 parts: " + line;
        LocalDateTime from = LocalDateTime.parse(parts[3]);
        LocalDateTime to = LocalDateTime.parse(parts[4]);
        Task event = new Event(description, from, to);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks list of tasks to be saved
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("oh no!!! i couldn't save the following task: " + e.getMessage());
        }
    }
}
