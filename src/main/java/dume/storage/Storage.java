package dume.storage;

import dume.task.Deadline;
import dume.task.Event;
import dume.task.Task;
import dume.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from and writing to the save file for tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage tied to a file path.
     *
     * @param filePath path to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the save file.
     * Creates the parent directory if it doesn't exist.
     *
     * @return list of tasks read from file, empty list if file doesn't exist
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parse(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the save file.
     *
     * @param tasks tasks to save
     */
    public void save(List<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private Task parse(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isTaskCompleted = parts[1].equals("1");
        String details = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(details);
                if (isTaskCompleted) {
                    todo.mark();
                }
                return todo;
            case "D":
                Deadline deadline = new Deadline(details, parts[3]);
                if (isTaskCompleted) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                String[] times = parts[3].split(" to ", 2);
                String start = times.length > 0 ? times[0] : "";
                String end = times.length > 1 ? times[1] : "";
                Event event = new Event(details, start, end);
                if (isTaskCompleted) {
                    event.mark();
                }
                return event;
            default:
                return null;
        }
    }
}
