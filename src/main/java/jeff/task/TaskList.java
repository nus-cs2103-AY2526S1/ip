package jeff.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks for the Jeff chatbot.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {

        this.tasks = new ArrayList<>();

    }

    /**
     * Creates a TaskList with existing tasks.
     *
     * @param existingTasks the existing list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> existingTasks) {

        this.tasks = existingTasks;

    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to add
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param i the index of the task to get
     * @return the task at the specified index
     * @throws AssertionError if the index is out of bounds
     */
    public Task get(int i) {
        assert i >= 0 && i < tasks.size() : "Task index out of bounds";
        return tasks.get(i);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param i the index of the task to remove
     * @return the removed task
     * @throws AssertionError if the index is out of bounds
     */
    public Task remove(int i) {
        assert i >= 0 && i < tasks.size() : "Task index out of bounds";
        return tasks.remove(i);
    }

    /**
     * Gets the current number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }
}
