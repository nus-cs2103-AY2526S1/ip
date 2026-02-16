package botchat.storage;

import botchat.task.Task;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles the loading and storing of the tasks to hard drive.
 */
public class Store {
    private final String filePath;

    /**
     * Constructs a store that manages the reading
     * and writing of information.
     * @param filePath path to the storage file.
     */
    public Store(String filePath) {
        assert filePath != null : "filePath cannot be null";
        this.filePath = filePath;
    }

    /**
     * Load tasks from the storage file.
     * <p>
     * Creates the file and its parent directory if it is missing.
     * @return a list of tasks read from the file.
     */
    public ArrayList<Task> loadTasks() {
        Path path = Path.of(filePath);
        ensureFileReady(path);
        return readTasks(path);
    }

    /**
     * Saves list of tasks into the storage file.
     * @param tasks the list of tasks to be stored.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "tasks cannot be null";
        assert this.filePath != null : "filePath cannot be null";

        Path path = Path.of(this.filePath);
        ensureFileWritable(path);
        writeTasks(tasks);
    }

    /**
     * Ensures that the file is ready to be written
     * <p>
     *     Creates parent dirctories if they do not exist and creates
     *     the file to be written if it is missing
     * </p>
     *
     * @param path the path to the file
     */
    private void ensureFileWritable(Path path) {
        assert path != null : "path cannot be null";

        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write to task ", e);
        }
    }

    /**
     * Writes the given list of tasks to the file
     * @param tasks the list of tasks
     */
    private void writeTasks(ArrayList<Task> tasks) {
        File file = new File(filePath);
        assert file != null : "file cannot be null";

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toStorage() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Ensure File is ready to be read from
     * Creates a file and directory respectively if they
     * are missing.
     * @param path the path to the file to be read from
     */
    public void ensureFileReady(Path path) {
        assert path != null : "path cannot be null";

        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to prepare file '" + filePath + "'", e);
        }
    }

    /**
     * Reads the task from the file to load into storage
     * @param path the path to the file
     * @return the list of tasks read from the file
     */
    public ArrayList<Task> readTasks(Path path) {
        assert path != null : "path cannot be null";

        ArrayList<Task> tasks = new ArrayList<>();
        assert tasks != null : "tasks cannot be null";

        Scanner scanner = null;
        try {
            scanner = new Scanner(path.toFile());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                tasks.add(Task.convFromStorage(line));
            }
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("Failed to open file '" + filePath + "'", e);
        } finally {
            if (scanner != null) scanner.close();
        }
        return tasks;
    }

}
