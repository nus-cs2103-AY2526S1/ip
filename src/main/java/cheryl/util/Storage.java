package cheryl.util;

import cheryl.task.Deadline;
import cheryl.task.Event;
import cheryl.task.Task;
import cheryl.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Handles reading from and writing to the file system for task persistence.
 */
public class Storage {

    private final String filePath;
    private static final String DELIMITER = " \\| ";
    private static final int IDX_TYPE = 0;
    private static final int IDX_ISDONE = 1;
    private static final int IDX_TITLE = 2;
    private static final int IDX_BY = 3;
    private static final int IDX_FROM = 3;
    private static final int IDX_TO = 4;

    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Creates a new Storage with the given file path.
     *
     * @param filePath Path to the file where tasks are saved
     */
    public Storage(String filePath) {
        this.filePath = Objects.requireNonNull(filePath, "filePath must not be null");
        assert !this.filePath.isBlank() : "filePath should not be blank";
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return List of tasks loaded from the file
     * @throws IOException If an I/O error occurs during loading
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        ensureFileExists(file);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(parseLine(line));
            }
        }
        return tasks;
    }

    private void ensureFileExists(File file) throws IOException {
        File folder = file.getParentFile();
        if (!folder.exists()) folder.mkdirs();
        if (!file.exists()) file.createNewFile();
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line The line from the file
     * @return The corresponding Task
     */
    private Task parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        String type = parts[IDX_TYPE];
        boolean isDone = parts[IDX_ISDONE].equals("1");
        String title = parts[IDX_TITLE];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(title);
                break;
            case "D":
                task = new Deadline(title, parseDate(parts[IDX_BY]));
                break;
            case "E":
                task = new Event(title, parseDate(parts[IDX_FROM]), parseDate(parts[IDX_TO]));
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
        if (isDone) task.mark();
        return task;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks List of tasks to save
     * @throws IOException If an I/O error occurs during saving
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                fw.write(taskToLine(t) + "\n");
            }
        }
    }

    /**
     * Converts a task into its storage representation.
     *
     * @param t The task to convert
     * @return The storage line string
     */
    private String taskToLine(Task t) {
        if (t instanceof Todo) {
            return String.format("T | %s | %s", t.isDone() ? "1" : "0", t.getTitle());
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return String.format("D | %s | %s | %s",
                    d.isDone() ? "1" : "0",
                    d.getTitle(),
                    d.getDueDate().format(SAVE_FORMAT));
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return String.format("E | %s | %s | %s | %s",
                    e.isDone() ? "1" : "0",
                    e.getTitle(),
                    e.getFrom().format(SAVE_FORMAT),
                    e.getTo().format(SAVE_FORMAT));
        }
        throw new IllegalArgumentException("Unknown task type: " + t.getClass());
    }

    /**
     * Parses a date string into a LocalDate using yyyy-MM-dd format.
     *
     * @param dateStr The date string
     * @return Parsed LocalDate
     */
    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, SAVE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date in storage file: " + dateStr);
        }
    }
}
