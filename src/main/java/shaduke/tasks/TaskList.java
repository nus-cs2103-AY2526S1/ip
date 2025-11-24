package shaduke.tasks;

import java.util.ArrayList;

/**
 * Representation of a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs the TaskList with the current list of tasks.
     *
     * @param tasks the current list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new TaskList if there are no current tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task to the task list.
     *
     * @param index the index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a chosen task.
     *
     * @param index the index of the chosen task.
     * @return the chosen task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the current number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}