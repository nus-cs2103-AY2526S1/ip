package dobby;

import dobby.exceptions.InvalidTaskException;
import dobby.task.Task;
import java.util.ArrayList;

/**
 * Represents a collection of tasks and provides operations to manipulate them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from an existing list of tasks.
     *
     * @param tasks Existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task by index.
     *
     * @param index Index of task (0-based).
     * @return The removed task.
     * @throws InvalidTaskException If index is invalid.
     */
    public Task deleteTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns a task at the specified index.
     *
     * @param index Index of task.
     * @return The task.
     * @throws InvalidTaskException If index is invalid.
     */
    public Task getTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks as an unmodifiable list.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


}
