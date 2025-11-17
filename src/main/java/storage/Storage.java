package storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * Handles file storage operations for the task list.
 * Manages creation, saving, and retrieval of task data from persistent storage.
 */
public class Storage {
    protected static Path path = Paths.get("data", "Bara.txt");

    /**
     * Initializes the storage system by creating the data directory and file if they don't exist.
     * Creates the necessary directory structure and empty data file.
     */
    public static void initialize() {
        File file = path.toFile();
        try {
            if (!file.exists()) {
                Path parentDir = path.getParent();
                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Saves all tasks from the task list to the storage file.
     * Overwrites any existing content in the file with the current task data.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public static void saveTasks(TaskList taskList) {
        StringBuilder data = new StringBuilder();
        for (Task t : taskList.getAllTasks()) {
            data.append(t.store());
        }
        try {
            Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the file path used for task storage.
     *
     * @return the Path object representing the storage file location
     */
    public static Path getFilePath() {
        return path;
    }
}
