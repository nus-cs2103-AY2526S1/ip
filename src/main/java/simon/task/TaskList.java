package simon.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks. A <code>TaskList</code> object holds a collection of <code>Task</code> objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the inputted list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task to return.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for TaskList";
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index Index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for TaskList";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a numbered list of all tasks in the TaskList.
     *
     * @return String listing out all tasks.
     */
    public String getAll() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n"));
    }
}