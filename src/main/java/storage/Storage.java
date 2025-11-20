package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import tasklists.TaskList;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It provides methods to load tasks from a file into a TaskList and save the tasks
 * in a TaskList to a file. The file is specified by the filePath provided when the
 * Storage object is created.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file into the TaskList.
     * Skips malformed lines and prints warnings for errors.
     *
     * @param taskList The TaskList to populate with loaded tasks.
     */
    public void loadTasks(TaskList taskList) {
        try {
            Path path = Paths.get(filePath);
            ensureParentDirectories(path);

            if (!Files.exists(path)) {
                return; // nothing to load
            }

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    ParsedTask parsed = parseLine(line);
                    if (parsed == null) {
                        continue; // skip malformed line
                    }
                    Task task = createTaskFromParsed(parsed);
                    if (task == null) {
                        continue; // could not create task from parsed data
                    }
                    if (parsed.isDone) {
                        task.markAsDone();
                    }
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves tasks from TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to save.
     */
    public void saveTasks(TaskList taskList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                bw.write(formatTaskLine(task));
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // --- Helper methods to reduce nesting and clarify intent ---

    private void ensureParentDirectories(Path path) throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private ParsedTask parseLine(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        String extra = parts.length > 3 ? parts[3] : null;
        return new ParsedTask(type, isDone, description, extra);
    }

    private Task createTaskFromParsed(ParsedTask p) {
        switch (p.type) {
        case "T":
            return new ToDos(p.description);
        case "D":
            if (p.extra != null) {
                return new Deadline(p.description, p.extra);
            }
            return null;
        case "E":
            if (p.extra != null) {
                String[] timeParts = p.extra.split(" to ");
                if (timeParts.length == 2) {
                    return new Events(p.description, timeParts[0], timeParts[1]);
                }
            }
            return null;
        default:
            return null;
        }
    }

    private String formatTaskLine(Task task) {
        String taskStatus = task.isDone() ? "1" : "0";
        String taskDescription = task.getDescription();
        if (task instanceof ToDos) {
            return "T | " + taskStatus + " | " + taskDescription;
        } else if (task instanceof Deadline) {
            return "D | " + taskStatus + " | " + taskDescription + " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Events) {
            return "E | " + taskStatus + " | " + taskDescription + " | " + ((Events) task).getFrom() + " to "
                    + ((Events) task).getTo();
        }
        return ""; // unknown task type
    }

    /**
     * Small holder for parsed fields from a saved line. Keeps parsing logic
     * decoupled from task construction and reduces nesting in the main loader.
     */
    private static class ParsedTask {
        final String type;
        final boolean isDone;
        final String description;
        final String extra;

        ParsedTask(String type, boolean isDone, String description, String extra) {
            this.type = type;
            this.isDone = isDone;
            this.description = description;
            this.extra = extra;
        }
    }
}
