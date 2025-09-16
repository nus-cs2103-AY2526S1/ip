package tinkerton.task;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks and provides methods to manipulate and query the list.
 */
public class TaskList {
    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i The index of the task.
     * @return The task at the given index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param i The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Returns a new TaskList containing tasks that match the given predicate.
     *
     * @param predicate The predicate to filter tasks.
     * @return A filtered TaskList.
     */
    public TaskList filter(Predicate<Task> predicate) {
        return new TaskList(this.tasks.stream().filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Checks if a task with the given name already exists in the list.
     *
     * @param name The name of the task to check.
     * @return true if a task with the same name exists, false otherwise.
     */
    public boolean containsTaskName(String name) {
        return tasks.stream().anyMatch(task -> task.name().equalsIgnoreCase(name));
    }
}
