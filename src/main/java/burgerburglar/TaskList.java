package burgerburglar;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {

    private final List<Task> tasks;

    /** Constructs an empty task list. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Constructs a task list with the given tasks. */
    public TaskList(List<Task> initialTasks) {
        assert initialTasks != null : "Input task list cannot be null";
        tasks = new ArrayList<>(initialTasks);
    }

    /** Adds a task to the list. */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task to task list";
        tasks.add(task);
        assert tasks.contains(task) : "Task should have been added successfully";
    }

    /**
     * Marks a task as done or undone.
     *
     * @param index  0-based index of task
     * @param isDone true = done, false = undone
     * @return the updated task
     */
    public Task markTask(int index, boolean isDone) {
        Task task = getTaskAt(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    /** Deletes a task from the list (1-based index). */
    public Task deleteTask(int index) {
        assert !tasks.isEmpty() : "Cannot delete from an empty task list";
        validateDeleteIndex(index);
        Task removed = tasks.remove(index - 1);
        assert removed != null : "Removed task should not be null";
        return removed;
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /** Returns a copy of the task list. */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /** Returns a formatted string representation of the task list. */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "ALL DONE. BURGER!\n";
        }
        StringBuilder sb = new StringBuilder("YOU SHOULD GET STARTED SOON:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    // ----------------- Helper Methods -----------------

    /** Retrieves a task safely at the given 0-based index. */
    private Task getTaskAt(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        Task task = tasks.get(index);
        assert task != null : "Task at index should not be null";
        return task;
    }

    /** Validates index for deleteTask (1-based). */
    private void validateDeleteIndex(int index) {
        if (index <= 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
