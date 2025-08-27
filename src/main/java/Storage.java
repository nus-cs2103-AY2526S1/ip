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
    private TaskList taskList;

    /**
     * Creates a Storage instance with the default file path.
     */
    public Storage() {
        this.filePath = Paths.get(DATA_DIR, FILE_NAME);
        this.taskList = new TaskList();
    }

    /**
     * Loads tasks from the file.
     * Creates the directory and file if they don't exist.
     *
     * @return TaskList of loaded tasks
     * @throws IOException if there's an error reading the file
     */
    public TaskList load() throws IOException {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return new TaskList();
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

        this.taskList = new TaskList(tasks);
        return this.taskList;
    }

    /**
     * Gets the current task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Initializes the storage with a given TaskList.
     * This is used when loading from file fails, then start with an empty list.
     *
     * @param taskList The TaskList to initialize with
     */
    public void initializeWithTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }



    /**
     * Gets a task at the specified index.
     */
    public Task getTask(int index) throws ByteException {
        return taskList.get(index);
    }

    /**
     * Adds a task to the list and saves.
     */
    public void addTask(Task task) {
        taskList.add(task);
        saveTasks(taskList);
    }

    /**
     * Marks a task as done and saves.
     */
    public void markTask(int index) throws ByteException {
        taskList.mark(index);
        saveTasks(taskList);
    }

    /**
     * Marks a task as not done and saves.
     */
    public void unmarkTask(int index) throws ByteException {
        taskList.unmark(index);
        saveTasks(taskList);
    }

    /**
     * Deletes a task and saves.
     */
    public Task deleteTask(int index) throws ByteException {
        Task removed = taskList.delete(index);
        saveTasks(taskList);
        return removed;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks TaskList to save
     */
    public void saveTasks(TaskList tasks) {
        try {
            File dataDir = new File(DATA_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath.toFile());
            tasks.writeToFile(writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not save tasks to file: " + e.getMessage());
        }
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
