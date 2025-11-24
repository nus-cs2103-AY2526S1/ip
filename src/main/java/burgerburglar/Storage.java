package burgerburglar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the persistent storage of {@link TaskList} to a local file.
 * <p>
 * Supports loading tasks from a file, saving tasks to a file, and parsing
 * task lines into {@link Task} objects. Handles corrupted or missing files gracefully.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage manager for a specific file path.
     *
     * @param filePath the file path for saving and loading tasks
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath cannot be null";
        assert !filePath.isBlank() : "File path cannot be blank";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file or directories do not exist, they will be created and an empty TaskList returned.
     *
     * @return a TaskList loaded from the file
     * @throws BurgerException if an I/O error occurs while reading
     */
    public TaskList load() throws BurgerException {
        File file = new File(filePath);

        if (!file.exists()) {
            createFileWithDirectories(file);
            return new TaskList();
        }

        List<Task> tasks = readTasksFromFile(file);
        return new TaskList(tasks);
    }

    /**
     * Saves the given {@link TaskList} to the storage file.
     *
     * @param list the TaskList to save
     */
    public void save(TaskList list) {
        assert list != null : "TaskList to save cannot be null";

        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : list.getTasks()) {
                fw.write(task.serialize() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Could not save tasks.");
        }
    }

    /**
     * Creates the storage file along with missing parent directories.
     *
     * @param file the File object representing the storage file
     * @throws BurgerException if the file cannot be created
     */
    private void createFileWithDirectories(File file) throws BurgerException {
        try {
            file.getParentFile().mkdirs();
            boolean created = file.createNewFile();
            assert created || file.exists() : "File should exist after creation attempt";
        } catch (IOException e) {
            throw new BurgerException("BURGER ERROR: Failed to create storage file.");
        }
    }

    /**
     * Reads all task lines from a file and parses them into Task objects.
     *
     * @param file the file to read tasks from
     * @return a list of Task objects
     */
    private List<Task> readTasksFromFile(File file) {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseLineSafe(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("BURGER ERROR: Failed to read tasks from file.");
        }

        return tasks;
    }

    /**
     * Safely parses a line into a {@link Task} object.
     * <p>
     * Handles todos, deadlines, and events. Returns null if the line is invalid or corrupted.
     *
     * @param line the line to parse
     * @return the Task object, or null if invalid
     */
    private Task parseLineSafe(String line) {
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

        try {
            switch (type) {
            case "T":
                return new Todo(description, isDone);

            case "D":
                LocalDateTime deadline = parseDateSafe(parts, 3);
                return new Deadline(description, deadline, isDone);

            case "E":
                LocalDateTime from = parseDateSafe(parts, 3);
                LocalDateTime to = parseDateSafe(parts, 4);
                return new Event(description, from, to, isDone);

            default:
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("BURGER ERROR: Malformed line skipped → " + line);
            return null;
        }
    }

    /**
     * Safely parses a LocalDateTime from the given parts array.
     *
     * @param parts the array containing serialized task data
     * @param index the index where the date string is located
     * @return the parsed LocalDateTime, or null if invalid or missing
     */
    private LocalDateTime parseDateSafe(String[] parts, int index) {
        if (parts.length <= index || parts[index].isBlank()) {
            return null;
        }

        try {
            return LocalDateTime.parse(parts[index]);
        } catch (DateTimeParseException e) {
            System.out.println("BURGER ERROR: Invalid date in saved task, using unspecified.");
            return null;
        }
    }
}
