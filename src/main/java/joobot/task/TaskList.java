package joobot.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks in the Joo application.
 * Provides methods to add, delete, and mark tasks as done/undone.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the given tasks.
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task using varargs
     * @param tasksToAdd list of tasks
     */
    public void add(Task... tasksToAdd) {
        for (Task t : tasksToAdd) {
            tasks.add(t);
        }
    }

    /**
     * Sorts a task list based on comparator
     * @param comparator
     */
    public void sort(Comparator<Task> comparator) {
        tasks.sort(comparator);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the given index.
     *
     * @param index the index of the task
     * @return the task at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index index of the task to remove
     * @return the removed Task
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for remove";
        return tasks.remove(index);
    }

    /**
     * Returns the tasks that have the keyword
     *
     * @return list of all similar tasks
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription()
                                           .toLowerCase()
                                           .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
