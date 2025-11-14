package task;

import java.util.ArrayList;

import exception.BaymaxException;

/**
 * Represents a collection of {@link Task} objects in the Baymax application.
 * <p>
 * This class provides methods to add, remove, retrieve, and search tasks.
 * It also ensures that duplicate tasks are not added.
 */
public class TaskList {

    /**
     * The internal list of tasks
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     * <p>
     * Throws a {@link BaymaxException} if the task already exists in the list.
     *
     * @param t the task to add
     * @throws BaymaxException if the task already exists
     */
    public void add(Task t) throws BaymaxException {
        if (tasks.contains(t)) {
            throw new BaymaxException("This task already exists!");
        }
        tasks.add(t);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index without removing it.
     *
     * @param index the index of the task
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of all tasks.
     *
     * @return the list of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the specified keyword in their description.
     *
     * @param keyword the keyword to search for
     * @return a list of tasks containing the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                matches.add(t);
            }
        }
        return matches;
    }

}
