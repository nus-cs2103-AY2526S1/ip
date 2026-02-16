package jooh.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jooh.task.*;

/**
 * Handles persistence of tasks by reading from and writing to a text file.
 * Provides encoding and decoding logic to translate between in-memory tasks
 * and their serialized string representation on disk.
 */
public class Storage {
    private final Path file = Paths.get("data", "Jooh.txt");

    /**
     * Ensures that the data directory and storage file exist.
     * Creates them if they do not already exist.
     *
     * @throws IOException If an I/O error occurs while creating files or directories.
     */
    private void checkFiles() throws IOException {
        Path parent = file.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
    }

    /**
     * Loads all tasks from the storage file into memory.
     *
     * @return A list of tasks reconstructed from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        checkFiles();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Task> tasks = new ArrayList<>();
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) {
                continue;
            }
            tasks.add(decode(line));
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the storage file,
     * overwriting any existing content.
     *
     * @param tasks The list of tasks to be persisted.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        checkFiles();
        List<String> out = new ArrayList<>();
        for (Task task : tasks) {
            out.add(encode(task));
        }
        Files.write(file, out, StandardCharsets.UTF_8);
    }

    /**
     * Encodes a single task into its serialized string form
     * suitable for saving to the storage file.
     *
     * @param task The task to encode.
     * @return A string representation of the task.
     */
    private static String encode(Task task) {
        String done = task.getIsDone() ? "1" : "0";
        if (task instanceof Todo) {
            return String.join(" | ", "T", done, task.getDesc());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.join(" | ", "D", done, deadline.getDesc(), deadline.getDeadline());
        } else if (task instanceof Fixed) {
            Fixed fixed = (Fixed) task;
            return String.join(" | ", "F", done, fixed.getDesc(), fixed.getDuration());
        } else {
            Event event = (Event) task;
            return String.join(" | ", "E", done, event.getDesc(), event.getFrom(), event.getTo());
        }
    }

    /**
     * Decodes a serialized string back into a {@link Task}.
     *
     * @param task The string representation of a task.
     * @return A task reconstructed from the string.
     * @throws IllegalArgumentException If the input string is malformed or
     *                                  does not represent a valid task.
     */
    private static Task decode(String task) {
        String[] parsed = task.split("\\s*\\|\\s*");
        if (parsed.length < 3) {
            throw new IllegalArgumentException("Line's broken");
        }
        String type = parsed[0];
        Boolean done = "1".equals(parsed[1]);
        String desc = parsed[2];
        Task t;
        switch (type) {
            case "T":
                t = new Todo(desc, done);
                break;

            case "D":
                if (parsed.length < 4) {
                    throw new IllegalArgumentException("Line's broken");
                }
                t = new Deadline(desc, parsed[3], done);
                break;

            case "E":
                if (parsed.length < 5) {
                    throw new IllegalArgumentException("Line's broken");
                }
                t = new Event(desc, parsed[3], parsed[4], done);
                break;
            case "F":
                if (parsed.length < 4) {
                    throw new IllegalArgumentException("Line's broken");
                }
                t = new Fixed(desc, done, parsed[3]);
                break;

            default:
                throw new IllegalArgumentException("Unknown task type");
        }
        return t;
    }
}
