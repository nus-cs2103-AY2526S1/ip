package merlie.tasklist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.ui.Ui;

/**
 * Represents a list of tasks. Provides methods to add, delete, mark, and check
 * tasks in the list.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list contains no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index Index of the task to mark as done.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    public void markDone(int index) {
        this.tasks.get(index).markDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index Index of the task to mark as not done.
     */
    public void markUndone(int index) {
        this.tasks.get(index).markUndone();
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
     * Deletes the task at the specified index from the list.
     *
     * @param index Index of the task to delete.
     * @return The removed task.
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns a stream of tasks in the list.
     *
     * @return Stream of tasks.
     */
    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Checks if a task is already in the list.
     * If a duplicate is found, reports an error.
     * If an update is successful (existent task with updated fields),
     * saves the list and shows update output.
     *
     * @param newTask The task to check.
     * @param ui User interface object for displaying messages.
     * @param listFile ListFile object to save the list if updated.
     * @return true if the task is a duplicate or successfully updated, false otherwise.
     * @throws MerlieException If saving the list fails.
     * @throws IndexOutOfBoundsException for delete
     */
    public boolean isTaskInList(Task newTask, Ui ui, ListFile listFile) throws MerlieException {
        for (Task task : this.tasks) {
            if (task.isDuplicate(newTask)) {
                ui.errorOutput(newTask.getDescription() + " is already in your list!");
                return true;
            }
            if (task.isUpdateSuccessful(newTask)) {
                listFile.save(this);
                ui.updateOutput(task);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given index is within the bounds of the task list.
     *
     * @param index The index to check.
     * @return true if the index is valid (0 ≤ index < size of the list), false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }
}
