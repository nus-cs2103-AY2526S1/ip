package floydai.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * Provides methods to add, remove, retrieve, and update tasks.
 * Acts as a wrapper around an {@link ArrayList} to manage tasks more easily.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} initialized with the given tasks.
     * If the provided list is {@code null}, an empty list is created.
     *
     * @param tasks An {@link ArrayList} of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The {@link Task} to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task in the list.
     * @return The {@link Task} at the given index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to remove.
     * @return The removed {@link Task}.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return A {@link List} containing all tasks.
     */
    public List<Task> getAll() {
        return tasks;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to mark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index of the task to unmark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks whose descriptions contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }
}
