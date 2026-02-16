package bestie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Persists the user's tasks to disk and reconstructs them on start-up.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a storage component backed by {@code data/bestie.txt} in the
     * working directory.
     */
    public Storage() {
        // ./data/bestie.txt (relative + OS-independent)
        this(Paths.get("data", "bestie.txt"));
    }

    /**
     * Creates a storage component that reads and writes to the specified path.
     *
     * @param filePath location of the data file
     */
    public Storage(Path filePath) {
        assert filePath != null : "Storage file path must not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from disk.
     *
     * @return a mutable list of tasks from the save file, or an empty list if
     *         the file does not exist
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks; // first run
        }

        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        int lineNo = 0;
        for (String raw : lines) {
            lineNo++;
            String line = raw.trim();
            if (line.isEmpty()) continue;

            try {
                tasks.add(parseLine(line));
            } catch (BestieException ex) {
                // Corrupted line: skip but don't crash; log to stderr so UI output stays clean.
                System.err.println("Skipping corrupted line " + lineNo + ": " + ex.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Saves the supplied tasks to disk, overwriting any existing data file.
     *
     * @param tasks tasks to persist
     * @throws IOException if writing fails
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks to save must not be null";
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toDataString());
        }
        Files.write(
                filePath,
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        );
    }

    /**
     * Parses a single line from the data file.
     *
     * <p>The syntax matches the format produced by {@link Task#toDataString()}.
     * Example lines:</p>
     * <ul>
     *     <li>{@code T | 1 | read book}</li>
     *     <li>{@code D | 0 | return book | Sunday}</li>
     *     <li>{@code E | 0 | project meeting | Mon 2pm | 4pm}</li>
     * </ul>
     */
    private Task parseLine(String line) throws BestieException {
        assert line != null : "Line to parse must not be null";
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BestieException("Too few fields: " + line);
        }
        String type = parts[0].trim();
        String doneFlag = parts[1].trim();

        boolean isDone;
        if ("1".equals(doneFlag)) {
            isDone = true;
        } else if ("0".equals(doneFlag)) {
            isDone = false;
        } else {
            throw new BestieException("Invalid done flag (must be 0/1): " + doneFlag);
        }

        Task t;
        int requiredParts;
        switch (type) {
        case "T":
            if (parts.length < 3) {
                throw new BestieException("Todo missing description");
            }
            t = new Todo(parts[2]);
            requiredParts = 3;
            break;
        case "D":
            if (parts.length < 4) {
                throw new BestieException("Deadline missing /by");
            }
            t = new Deadline(parts[2], parts[3]);
            requiredParts = 4;
            break;
        case "E":
            if (parts.length < 5) {
                throw new BestieException("Event missing /from or /to");
            }
            t = new Event(parts[2], parts[3], parts[4]);
            requiredParts = 5;
            break;
        default:
            throw new BestieException("Unknown task type: " + type);
        }

        if (isDone) {
            t.markAsDone();
        }

        if (parts.length > requiredParts) {
            applyTags(t, parts[requiredParts]);
        }
        return t;
    }

    private void applyTags(Task task, String serializedTags) {
        if (serializedTags == null || serializedTags.isBlank()) {
            return;
        }
        ArrayList<String> rawTags = new ArrayList<>();
        for (String token : serializedTags.split(",")) {
            if (!token.isBlank()) {
                rawTags.add(token);
            }
        }
        task.addTags(rawTags);
    }
}