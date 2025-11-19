package Coffee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles reading from and writing to the storage file for tasks.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath Path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "File path must not be null/blank";
        this.filePath = Paths.get(filePath);
        assert this.filePath != null : "filePath must not be null after Paths.get()";
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks loaded from the file. Returns an empty list if the file does not exist.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        if (!Files.exists(filePath)) return list;
        try {
            System.out.println("Looking for: " + filePath.toAbsolutePath());
            for (String line : Files.readAllLines(filePath)) {
                Task t = TaskLoader.fromFileLine(line);
                assert t != null : "TaskLoader returned null for line: " + line;
                list.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        assert list != null : "load() should never return null";
        return list;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks List of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "Tasks list passed to save() is null";
        try {
            Files.createDirectories(filePath.getParent());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                for (Task task : tasks) {
                    assert task != null : "Null task encountered in save()";
                    String line = task.toFileString();
                    assert line != null && !line.isBlank() : "Task produced invalid file string";
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }
}
