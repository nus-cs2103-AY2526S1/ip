package winnie.taskmanager;

import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;

import java.io.IOException;

/**
 * Manages the task list and handles storage operations.
 */
public class TaskManager {
    private TaskList tasks;
    private Storage storage;

    /**
     * Initializes a new TaskManager with an empty task list and a storage object.
     */
    public TaskManager() {
        this.tasks = new TaskList();
        this.storage = new Storage("./data/winnie.txt");
        loadTasks();
    }

    /**
     * Saves the current task list to storage.
     */
    private void saveTasks() {
        try {
            storage.saveTasksToFile(tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the task list from storage.
     */
    private void loadTasks() {
        try {
            TaskList loadedTasks = storage.loadTasks();
            this.tasks = loadedTasks;
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Adds a task to the task list and saves the changes.
     * 
     * @param task The task to add.
     *
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null when adding to TaskManager";
        tasks.addTask(task);
        saveTasks();
    }

    /**
     * Returns the number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.getTaskCount();
    }

    /**
     * Marks a task as done.
     * 
     * @param index The index of the task to mark as done.
     * @return The marked task, or null if the index is invalid.
     */
    public Task markTask(int index) {
        if (index >= 0 && index < tasks.getTaskCount()) {
            tasks.getTask(index).markAsDone();
            saveTasks();
            return tasks.getTask(index);
        }
        return null;
    }

    /**
     * Marks a task as not done.
     * 
     * @param index The index of the task to unmark.
     * @return The unmarked task, or null if the index is invalid.
     */
    public Task unmarkTask(int index) {
        if (index >= 0 && index < tasks.getTaskCount()) {
            tasks.getTask(index).markAsNotDone();
            saveTasks();
            return tasks.getTask(index);
        }
        return null;
    }

    /**
     * Retrieves a task from the task list.
     * 
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.getTaskCount()) {
            return tasks.getTask(index);
        }
        return null;
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param index The index of the task to delete.
     * @return The deleted task, or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.getTaskCount()) {
            Task deletedTask = tasks.deleteTask(index);
            saveTasks();
            return deletedTask;
        }
        return null;
    }
}
