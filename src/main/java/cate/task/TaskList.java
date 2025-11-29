package cate.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a collection of {@link Task} objects.
 * A {@code TaskList} supports adding, deleting, marking, unmarking,
 * and printing tasks, while also providing access to the underlying list.
 */
public class TaskList {
    private static final String line = "    _______________________________________\n";
    private List<Task> list;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>
     * This initializes the internal list of tasks to an empty {@link ArrayList},
     * allowing tasks to be added later.
     * </p>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an existing list of tasks.
     *
     * @param list The list of tasks to initialize this {@code TaskList} with.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Returns a copy of the internal list of tasks.
     * Modifications to the returned list will not affect the {@code TaskList}.
     *
     * @return A new {@link ArrayList} containing all tasks in this list.
     */
    public List<Task> getList() {
        return new ArrayList<>(list);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public int getIndexOfTask(Task task) {
        return list.indexOf(task);
    }

    public int size() {
        return list.size();
    }

    /**
     * Adds a task to the list and prints a confirmation message,
     * including the number of tasks in the list after addition.
     *
     * @param task The {@link Task} to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    public void insertTask(int index, Task task) {
        list.add(index, task);
    }

    /**
     * Deletes a task at the given index (1-based) from the list
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to delete.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void deleteTask(int i) {
        Task t = list.get(i);
        list.remove(t);
    }

    /**
     * Marks the task at the given index (1-based) as done
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to mark as done.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markTask(int i) {
        Task t = list.get(i);
        t.markDone();
    }

    /**
     * Marks the task at the given index (1-based) as not done
     * and prints a confirmation message.
     *
     * @param i The 1-based index of the task to unmark.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmarkTask(int i) {
        Task t = list.get(i);
        t.markUndone();
    }

    /**
     * Searches the task list for tasks whose descriptions contain the given query
     * string and prints the matching tasks to the console.
     *
     * @param query the keyword to search for within task descriptions
     */
    public List<Task> findTasks(String query) {
        return list.stream()
                .filter(t -> t.descriptionContains(query))
                .collect(Collectors.toList());
    }
}
