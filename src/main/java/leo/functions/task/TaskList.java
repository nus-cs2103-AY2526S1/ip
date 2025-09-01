package leo.functions.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import leo.FileHandler;

/**
 * The TaskList class provides utility methods to manage list management operations
 * such as:
 * - adding task to files
 * - getting the number of tasks in the file
 * - getting the task at a specified index in the list
 * - deleting a task in the list
 * - marking a task in the list as completed
 * - marking a task in the list as not completed
 */
public class TaskList {
    private FileHandler fileHandler;
    private ArrayList<Task> listItems;

    /**
     * Instantiates a TaskList.
     *
     * @param fileHandler FileHandler to facilitate file operations.
     * @throws FileNotFoundException If file is in the wrong format or cannot be accessed.
     * @throws ArrayIndexOutOfBoundsException If task details are in the incorrect format.
     */
    public TaskList(FileHandler fileHandler) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        this.fileHandler = fileHandler;
        this.listItems = fileHandler.retrieveTasksFromFile();
    }

    /**
     * Adds a task to the list and the file.
     *
     * @param task Task to be added to the list and the file.
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    public void addTask(Task task) throws IOException {
        fileHandler.appendToFile(task.appendToFile());
        listItems.add(task);
    }

    /**
     * Get the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public Integer getSize() {
        return this.listItems.size();
    }

    /**
     * Get the task at the specified index.
     *
     * @param index Index of the specified task.
     * @return The task at the specified index.
     */
    public Task getItemAtIndex(int index) {
        return this.listItems.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the specified task.
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    public void deleteItemAtIndex(int index) throws IOException {
        this.listItems.remove(index);
        fileHandler.deleteFromFile(this.listItems);
    }

    /**
     * Marks the task as completed.
     *
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    public void markTask() throws IOException {
        fileHandler.deleteFromFile(this.listItems);
    }

    /**
     * Marks the task as not completed.
     *
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    public void unmarkTask() throws IOException {
        fileHandler.deleteFromFile(this.listItems);
    }
}
