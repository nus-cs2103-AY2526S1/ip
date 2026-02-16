package guibot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import guibot.exception.DataFileCorruptedException;

/**
 * Handles interactions with a data file.
 */
public class Storage {
    private Path filePath;

    /**
     * Creates a Storage object and the data file if it does not exist yet.
     *
     * @param filePath Path to data file.
     * @return Storage object with path set.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = Paths.get(filePath);

        // Create the data directory and file if it does not exist
        Files.createDirectories(this.filePath.getParent());
        if (!Files.exists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }

    /**
     * Loads tasks from data file into a TaskList.
     *
     * @throws DataFileCorruptedException If data file is corrupted.
     */
    public void loadTasks(TaskList tasks) throws IOException, DataFileCorruptedException {
        Scanner taskReader = new Scanner(filePath);
        while (taskReader.hasNextLine()) {
            tasks.add(Parser.getTaskFromString(taskReader.nextLine()));
        }
    }

    /**
     * Saves tasks from a TaskList into the data file.
     *
     * @param taskList TaskList to save tasks from.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        Files.writeString(filePath, taskList.toStorageString());
    }

    /**
     * Deletes the data file.
     */
    public void deleteFile() throws IOException {
        Files.delete(filePath);
    }
}
