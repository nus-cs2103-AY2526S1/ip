package borat.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import borat.task.Task;
import borat.task.ToDo;
import borat.task.Deadline;
import borat.task.Event;

/**
 * Loads tasks to and from a plain-text file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a storage instance in the given file path.
     *
     * @param filePath path to the data file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads existing tasks from disk. Returns an empty list if the file is
     * missing or unreadable.
     *
     * @return list of tasks read from file
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the provided tasks to the backing file, creating parent
     * directories as needed.
     *
     * @param tasks tasks to persist
     */
    public void save(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            for (Task task : tasks) {
                lines.add(task.toFileString());
            }

            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file into a {@link Task}.
     * Lines follow the format produced by each task's {@code toFileString()}.
     *
     * @param line raw line from file
     * @return parsed Task or null if the line is malformed
     */
    private Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;

            switch (taskType) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        String deadline = parts[3];
                        task = new Deadline(description, deadline);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        String start = parts[3];
                        String end = parts[4];
                        task = new Event(description, start, end);
                    }
                    break;
            }

            if (task != null && isDone) {
                task.setDone(true);
            }

            return task;
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            return null;
        }
    }
}