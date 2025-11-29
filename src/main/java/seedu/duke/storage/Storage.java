package seedu.duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

/**
 * Handles persistence of tasks to and from disk.
 */
public class Storage {

    private final Path file;

    /**
     * Creates a {@code Storage} pointing to a file path.
     *
     * @param filePath Path to the tasks file.
     */
    public Storage(String filePath) {
        this.file = Paths.get(filePath);
    }

    /**
     * Loads tasks from disk. Creates parent directory/file if missing.
     *
     * @return List of tasks loaded from the file.
     * @throws DukeException If an I/O error occurs.
     */
    public List<Task> load() throws DukeException {
        try {
            ensureStorageReady();
            if (Files.notExists(file)) {
                Files.createFile(file);
                return new ArrayList<>();
            }
            final List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            return parseTasks(lines);
        } catch (IOException e) {
            throw new DukeException("Failed to load tasks from " + file + ".", e);
        }
    }

    /**
     * Saves the entire task list atomically to disk.
     *
     * @param tasks In-memory task list.
     * @throws DukeException If any I/O error occurs.
     */
    public void save(TaskList tasks) throws DukeException {
        final Path tmp = tempPath();
        try {
            writeTempFile(tmp, tasks);
            replaceWithTemp(tmp);
        } catch (IOException e) {
            throw new DukeException("Failed to persist tasks to " + file + ".", e);
        }
    }

    // ===== helpers (single-purpose, private) =====

    private void ensureStorageReady() throws IOException {
        final Path parent = file.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private List<Task> parseTasks(List<String> lines) {
        final List<Task> tasks = new ArrayList<>();
        for (String raw : lines) {
            final String line = raw.trim();
            if (line.isEmpty()) {
                continue;
            }
            try {
                tasks.add(parseLine(line));
            } catch (IllegalArgumentException ex) {
                // Not fatal: skip corrupted lines but do not keep the catch block empty.
                System.err.println("Skipping malformed line in " + file + ": " + line);
            }
        }
        return tasks;
    }

    private Path tempPath() {
        return file.resolveSibling(file.getFileName().toString() + ".tmp");
    }

    private void writeTempFile(Path tmp, TaskList tasks) throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(
                tmp, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < tasks.size(); i++) {
                w.write(tasks.get(i).toSaveString());
                w.newLine();
            }
        }
    }

    private void replaceWithTemp(Path tmp) throws IOException {
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }

    /**
     * Parses one serialized task line.
     * <p>Format: {@code TYPE|done|name|extras...}</p>
     *
     * @param line Serialized line.
     * @return The parsed {@link Task}.
     * @throws IllegalArgumentException If the line is malformed.
     */
    private Task parseLine(String line) {
        final String[] parts = line.split("\\|", -1);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Bad format: " + line);
        }

        final String type = parts[0].trim();
        final boolean done = "1".equals(parts[1].trim());
        final String name = parts[2].trim();

        final Task task;
        switch (type) {
        case "T":
            task = new ToDo(name);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Deadline missing /by.");
            }
            task = new Deadline(name, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Event missing /from or /to.");
            }
            task = new Event(name, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }

        task.setDone(done);
        return task;
    }
}
