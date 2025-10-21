package geegar.storage;

import geegar.exception.GeegarException;
import geegar.task.Task;

import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to storage
 * The Storage class manages with the underlying file system to delegate either loading or saving
 * using the TaskReader and TaskWriter class
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path
     *
     * @param filePath the path of the file used for storing tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from storage
     *
     * @return a list of tasks retrieved from the storage
     * @throws GeegarException if file cannot be read or parsed
     */
    public ArrayList<Task> load() throws GeegarException {
        return TaskReader.loadTasks();
    }

    /**
     * Saves the given list of tasks to storage
     *
     * @param tasks the list of tasks to save
     * @throws GeegarException if the file cannot be written to
     */
    public void save(ArrayList<Task> tasks) throws GeegarException {
        TaskWriter.saveTasks(tasks);
    }

    /**
     * Getter to returnt he file path associated with this storage instance
     *
     * @return the file path used for storing tasks
     */
    public String getFilePath() {
        return filePath;
    }
}
