package tsundere.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import tsundere.TsundereException;

/**
 * A list of tasks utilizing the ArrayList implementation.
 *
 * @see java.util.ArrayList
 */
public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new task list with the given tasks.
     *
     * @param tasks An ArrayList of Tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Filters the task list for tasks which contains the specified name as a substring in the task's name.
     *
     * @return A new {@link TaskList} of the filtered tasks.
     */
    public TaskList find(String name) {
        ArrayList<Task> filteredList = new ArrayList<>(tasks.stream().filter(
                task -> task.getName().contains(name)).toList());
        return new TaskList(filteredList);
    }

    /**
     * Checks if the user input index is within the correct range.
     *
     * @param i The index of the task in the task list.
     */
    private void validateIndex(int i) throws TsundereOutOfBoundsException {
        if (i < 0 || i >= tasks.size()) {
            throw new TsundereOutOfBoundsException();
        }
    }

    /**
     * Marks the task at the given index.
     *
     * @param id The index of the task in the task list
     */
    public void mark(int id) throws TsundereException {
        validateIndex(id);
        tasks.get(id).markDone();
    }

    /**
     * Unmarks the task at the given index.
     *
     * @param id The index of the task in the task list
     * @throws TsundereException If the given index is invalid
     */
    public void unmark(int id) throws TsundereException {
        validateIndex(id);
        tasks.get(id).markUndone();
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Takes in any number of tasks and appends them to the task list.
     * @param tasks A variable number of tasks
     */
    public void addAll(Task... tasks) {
        Collections.addAll(this.tasks, tasks);
    }

    /**
     * Appends all the tasks in the given task list to the current task list.
     * @param tasks List of tasks to be added
     */
    public void addAll(TaskList tasks) {
        for (Task task : tasks) {
            this.tasks.add(task);
        }
    }

    /**
     * Retrieves the task from the task list at the given index and return it
     *
     * @param id The index of the task to be retrieved from the task list
     * @return The retrieved task
     * @throws TsundereOutOfBoundsException If the given index is invalid
     */
    public Task get(int id) throws TsundereOutOfBoundsException {
        validateIndex(id);
        return tasks.get(id);
    }

    /**
     * Removes the task from the task list at the given index and return it
     *
     * @param id The index of the task to be retrieved from the task list
     * @return The removed task
     * @throws TsundereOutOfBoundsException If the given index is invalid
     */
    public Task remove(int id) throws TsundereOutOfBoundsException {
        validateIndex(id);
        return tasks.remove(id);
    }

    /**
     * Undoes an {@link tsundere.command.AddTaskCommand} by deleting the task at the end of the task list.
     */
    public void undoAdd() {
        tasks.remove(tasks.size() - 1);
    }

    /**
     * Undoes an {@link tsundere.command.DeleteCommand} by adding the task back into its original index.
     * @param task Task to be added into the list
     * @param index Index that the task should in after the undo
     */
    public void undoDelete(Task task, int index) {
        List<Task> temp = new ArrayList<>(tasks.subList(0, index));
        temp.add(task);
        temp.addAll(tasks.subList(index, tasks.size()));
        tasks = temp;
    }

    /**
     * Undoes a {@link tsundere.command.MarkCommand} by unmarking the task at the index.
     * @param index Index of the task to be unmarked
     */
    public void undoMark(int index) {
        try {
            tasks.get(index).markUndone();
        } catch (TsundereAlreadyUnmarkedException e) {
            assert false : "undoMark error";
        }
    }

    /**
     * Undoes a {@link tsundere.command.UnmarkCommand} by marking the task at the index.
     * @param index Index of the task to be marked
     */
    public void undoUnmark(int index) {
        try {
            tasks.get(index).markDone();
        } catch (TsundereAlreadyMarkedException e) {
            assert false : "undoUnmark error";
        }
    }

    /**
     * Checks if the task list is empty.
     * @return true if task list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskList) {
            return ((TaskList) o).tasks.equals(tasks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }
}
