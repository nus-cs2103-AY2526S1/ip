package peanutbutter.tasks;

import java.util.ArrayList;
import java.util.List;

import peanutbutter.exceptions.JuinException;

/**
 * List of tasks created by the user
 * Provides methods to add, delete, get, mark, and unmark tasks.
 */
public class TaskList {
    private ArrayList <Task> taskList;

    /**
     * Creates a TaskList with existing tasks
     *
     * @param read List of task saved by the system
     */
    public TaskList(List<Task> read) {
        assert read != null : "Initial task list cannot be empty";
        this.taskList = new ArrayList<>(read);
    }

    /**
     * Gets the list of tasks
     *
     * @return List of tasks
     */
    public List<Task> getTasks() {
        assert taskList != null : "Task list cannot be null";
        return this.taskList;
    }

    /**
     * Adds a task to the list
     *
     * @param task The task that is being added to the task list
     */
    public void addTask(Task task) throws JuinException {
        assert task != null : "Task cannot be null";
        taskList.add(task);
    }

    /**
     * Deletes a task from the list
     *
     * @param index The index of the task that is being deleted from the task list
     *
     * @return The task that was just deleted
     */
    public Task deleteTask(int index) throws JuinException {
        checkIndex(index);
        return taskList.remove(index);
    }

    /**
     * Marks a task as done in the list
     *
     * @param index The index of the task that is being marked as done in the task list
     */
    public void markTaskDone(int index) throws JuinException {
        checkIndex(index);
        taskList.get(index).markDone();
    }

    /**
     * Marks a task as not done in the list
     *
     * @param index The index of the task that is being marked as done in the task list
     */
    public void unmarkTaskDone(int index) throws JuinException {
        checkIndex(index);
        taskList.get(index).unmarkDone();
    }

    /**
     * Gets the size of the task list
     *
     * @return The size of the list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets a specific task based on the task's index
     *
     * @param index The index of the task that is being got
     *
     * @return The task that was just got
     */
    public Task get(int index) throws JuinException {
        checkIndex(index);
        return taskList.get(index);
    }

    /**
     * Checks the validity of the index
     *
     * @param index The index of the task that is being checked
     */
    private void checkIndex(int index) throws JuinException {
        if (index < 0 || index >= taskList.size()) {
            throw new JuinException("INVALID TASK");
        }
    }

}
