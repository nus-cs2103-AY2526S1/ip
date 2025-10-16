package lebron.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.ToDo;

/**
 * The Storage class is responsible for managing the storage of tasks in a file.
 * It handles loading tasks from the file, ensuring the file exists, and writing tasks to the file.
 */
public class Storage {
    private final Path filePath;
    private final File dataFile;

    /**
     *  Constructor to initialize the Storage with the specified file path.
     * If the file does not exist, it is created along with any necessary directories.
     */
    public Storage(String pathString) {
        assert pathString != null && !pathString.trim().isEmpty() : Constants.ERROR_EMPTY_FILEPATH;
        this.filePath = Paths.get(pathString);
        this.dataFile = filePath.toFile();

        ensureFileExists();
        assert Files.exists(filePath) : "Data file should exist after ensureFileExists()";
    }

    /**
     * Ensures that the data file exists; creates it and its parent directories if they don't exist.
     */
    public void ensureFileExists() {
        try {
            ensureDirectoryExists();
            createFileIfNotExists();
        } catch (IOException e) {
            System.out.println(Constants.IO_ERROR_CREATING_FILE + e.getMessage());
        }
    }

    /**
     * Ensures that the parent directory of the file path exists; creates it if it doesn't.
     *
     * @throws IOException if there is an error creating the directory.
     */
    private void ensureDirectoryExists() throws IOException {
        Path parentDirectory = filePath.getParent();
        if (parentDirectory != null && !Files.exists(parentDirectory)) {
            Files.createDirectories(parentDirectory);
        }
    }
    /**
     * Creates the data file if it does not already exist.
     *
     * @throws IOException If there is an error creating the file.
     */
    private void createFileIfNotExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Loads tasks from the data file into an ArrayList.
     * Each line in the data file is parsed into a Task object using the Parser class.
     *
     * @return An ArrayList of Task objects loaded from the data file.
     * @throws LeBronException If there is an error reading the data file.
     */
    public ArrayList<Task> load() throws LeBronException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(dataFile.toPath())) { // Read each line from the file
                Task task = Parser.parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new LeBronException(Constants.IO_ERROR_READING_FILE + e.getMessage());
        }

        return tasks;
    }

    /**
     * Appends a task to the data file in a specific format.
     *
     * @param task The Task object to be written to the file.
     * @throws LeBronException If there is an error writing to the file.
     */
    public void writeTaskToFile(Task task) throws LeBronException {
        try (FileWriter fw = new FileWriter(dataFile.getPath(), true)) {
            String taskLine = formatTaskForStorage(task);
            fw.write(taskLine);
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            throw new LeBronException(Constants.IO_ERROR_WRITING_FILE + e.getMessage());
        }
    }

    /**
     * Formats a Task object into a string suitable for storage in the data file.
     *
     * @param task The Task object to format.
     * @return A string representation of the task for storage.
     */
    private String formatTaskForStorage(Task task) {
        StringBuilder sb = new StringBuilder();

        // Add task type
        sb.append(getTaskTypeCode(task)).append(Constants.STORAGE_SEPARATOR_WRITE);

        // Add completion status
        sb.append(task.isDone() ? Constants.DONE_FLAG : Constants.NOT_DONE_FLAG)
                .append(Constants.STORAGE_SEPARATOR_WRITE);

        // Add description
        sb.append(task.getDescription());

        // Add type-specific data
        appendTypeSpecificData(sb, task);

        return sb.toString();
    }

    /**
     * Returns the task type code based on the instance type of the Task.
     *
     * @param task The Task object.
     * @return A string representing the task type code ("T", "D", or "E").
     * @throws IllegalArgumentException If the task type is unknown.
     */
    private String getTaskTypeCode(Task task) {
        if (task instanceof ToDo) {
            return Constants.TASK_TYPE_T;
        }
        if (task instanceof Deadline) {
            return Constants.TASK_TYPE_D;
        }
        if (task instanceof Event) {
            return Constants.TASK_TYPE_E;
        }
        throw new IllegalArgumentException("Unknown task type: " + task.getClass());
    }

    private void appendTypeSpecificData(StringBuilder sb, Task task) {
        if (task instanceof Deadline deadline) {
            sb.append(Constants.STORAGE_SEPARATOR_WRITE).append(deadline.getBy());
        } else if (task instanceof Event event) {
            sb.append(Constants.STORAGE_SEPARATOR_WRITE).append(event.getStart())
                    .append(Constants.STORAGE_SEPARATOR_WRITE)
                    .append(event.getEnd());
        }
    }
}
