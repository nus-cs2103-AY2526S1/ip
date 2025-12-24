package finixx.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import finixx.task.Task;
import finixx.task.TaskCollection;
import finixx.ui.Ui;

/**
 * Handles loading and saving tasks to a file for persistence.
 * Ensures the storage file and its parent directories exist.
 */
public class Storage {

    private Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Ensures the file and its parent directory exist.
     *
     * @param filePath Path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExist();

        assert this.filePath != null : "filePath should not be null";
        assert this.filePath.toFile().exists() : "Storage file should exist after ensureFileExist()";
    }

    private void ensureFileExist() {
        try {
            createParentDirectoryIfNeeded(filePath.toFile());
            createFileIfNeeded(filePath.toFile());
        } catch (IOException e) {
            Ui.printCreateFileError(e);
        }
    }

    private void createParentDirectoryIfNeeded(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    private void createFileIfNeeded(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A TaskCollection containing all loaded tasks
     * @throws IOException If an I/O error occurs reading from the file
     */
    public TaskCollection loadTasks() throws IOException {
        List<String> lines = readLinesFromFile();
        return parseTasks(lines);
    }

    private List<String> readLinesFromFile() throws IOException {
        return Files.readAllLines(filePath);
    }

    private TaskCollection parseTasks(List<String> lines) {
        TaskCollection taskList = new TaskCollection();
        for (String line : lines) {
            Task task = Task.fromSaveFormat(line);
            taskList.addTask(task);
        }
        return taskList;
    }

    /**
     * Saves the given tasks to the storage file.
     *
     * @param tasks TaskCollection to save
     */
    public void saveTasks(TaskCollection tasks) {
        try {
            Files.write(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            Ui.printErrorSavingTask(e);
        }
    }
}
