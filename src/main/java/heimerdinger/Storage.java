package heimerdinger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import heimerdinger.task.Deadline;
import heimerdinger.task.Event;
import heimerdinger.task.Task;
import heimerdinger.task.ToDo;

/**
 * Responsible for reading from and writing to a .txt file that contains a list of tasks
 */
public class Storage {
    private final Path path;

    /**
     * Creates a Storage object that contains information about the filePath to access, write, and read from
     *
     * @param filePath relative path of the file used to store information about tasks
     */
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Load tasks from the storage file
     *
     * @return an ArrayList of tasks obtained from storage file
     * @throws HeimerdingerException if file cannot be read
     */
    // AI assisted to show where wrong data entry appears
    public ArrayList<Task> load() throws HeimerdingerException {
        try {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
                return new ArrayList<>();
            }

            ArrayList<Task> tasks = new ArrayList<>();
            int lineNo = 0;
            for (String line : Files.readAllLines(path, java.nio.charset.StandardCharsets.UTF_8)) {
                lineNo++;
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                try {
                    tasks.add(read(trimmed));
                } catch (HeimerdingerException ex) {
                    throw new HeimerdingerException("Line " + lineNo + ": " + ex.getMessage());
                } catch (RuntimeException ex) {
                    throw new HeimerdingerException("Line " + lineNo + ": malformed entry.");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new HeimerdingerException("Could not read tasks file: " + path);
        }
    }


    /**
     * Saves a list of tasks to storage file
     *
     * @param tasks list of tasks to be stored
     * @throws HeimerdingerException if file at filePath cannot be written to
     */
    // AI assisted to avoid corruption if app crashes mid write
    public void save(List<Task> tasks) throws HeimerdingerException {
        try {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            // Prepare lines to write
            List<String> lines = new ArrayList<>(tasks.size());
            for (Task t : tasks) {
                lines.add(write(t));
            }

            // Write to a temp file first
            Path tmp = Files.createTempFile(
                    parent != null ? parent : path.toAbsolutePath().getParent(),
                    "tasks-", ".tmp");
            try (BufferedWriter w = Files.newBufferedWriter(tmp, java.nio.charset.StandardCharsets.UTF_8)) {
                for (String line : lines) {
                    w.write(line);
                    w.newLine();
                }
            }

            // Move into place (atomic if supported)
            try {
                Files.move(tmp, path,
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                // Fallback if filesystem doesn't support atomic moves
                Files.move(tmp, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new HeimerdingerException("Failed to save tasks to file: " + path);
        }
    }


    /**
     * Converts a line of text into something readable by the programme
     *
     * @param line raw string from the file
     * @return Task object that corresponds to the raw string
     */
    // AI assisted to validate field count per task
    private Task read(String line) throws HeimerdingerException {
        // allow spaces around the delimiter; keep empty fields if any
        String[] p = line.split("\\s*\\|\\s*", -1);
        if (p.length < 3) {
            throw new HeimerdingerException("Not enough fields: \"" + line + "\"");
        }

        String taskType = p[0];
        boolean isDone = "1".equals(p[1]);
        Task task;

        switch (taskType) {
        case "T" -> {
            // T | done | desc
            if (p.length < 3) {
                throw new HeimerdingerException("ToDo missing description.");
            }
            task = new ToDo(p[2]);
        }
        case "D" -> {
            // D | done | desc | due
            if (p.length < 4) {
                throw new HeimerdingerException("Deadline missing due date/time.");
            }
            task = new Deadline(p[2], p[3]);
        }
        case "E" -> {
            // E | done | desc | from | to
            if (p.length < 5) {
                throw new HeimerdingerException("Event missing from/to date/time.");
            }
            task = new Event(p[2], p[3], p[4]);
        }
        default -> throw new HeimerdingerException("Unknown task type \"" + taskType + "\".");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }


    /**
     * Converts a Task into a string representation
     *
     * @param task the task to convert into string format
     * @return string representation of the task
     */
    public String write(Task task) throws HeimerdingerException {
        String type = task.getIcon();
        String done = task.getStatusIcon().equals("X") ? "1" : "0";
        if (type.equals("T")) {
            ToDo todo = (ToDo) task;
            return type + " | " + done + " | " + todo.getDescription();
        } else if (type.equals("D")) {
            Deadline deadline = (Deadline) task;
            return type + " | " + done + " | " + deadline.getDescription() + " | " + deadline.encode();
        } else if (type.equals("E")) {
            Event event = (Event) task;
            return type + " | " + done + " | " + event.getDescription() + " | "
                    + event.fromDateEncode() + " | " + event.toDateEncode();
        } else {
            throw new HeimerdingerException("There seems to be an error in writing your task to my database.");
        }
    }
}
