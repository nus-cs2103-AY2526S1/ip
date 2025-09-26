package winnie.tasklist;

import winnie.task.Task;
import java.util.ArrayList;

/**
 * Abstraction over a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with the given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     * @return The marked task, or null if the index is invalid.
     */
    public Task markTask(int index) {
        // This assertion will hold since we check the index before calling this method.
        // Checked using "find all references" in IntelliJ, as a static analysis tool.
        assert index >= 0 && index < tasks.size() : "Task index cannot be negative";

        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task, or null if the index is invalid.
     */
    public Task unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task, or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        return null;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Finds tasks in the list that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a TaskList containing only the active (non-snoozed) tasks.
     *
     * @return A TaskList containing active tasks.
     */
    public TaskList getActiveTasks() {
        TaskList activeTasks = new TaskList();
        for (Task task : tasks) {
            if (!task.isSnoozed()) {
                activeTasks.addTask(task);
            }
        }
        return activeTasks;
    }

    /**
     * Returns a TaskList containing only the snoozed tasks.
     *
     * @return A TaskList containing snoozed tasks.
     */
    public TaskList getSnoozedTasks() {
        TaskList snoozedTasks = new TaskList();
        for (Task task : tasks) {
            if (task.isSnoozed()) {
                snoozedTasks.addTask(task);
            }
        }
        return snoozedTasks;
    }

    /**
     * Returns a copy of the tasks in the task list. This should only be used
     * for testing purposes.
     */
    protected Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }
}
