package chathyt.storage;

import chathyt.task.Task;
import chathyt.task.TaskDecoder;
import chathyt.task.TaskEncoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles persistent storage of tasks to and from a file.
 */

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file does not exist, it will be created along with any
     * necessary parent directories. Corrupted lines that cannot be
     * decoded are skipped with a warning message.
     * </p>
     *
     * @return An {@link ArrayList} of {@link Task} objects loaded from the file.
     */

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return tasks;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        tasks.add(TaskDecoder.decode(line));
                    } catch (Exception e) {
                        System.err.println("Skipping corrupted line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * <p>
     * Each task is encoded into a string representation using
     * {@link TaskEncoder} before being written to the file.
     * </p>
     *
     * @param tasks The {@link ArrayList} of {@link Task} objects to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                bw.write(TaskEncoder.encode(task));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}