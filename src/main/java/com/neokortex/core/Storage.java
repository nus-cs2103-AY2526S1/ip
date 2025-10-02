package com.neokortex.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.neokortex.task.ListLoadWrapper;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;

/**
 * Handles persistent storage operations for {@link Task}s by storing it in a file.
 *
 * <p>
 * The Storage class manages reading from and writing to a persistent file storage.
 * It handles file creation, directory management, and storing and loading data from a directory.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>File-based persistent storage</li>
 *     <li>Automatic directory and file creation</li>
 *     <li>Error handling for file operations</li>
 *     <li>Handling of Corrupted data</li>
 *     <li>Support for incremental loading with error tracking</li>
 *     <li>Ability to change storage directory</li>
 * </ul>
 *
 * <p><b>Error Handling Strategy:</b></p>
 * <ul>
 *     <li>Continues loading when individual tasks fail deserialization</li>
 *     <li>Returns failed lines for diagnostic purposes</li>
 *     <li>Returns empty list rather than throwing exceptions for I/O errors</li>
 *     <li>Provides boolean success/failure status for save operations</li>
 * </ul>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 *
 * @see ListLoadWrapper
 * @see Task#serialize()
 * @see Task#deserialize(String)
 */
public class Storage {
    private Path filePath;
    private boolean pathSet;

    /**
     * Constructs a new Storage instance with the default file path (root directory).
     */
    public Storage() {
        this.filePath = Path.of("");
    }

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath the path to the file used for task storage.
     *                 The parent directories will be created automatically if needed.
     *                 Cannot be null.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        this.pathSet = true;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
        this.pathSet = true;
    }

    public boolean isPathSet() {
        return this.pathSet;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    /**
     * Loads tasks from persistent storage into a TaskList.
     *
     * <p>
     * This method attempts to read the storage file line by line, deserializing
     * each line into a Task object. Lines that cannot be deserialized are collected
     * separately for error reporting. If the file doesn't exist, returns an empty list.
     * </p>
     *
     * <p><b>Loading Process:</b></p>
     * <ol>
     *   <li>Checks if storage file exists</li>
     *   <li>Reads file line by line</li>
     *   <li>Attempts to deserialize each line into a Task</li>
     *   <li>Collects successfully loaded tasks</li>
     *   <li>Records failed lines for error reporting</li>
     *   <li>Returns both results in a ListLoadWrapper</li>
     * </ol>
     *
     * @return a ListLoadWrapper containing successfully loaded tasks and failed lines
     * @see ListLoadWrapper
     * @see Task#deserialize(String)
     */
    public ListLoadWrapper loadListFromStorage() {
        TaskList list = new TaskList();
        List<String> failedLines = new ArrayList<>();

        if (!Files.exists(this.filePath)) {
            return new ListLoadWrapper(list, failedLines);
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {

                    Task task = Task.deserialize(line);
                    list.add(task);
                } catch (Exception e) {
                    failedLines.add(line);
                }
            }
        } catch (IOException e) {
            return new ListLoadWrapper(list, failedLines);
        }
        return new ListLoadWrapper(list, failedLines);
    }

    /**
     * Saves the current task list to persistent storage.
     * <p>
     * This method writes all tasks in the task list to the storage file,
     * creating the file and parent directories if they don't exist. Each
     * task is serialized to a string and written as a separate line.
     * </p>
     *
     * <p><b>Saving Process:</b></p>
     * <ol>
     *   <li>Creates parent directories if they don't exist</li>
     *   <li>Creates storage file if it doesn't exist</li>
     *   <li>Serializes each task to string format</li>
     *   <li>Writes each serialized task to a new line in the file</li>
     *   <li>Returns success/failure status</li>
     * </ol>
     *
     * @param taskList the TaskList containing tasks to be saved. Cannot be null.
     * @throws IOException in the event that the {@link TaskList} Cannot be stored.
     *
     * @see Task#serialize()
     */
    public void saveListToStorage(TaskList taskList) throws IOException {
        Files.createDirectories(filePath.getParent());

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
