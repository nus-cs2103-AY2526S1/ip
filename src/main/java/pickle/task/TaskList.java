package pickle.task;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a list of Tasks.
 * Provides method to add, retrieve, delete, mark, unmark and list size.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets all the tasks.
     *
     * @return the list of tasks as an ArrayList.
     */
    public ArrayList<Task> all() {
        return tasks;
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes a task from the list.
     *
     * @param value the value of which task to remove.
     * @return the task that was deleted.
     */
    public Task delete(int value) {
        return tasks.remove(value - 1);
    }

    /**
     * Add the given tasks to the list at the back.
     *
     * @param t the task that is to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves the task at the specific index of the task list.
     *
     * @param value Index of task.
     * @return The intended task.
     */
    public Task get(int value) {
        return tasks.get(value - 1);
    }

    /**
     * Expose a read-only stream of tasks.
     */
    public Stream<Task> stream() {
        return tasks.stream();
    }
}
