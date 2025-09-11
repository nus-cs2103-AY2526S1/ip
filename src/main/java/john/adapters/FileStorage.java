package john.adapters;

import john.model.Event;
import john.model.Task;
import john.model.TaskList;
import john.model.Todo;
import john.model.Deadline;

import john.ports.Storage;
import john.util.DateTimeParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * File-backed implementation of Storage that reads and writes tasks
 * to a plain text file using a pipe-delimited format.
 * <p>
 * File format (one task per line, fields are trimmed):
 * - Todo:     T | done(0|1) | title
 * - Deadline: D | done(0|1) | title | by (dd/MM/yyyy HH:mm:ss)
 * - Event:    E | done(0|1) | title | from (dd/MM/yyyy HH:mm:ss) | to (dd/MM/yyyy HH:mm:ss)
 * <p>
 * The load method will create the file if it does not exist and return an empty TaskList.
 * Corrupt or unknown lines are skipped with a console message in this implementation.
 * This class is not thread-safe.
 */
public class FileStorage implements Storage {
    /**
     * Absolute or relative path string to the save file location.
     */
    private final String filePath;

    /**
     * Constructs a FileStorage that reads from and writes to the given path.
     *
     * @param filePath path to the data file; may be relative or absolute
     */
    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Resolves a default path next to the running JAR or classes directory.
     * If the path cannot be resolved, falls back to $HOME/.duke/data.txt.
     * <p>
     * The anchor class used here is john.core.John. Replace it with your entry class if needed.
     *
     * @return a path pointing to data.txt beside the application artifact, or a home-directory fallback
     */
    public static Path resolveBesideJar() {
        String override = System.getProperty("john.data");
        if (override != null && !override.isBlank()) {
            return Paths.get(override);
        }
        try {
            Path loc = Paths.get(john.core.John.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
            if (Files.isRegularFile(loc) && loc.toString().endsWith(".jar")) {
                return loc.getParent().resolve("data.txt");
            }
        } catch (Exception ignore) {
            // fall through to defaults
        }
        Path workingDir = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
        return workingDir.resolve("data.txt");
    }

    /**
     * Loads tasks from the configured filePath.
     * If the file does not exist, attempts to create it and returns an empty TaskList.
     * <p>
     * Parsing rules:
     * - Lines are split by the '|' character.
     * - Whitespace around each field is trimmed.
     * - T creates a Todo, D creates a Deadline, E creates an Event.
     * - A done flag of "1" marks the task complete; anything else is treated as incomplete.
     * - Date-time fields are parsed using DateTimeParser.parseDateTime.
     * <p>
     * Error handling:
     * - IOExceptions during initial creation are logged to System.out and an empty TaskList is returned.
     * - IOExceptions during reading are wrapped in RuntimeException.
     *
     * @return a TaskList containing all successfully parsed tasks
     */
    @Override
    public TaskList load() {
        List<Task> tasks = new ArrayList<Task>();
        assert filePath != null && !filePath.isBlank() : "filePath required";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
                return new TaskList(tasks);
            } catch (IOException e) {
                System.out.println("Failed to create file at" + filePath);
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 0) {
                    continue;
                }

                String type = parts[0].trim().toUpperCase();

                switch (type) {
                    case "T":
                        if (parts.length >= 3) {
                            String title = parts[2].trim();
                            Task todo = new Todo(title);
                            if ("1".equals(parts[1].trim())) {
                                todo.markAsComplete();
                            }
                            tasks.add(todo);
                        }
                        break;

                    case "D":
                        if (parts.length >= 4) {
                            String title = parts[2].trim();
                            String deadline = parts[3].trim();
                            Task deadlineTask = new Deadline(title,
                                    DateTimeParser.parseDateTime(deadline));
                            if ("1".equals(parts[1].trim())) {
                                deadlineTask.markAsComplete();
                            }
                            tasks.add(deadlineTask);
                        }
                        break;

                    case "E":
                        if (parts.length >= 5) {
                            String title = parts[2].trim();
                            String from = parts[3].trim();
                            String to = parts[4].trim();
                            Task eventTask = new Event(title,
                                    DateTimeParser.parseDateTime(from), DateTimeParser.parseDateTime(to));
                            if ("1".equals(parts[1].trim())) {
                                eventTask.markAsComplete();
                            }
                            tasks.add(eventTask);
                        }
                        break;

                    default:
                        System.out.println("Unknown task type: " + parts[0]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new TaskList(tasks);
    }

    /**
     * Saves the given TaskList to the configured filePath.
     * Each task is written using its serialise representation, followed by the platform line separator.
     * <p>
     * Error handling:
     * - IOExceptions during writing are wrapped in RuntimeException.
     * <p>
     * Note:
     * - This method does not ensure that the parent directory exists. If needed, create it beforehand.
     *
     * @param tasks the TaskList to persist
     */
    @Override
    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.serialise() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}