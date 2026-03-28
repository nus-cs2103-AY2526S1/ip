package chani;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chani.tasks.Task;
import chani.tasks.TaskRegistry;

/**
 * Handles loading and saving of {@link Task} objects to a file.
 */
public class Storage {
    private static final int IDENTIFIER = 0;
    private static final int MARKED = 1;
    private static final int ARGS_START = 2;
    private final Path fPath;
    /**
     * Creates a Storage instance for the specified file path.
     * @param fPath The file path for storing tasks.
     */
    public Storage(String fPath) throws StorageException {
        this.fPath = Paths.get(fPath);
        createFileIfMissing();

    }

    /**
     * Creates the file and parent directories if they do not exist.
     * @throws StorageException if failed to create a file
     */
    private void createFileIfMissing() throws StorageException {
        try {
            Files.createDirectories(fPath.getParent());
            if (Files.notExists(fPath)) {
                Files.createFile(fPath);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to initialize storage file: " + fPath);
        }
    }

    /**
     * Loads tasks from the storage file.
     * Atomic read from files, either reads all lines or fails to read any
     * @return A list of tasks read from the file, or empty if an error occurs.
     */
    public List<Task> load() throws StorageException {
        try {
            List<String> lines = Files.readAllLines(fPath);
            List<Task> taskList = new ArrayList<>();
            for (String line : lines) {
                Task task = deserializeTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new StorageException("Failed to load tasks from file.");
        }
    }

    /**
     * Converts a line of storage data into a Task object.
     *
     * @param storageLine The line from the storage file.
     * @return A Task object, or null if parsing fails.
     */
    private Task deserializeTask(String storageLine) {
        String[] attributes = parseIntoAttributes(storageLine);
        if (attributes.length < ARGS_START) {
            System.out.println("WARNING: Skipping malformed line: " + storageLine);
            return null;
        }
        String identifier = attributes[IDENTIFIER];
        String[] args = Arrays.copyOfRange(attributes, ARGS_START, attributes.length);

        Task task = TaskRegistry.createTask(identifier, args);

        boolean isMarkedDone = "1".equals(attributes[MARKED]);
        if (isMarkedDone) {
            task.markAsDone();
        }

        return task;
    }


    /**
     * Saves tasks to the storage file.
     * @param tasks The list of tasks to save.
     */
    public void save(List<Task> tasks) throws StorageException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            String[] arr = task.toAttributeList().toArray(new String[0]);
            lines.add(toStorageLine(arr));
        }

        try {
            Files.write(fPath, lines);
        } catch (IOException e) {
            throw new StorageException("Failed to save to path " + fPath);
        }

    }

    /**
     * Formats task data into a single line suitable for file storage.
     *
     * @param taskAttributes The array of task attributes to format.
     * @return A string representation of the task, with fields separated by " | ".
     */
    private String toStorageLine(String[] taskAttributes) {
        return String.join(" | ", taskAttributes);
    }

    /**
     * Parses a line from the storage file back into task data fields.
     *
     * @param line The line read from the file.
     * @return An array of strings representing the task attributes.
     */
    private String[] parseIntoAttributes(String line) {
        return line.split(" \\| ");
    }

}
