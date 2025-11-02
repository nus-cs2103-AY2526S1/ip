package buddy.storage;

import buddy.model.Task;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds tasks to a text file and loads them back.
 */

public class Storage {

    private final Path dataDir;
    private final Path dataFile;

    public Storage(String filePath) {
        Path p = Paths.get(filePath);
        this.dataDir = p.getParent() == null ? Paths.get(".") : p.getParent();
        this.dataFile = p;
        createFolderIfMissing();
    }

    public Storage() {
        this("data/buddy.txt");
    }

    private void createFolderIfMissing() {
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            } else if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            System.out.println("Error creating data storage: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from text file. Non-existent files result in an empty list.
     */

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        assert dataFile != null : "Data file must be initialized";
        try {
            List<String> lines = Files.readAllLines(dataFile);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                tasks.add(Task.fromDataString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes all tasks to text file.
     */

    public void save(List<Task> taskList) {
        assert taskList != null : "Task list to save cannot be null";
        assert dataFile != null : "Data file must be initialized";
        try (BufferedWriter writer = Files.newBufferedWriter(dataFile)) {
            for (Task task : taskList) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
