package vicky.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import vicky.tasklist.Task;
import vicky.tasklist.TaskList;

/**
 * This class represents Storage
 * It provides methods to check if the directory and file exists
 *
 * @author Rachel Wong
 */
public class Storage {
    private final Path filePath;

    public Storage() {
        this(Paths.get("data", "Vicky.txt"));
    }

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Check if the parent directory exists, create empty file if missing.
     *
     * @throws IOException if grandparent directory is missing
     */
    public void init() throws IOException {
        Path parentPath = this.filePath.getParent();
        if (parentPath != null && Files.notExists(parentPath)) {
            Files.createDirectories(parentPath);
        }
        if (Files.notExists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }

    /**
     * Load tasks from hard disk.
     *
     * @returns ArrayList<Task></Task>
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (Files.notExists(this.filePath)) {
            return tasks;
        } else {
            try {
                List<String> lines = Files.readAllLines(this.filePath, StandardCharsets.UTF_8);
                for (String line: lines) {
                    if (line.isEmpty()) {
                        continue;
                    } else {
                        Task t = Task.fromStorageString(line);
                        tasks.add(t);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Couldn't fetch tasks: " + e.getMessage());
            }
            return tasks;
        }

    }

    /**
     * Save all tasks to disk.
     */
    public void save(TaskList tasks) throws IOException {

        assert tasks != null : "TaskList is Null";

        List<String> lines = new ArrayList<>(tasks.len());
        for (int i = 0; i < tasks.len(); i++) {
            Task t = tasks.getTask(i);
            lines.add(t.toStorageString());
        }
        Files.write(this.filePath, lines, StandardCharsets.UTF_8);
    }


}
