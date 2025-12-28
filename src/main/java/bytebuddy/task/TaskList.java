package bytebuddy.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a collection of tasks in ByteBuddy.
 * Provides methods to add, remove, retrieve, and update tasks.
 * Implements {@link Iterable} so tasks can be traversed using enhanced for-loops.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve (0-based).
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove (0-based).
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A new {@link TaskList} containing matching tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList results = new TaskList();
        tasks.stream().filter(t -> t.toString().contains(keyword)).forEach(results::addTask);
        return results;
    }


    /**
     * Returns an iterator over the tasks in this list.
     *
     * @return An iterator for traversing tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
