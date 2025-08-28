package duke;
import java.util.ArrayList;

/**
 * Contains the task list and has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the given list of tasks.
     * @param tasks The list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     * @param index The index of the task to remove
     * @return The removed task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the task list.
     * @param index The index of the task to get
     * @return The task at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the given index is valid.
     * @param index The index to check
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark
     * @return The marked task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to unmark
     * @return The unmarked task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.setDone(false);
        return task;
    }

    /**
     * Gets the underlying ArrayList of tasks.
     * @return The ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
