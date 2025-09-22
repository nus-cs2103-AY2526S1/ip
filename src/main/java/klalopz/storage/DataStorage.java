package klalopz.storage;

import klalopz.tasks.TaskList;
import klalopz.tasks.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handles loading and saving tasks to a file on disk.
 * The tasks are stored in "tasks.txt" inside a project-specific data directory.
 */
public class DataStorage {

    private final Path filePath;

    /**
     * Constructs a DataStorage instance with the given path.
     * If the path is null, a default path under the user's home directory
     * is used ("~/klalopz/data/tasks.txt").
     * Ensures that the directory exists.
     *
     * @param path Path to the directory where task data should be stored.
     */
    public DataStorage(Path path) {
        String home = System.getProperty("user.home");
        Path directoryPath;

        directoryPath = Objects.requireNonNullElseGet(path, () -> Paths.get(home, "klalopz", "data"));

        this.filePath = directoryPath.resolve("tasks.txt");

        try {
            Files.createDirectories(directoryPath);
            assert Files.exists(directoryPath) : "Directory should exist after creation";
        } catch (IOException e) {
            System.out.println("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     * Each line is deserialized into a Task object.
     *
     * @return List of tasks loaded from the file. Returns an empty list if the file is missing or unreadable.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.deserialize(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks from the given task list to the storage file.
     * Each task is serialized as a single line.
     *
     * @param tasks TaskList containing tasks to save.
     **/
    // Taken from https://www.javaguides.net/2025/02/top-10-best-practices-for-file-handling-in-java.html
    public void save(TaskList tasks) {
        assert tasks != null : "TaskList should not be null before saving";
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks.getAll()) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
