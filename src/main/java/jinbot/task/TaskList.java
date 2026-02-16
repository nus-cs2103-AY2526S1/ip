package jinbot.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. Provides operations to add, remove, retrieve,
 * and display tasks in a numbered format.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        Task task = tasks.get(index);
        assert index < getSize() : "index should be less than size of task list";
        return task;
    }

    /**
     * Removes the task at the specified index and returns it.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a numbered list of tasks as a string.
     * If the list is empty, returns "No tasks yet".
     *
     * @return The formatted string representation of all tasks in the list.
     */
    public String toNumberedListItems() {
        if (tasks.isEmpty()) {
            return "No tasks yet";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "task should not be null";
            sb.append(i + 1).append(". ").append(task);
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
