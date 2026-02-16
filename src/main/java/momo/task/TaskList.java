package momo.task;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index the index of the task to delete.
     * @return the task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task to mark as done.
     */
    public void markTask(int index) {
        getTask(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the index of the task to unmark.
     */
    public void unmarkTask(int index) {
        getTask(index).unmarkFromDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Converts all tasks in the list to a string format suitable for saving to disk.
     *
     * @return a string representing all tasks, separated by line breaks.
     */
    public String convertToSaveFormat() {
        return String.join(System.lineSeparator(),
                tasks.stream().map(Task::convertToSaveFormat).toList());
    }

    /**
     * Returns a stream of tasks in this list.
     *
     * @return a Stream of Task objects.
     */
    public Stream<Task> stream() {
        return tasks.stream();
    }

    /**
     * Clears all tasks from the list.
     */
    public void clear() {
        tasks.clear();
    }
}
