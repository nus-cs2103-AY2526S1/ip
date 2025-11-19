package Coffee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Maintains an ordered collection of {@link Task} objects and provides operations to
 * list, add, delete, mark/unmark, and search tasks. Index-based operations use one-based
 * indexing (i.e., the first task is index {@code 1}).
 */
public class TaskList {

    /** Backing store for tasks in insertion order. */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates an empty task list.
     */
    public TaskList() {
    }

    /**
     * Creates a task list backed by the given mutable {@link ArrayList}.
     *
     * @param taskList initial list used as the backing store.
     * @throws NullPointerException if {@code taskList} is {@code null}.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints all tasks to standard output in a user-friendly format.
     * Shows a header, a placeholder when the list is empty, and one-based indices.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println("Nothing =)");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /**
     * Adds the given task to the end of the list.
     *
     * @param task task to add.
     * @throws NullPointerException if {@code task} is {@code null}.
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println();
        assert !taskList.isEmpty() : "Task list should not be empty after adding a task";
    }

    /**
     * Deletes and returns the task at the given one-based index.
     *
     * @param i one-based index of the task to remove.
     * @return the removed task.
     * @throws IndexOutOfBoundsException if {@code i} is not in {@code [1, size()]}.
     */
    public Task deleteTask(int i) {
        int index = i - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + i);
        }
        assert !taskList.isEmpty() : "Task list should not be empty before deleting a task";
        Task removed = taskList.remove(index);
        assert taskList.size() >= 0 : "Task list size should not be negative after deletion";
        return removed;
    }

    /**
     * Marks the task at the given one-based index as done.
     *
     * @param i one-based index of the task to mark done.
     * @throws IndexOutOfBoundsException if {@code i} is not in {@code [1, size()]}.
     */
    public void markAsDone(int i) {
        int index = i - 1;
        taskList.get(index).markAsDone();
    }

    /**
     * Marks the task at the given one-based index as not done.
     *
     * @param i one-based index of the task to mark not done.
     * @throws IndexOutOfBoundsException if {@code i} is not in {@code [1, size()]}.
     */
    public void markAsNotDone(int i) {
        int index = i - 1;
        taskList.get(index).markAsNotDone();
    }

    /**
     * Returns an unmodifiable view of the tasks.
     * Mutating the returned list is not permitted and will throw {@link UnsupportedOperationException}.
     *
     * @return unmodifiable list view of tasks in insertion order.
     */
    public List<Task> view() {
        return Collections.unmodifiableList(taskList);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword substring to search for within task descriptions.
     * @return list of matching tasks in their original order; possibly empty.
     * @throws NullPointerException if {@code keyword} is {@code null}.
     */
    public List<Task> find(String keyword) {
        String needle = keyword.toLowerCase();
        List<Task> out = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.toLowerCase().contains(needle)) {
                out.add(t);
            }
        }
        return out;
    }

}
