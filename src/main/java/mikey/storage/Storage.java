package mikey.storage;

import mikey.task.Deadline;
import mikey.task.Event;
import mikey.task.Task;
import mikey.task.Todo;

import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Storage {
    private final Path savePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    /**
     * Initializes a Storage instance
     * @param filePath File path to be used as data file
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";

        this.savePath = Paths.get(filePath);
        System.out.println("Using data file at: " + savePath.toAbsolutePath());
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
    }

    /**
     * Loads the data from savePath
     *
     * @return a list of tasks that are stored in savePath
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (Files.notExists(savePath)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                Task task = parseTaskFromLine(line.trim());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Parses the task in each line
     * @param line Line to be read
     * @return Parsed task
     */
    private Task parseTaskFromLine(String line) {
        if (line.isEmpty()) {
            return null;
        }

        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean done = "1".equals(parts[1]);
        String desc = parts[2];

        Task task = createTaskByType(type, parts, desc);
        if (task != null && done) {
            task.markDone();
        }
        return task;
    }

    /**
     * Creates a task according to its type
     * @param type Type of task
     * @param parts Arguments of the command
     * @param desc Description of task
     * @return
     */
    private Task createTaskByType(String type, String[] parts, String desc) {
        try {
            switch (type) {
            case "T":
                Task todo = new Todo(desc);
                if (parts.length > 3) {
                    handleTag(todo, parts[3]);
                }
                return todo;
            case "D":
                LocalDateTime deadline = LocalDateTime.parse(parts[3], FORMATTER);
                Task deadlineTask = new Deadline(desc, deadline);
                if (parts.length > 4) {
                    handleTag(deadlineTask, parts[4]);
                }
                return deadlineTask;
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                Task event = new Event(desc, from, to);
                if (parts.length > 5) {
                    handleTag(event, parts[5]);
                }
                return event;
            default:
                return null;
            }
        } catch (Exception e) {
            return null; // Skip malformed tasks
        }
    }

    private void handleTag(Task t, String label) {
        t.markTagged();
        t.setTag(label);
    }

    /**
     * Saves tasks into the savePath file
     *
     * @param tasks List of tasks to be saved
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "Task list must not be null";

        try {
            if (Files.notExists(savePath.getParent())) {
                Files.createDirectories(savePath.getParent());
            }
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toSaveString());
            }
            Files.write(savePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return;
        }
    }
}
