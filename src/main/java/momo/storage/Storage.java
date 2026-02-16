package momo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import momo.exception.MomoException;
import momo.parser.Parser;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles saving and loading tasks to and from the local file system.
 */
public class Storage {
    private static final String DIRECTORY = "data";
    private static final String FILE_NAME = "momo.txt";

    /**
     * Saves the current tasks to a file in the local file system.
     * Creates the directory if it does not exist.
     *
     * @param tasks the task list to save.
     */
    public void save(TaskList tasks) {
        Path directoryPath = Paths.get(DIRECTORY);
        Path filePath = directoryPath.resolve(FILE_NAME);
        try {
            Files.createDirectories(directoryPath); // does nothing if directory already exists
            Files.writeString(filePath, tasks.convertToSaveFormat());
        } catch (IOException e) {
            System.out.println("Error saving tasks in hard disk: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file in the local file system into the given task list.
     * If reading the file or parsing the saved data fails, the task list is cleared
     * to prevent partial or corrupted data.
     * @param tasks the task list to load tasks into.
     */
    public void load(TaskList tasks) {
        Path filePath = Paths.get(DIRECTORY).resolve(FILE_NAME);
        if (!Files.exists(filePath)) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = Parser.parseToTask(line);
                tasks.addTask(task);
            }
        } catch (IOException | MomoException e) {
            tasks.clear();
        }
    }
}
