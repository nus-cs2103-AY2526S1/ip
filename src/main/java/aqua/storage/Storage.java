package aqua.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.Task.Priority;

/**
 * Handles loading and saving of Aqua data to a file.
 */
public class Storage {
    private final Path path;

    /**
     * Constructor for Storage
     *
     * @param path Path to the storage file
     */
    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Loads Aqua data from filepath.
     *
     * @return List of line of data stored
     * @throws StorageException If there is an error with storage
     */
    public List<String> load() throws StorageException {
        try {
            List<String> lines = new ArrayList<>();
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line.trim());
                }
            }

            return lines;
        } catch (IOException e) {
            throw new StorageException("Failed to load data from storage.");
        }

    }

    /**
     * Saves a task to the storage file.
     *
     * @param task the task to be saved
     * @throws StorageException if an I/O error occurs
     */
    public void add(Task task) throws StorageException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            writer.write(task.toStorageString());
            writer.newLine();
        } catch (IOException e) {
            throw new StorageException("Failed to add task to storage.");
        }
    }

    /**
     * Removes a task from the storage file.
     *
     * @param task the task to be removed
     * @throws StorageException if an I/O error occurs
     */
    public void remove(Task task) throws StorageException {
        try {
            Path tempPath = this.path.getParent().resolve("temp.txt");
            Files.createDirectories(tempPath.getParent());
            Files.deleteIfExists(tempPath);
            Files.createFile(tempPath);

            try (
                    BufferedReader reader = Files.newBufferedReader(path);
                    BufferedWriter writer = Files.newBufferedWriter(tempPath);
            ) {
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.equals(task.toStorageString())) {
                        continue;
                    }

                    writer.write(line.trim());
                    writer.newLine();
                }
            }

            Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to remove task from storage.");
        }
    }

    /**
     * Updates a task in the storage file using the provided updater function.
     *
     * @param task the task to be updated
     * @param updater a function that takes the current parts of the task line and returns the updated parts
     * @throws StorageException if an I/O error occurs
     */
    private void updateTaskInFile(Task task, UnaryOperator<String[]> updater) throws StorageException {
        try {
            Path tempPath = Files.createTempFile(path.getParent(), "temp", ".txt");
            try (
                    BufferedReader reader = Files.newBufferedReader(path);
                    BufferedWriter writer = Files.newBufferedWriter(tempPath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    for (int i = 0; i < parts.length; i++) {
                        parts[i] = parts[i].trim();
                    }
                    if (line.trim().equals(task.toStorageString())) {
                        parts = updater.apply(parts);
                        line = String.join(" | ", parts);
                    }
                    writer.write(line.trim());
                    writer.newLine();
                }
            }
            Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to update task in storage.");
        }
    }

    /**
     * Updates the done status of a task in the storage file.
     *
     * @param task the task to be updated
     * @param isDone the new done status of the task
     * @throws StorageException if an I/O error occurs
     */
    public void updateDoneStatus(Task task, boolean isDone) throws StorageException {
        updateTaskInFile(task, parts -> {
            parts[1] = isDone ? "1" : "0";
            return parts;
        });
    }

    /**
     * Updates the priority of a task in the storage file.
     *
     * @param task the task to be updated
     * @param priority the new priority of the task
     * @throws StorageException if an I/O error occurs
     */
    public void updateTaskPriority(Task task, Priority priority) throws StorageException {
        updateTaskInFile(task, parts -> {
            parts[2] = String.valueOf(priority.ordinal());
            return parts;
        });
    }
}
