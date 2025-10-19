package reboot;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import reboot.parser.Parser;
import reboot.task.Deadline;
import reboot.task.Event;
import reboot.task.Task;
import reboot.task.Todo;
import reboot.task.recurring.RecurringDeadline;
import reboot.task.recurring.RecurringEvent;
import reboot.task.recurring.RecurringTodo;

/**
 * Represents a storage location that will deal with writing to
 * and reading from file.
 */
public class Storage {
    private final Path file;

    /**
     * Constructs a new storage with the specified file path.
     * @param filePath Location of file to store data of tasklist.
     */
    public Storage(String filePath) {
        this.file = Paths.get("output", filePath);
    }

    /**
     * Checks if the path directory and file exists.
     * If either does not exist, create it.
     */
    public void checkFile() {
        try {
            // Ensure parent directories exist
            if (Files.notExists(file.getParent())) {
                Files.createDirectories(file.getParent());
            }

            // Ensure file exists
            if (Files.notExists(file)) {
                Files.createFile(file);
            }

        } catch (IOException e) {
            throw new RebootException(e.getMessage());
        }
    }

    /**
     * Returns the tasklist from previous sessions from the storage location
     */
    public List<Task> load() throws IOException {
        checkFile();
        List<Task> taskList;
        try {
            List<String> lines = Files.readAllLines(file);
            taskList = generateTasklistFromStringList(lines);
        } catch (IOException e) {
            throw new RebootException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Rewrites the file to include all the tasks in the tasklist
     * @param taskList List of tasks to be written into the file
     */
    public void writeFile(ArrayList<Task> taskList) {
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (Task t : taskList) {
                writer.write(t.toFileString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RebootException(e.getMessage());
        }
    }

    /**
     * Appends a new task to the file
     * @param line String to be appended to the file
     */
    public void appendLine(String line) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND)) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RebootException(e.getMessage());
        }
    }

    private static Optional<Task> createTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean status = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return Optional.of(new Todo(description, status));
        case "D":
            try {
                return Optional.of(new Deadline(description, status, Parser.parseDateOnly(parts[3])));
            } catch (Exception e) {
                return Optional.of(new Deadline(description, status, Parser.parseDateAndTime(parts[3])));
            }
        case "E":
            try {
                return Optional.of(new Event(description, status,
                        Parser.parseDateOnly(parts[3]), Parser.parseDateOnly(parts[4])));
            } catch (Exception e) {
                return Optional.of(new Event(description, status,
                        Parser.parseDateAndTime(parts[3]), Parser.parseDateAndTime(parts[4])));
            }
        case "RT":
            return Optional.of(new RecurringTodo(description, status, parts[3]));
        case "RD":
            try {
                return Optional.of(new RecurringDeadline(description, status,
                        Parser.parseDateOnly(parts[3]), parts[4]));
            } catch (Exception e) {
                return Optional.of(new RecurringDeadline(description, status,
                        Parser.parseDateAndTime(parts[3]), parts[4]));
            }
        case "RE":
            try {
                return Optional.of(new RecurringEvent(description, status,
                        Parser.parseDateOnly(parts[3]), Parser.parseDateOnly(parts[4]), parts[5]));
            } catch (Exception e) {
                return Optional.of(new RecurringEvent(description, status,
                        Parser.parseDateAndTime(parts[3]), Parser.parseDateAndTime(parts[4]), parts[5]));
            }
        default:
            return Optional.empty();
        }
    }

    private static List<Task> generateTasklistFromStringList(
            List<String> lines) {
        return lines.stream()
                .filter(line -> line.startsWith("T") || line.startsWith("E")
                        || line.startsWith("D") || line.startsWith("RT")
                        || line.startsWith("RE") || line.startsWith("RD"))
                .map(Storage::createTask)
                .filter(Optional::isPresent)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}
