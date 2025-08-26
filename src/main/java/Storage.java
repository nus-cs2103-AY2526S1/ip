import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading tasks to/from a file.
 */
public class Storage {
    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "byte.txt";
    private final Path filePath;

    /**
     * Creates a Storage instance with the default file path.
     */
    public Storage() {
        this.filePath = Paths.get(DATA_DIR, FILE_NAME);
    }

    /**
     * Loads tasks from the file.
     * Creates the directory and file if they don't exist.
     *
     * @return List of loaded tasks
     * @throws IOException if there's an error reading the file
     */
    public List<Task> loadTasks() throws IOException {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(filePath);
        List<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                try {
                    Task task = parseTaskFromString(line);
                    tasks.add(task);
                } catch (ByteException e) {
                    System.err.println("Invalid task line: " + line + " - " + e.getMessage());
                }
            }
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks List of tasks to save
     * @throws IOException if there's an error writing to the file
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        // Create directory if it doesn't exist
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }
        }
    }

    /**
     * Converts a task to its file representation.
     *
     * @param task Task to convert
     * @return String representation for file storage
     */
    private String taskToFileString(Task task) {
        Status status = task.isDone ? Status.DONE : Status.NOT_DONE;
        return status.getValue() + " | " + task;
    }

    /**
     * Parses a task from its file string representation.
     *
     * @param line String representation from file
     * @return Parsed Task object
     * @throws ByteException if the line format is invalid
     */
    private Task parseTaskFromString(String line) throws ByteException {
        String[] tokens = line.split(" \\| ");

        Status status = Status.fromString(tokens[0]);
        String taskString = tokens[1];

        Task task = switch (taskString.substring(1, 2)) {
            case "T" -> {
                String description = taskString.substring(taskString.indexOf("] ") + 2);
                yield new Todo(description);
            }
            case "D" -> {
                int byStart = taskString.indexOf("(by: ") + 5;
                int byEnd = taskString.indexOf(")");

                String by = taskString.substring(byStart, byEnd);
                String description = taskString.substring(taskString.indexOf("] ") + 2, taskString.indexOf(" (by:"));
                String convertedBy = convertDisplayToInput(by);
                yield new Deadline(description, convertedBy);
            }
            case "E" -> {
                int fromStart = taskString.indexOf("(from: ") + 7;
                int fromEnd = taskString.indexOf(" to:");
                int toStart = taskString.indexOf("to: ") + 4;
                int toEnd = taskString.indexOf(")");

                String from = taskString.substring(fromStart, fromEnd);
                String to = taskString.substring(toStart, toEnd);
                String description = taskString.substring(taskString.indexOf("] ") + 2, taskString.indexOf(" (from:"));
                String convertedFrom = convertDisplayToInput(from);
                String convertedTo = convertDisplayToInput(to);
                yield new Event(description, convertedFrom, convertedTo);
            }
            default -> throw new ByteException("Unknown task type in string: " + taskString);
        };

        if (status == Status.DONE) {
            task.mark();
        } else {
            task.unmark();
        }

        return task;
    }

    /**
     * Converts display format (MMM dd yyyy, h:mm a) back to input format (d/M/yyyy HHmm).
     * This is needed when loading tasks from file.
     *
     * @param displayFormat The date string in display format
     * @return The date string in input format
     * @throws ByteException if the conversion fails
     */
    private String convertDisplayToInput(String displayFormat) throws ByteException {
        try {
            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime dateTime = LocalDateTime.parse(displayFormat, displayFormatter);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return dateTime.format(inputFormatter);
        } catch (Exception e) {
            throw new ByteException("Failed to parse date from storage: " + displayFormat);
        }
    }
}
