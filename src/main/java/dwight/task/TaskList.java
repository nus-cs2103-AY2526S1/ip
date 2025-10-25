package dwight.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a collection of {@link Task} objects. Provides methods to add, remove, update,
 * display, and serialize tasks.
 */
public class TaskList {

    /** Unique sets. */
    private Set<String> uniqueTaskKeys;

    /** The underlying list storing all tasks. */
    private List<Task> list;

    /** Creates an empty {@code TaskList}. */
    public TaskList() {
        this.uniqueTaskKeys = new HashSet<>();
        this.list = new ArrayList<>();
    }

    /**
     * Creates a new {@code TaskList} initialized with an existing list of tasks. Intended for
     * internal use, such as filtering.
     *
     * @param list The list of tasks to initialize the task list with.
     */
    private TaskList(List<Task> list) throws DuplicateTaskException {
        this();
        for (Task task : list) {
            this.add(task);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) throws DuplicateTaskException {
        String uniqueKey = task.getUniqueKey();
        if (this.uniqueTaskKeys.contains(uniqueKey)) {
            throw new DuplicateTaskException(task.toString());
        }
        this.uniqueTaskKeys.add(uniqueKey);
        this.list.add(task);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param at The 0-based index of the task.
     * @return The specified task.
     */
    public Task get(int at) {
        return this.list.get(at);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param at The 0-based index of the task to delete.
     * @return The removed task.
     */
    public Task delete(int at) {
        this.uniqueTaskKeys.remove(this.list.get(at).getUniqueKey());
        return this.list.remove(at);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param at The 0-based index of the task to mark.
     * @return The updated task after being marked.
     */
    public Task mark(int at) {
        return this.list.get(at).mark();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param at The 0-based index of the task to unmark.
     * @return The updated task after being unmarked.
     */
    public Task unmark(int at) {
        return this.list.get(at).unmark();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns a new {@code TaskList} containing only the tasks whose descriptions match the given
     * keyword or phrase.
     *
     * @param description The keyword or phrase to filter tasks by.
     * @return A new {@code TaskList} containing the matching tasks.
     */
    public TaskList filtered(String description) {
        try {
            return new TaskList(
                    this.list.stream()
                            .filter(task -> task.isMatching(description))
                            .collect(Collectors.toList()));
        } catch (DuplicateTaskException e) {
            // This should never happen since we're filtering from a valid TaskList
            throw new IllegalStateException("Unexpected duplicate found during filtering", e);
        }
    }

    /**
     * Returns a string representation of the task list, with each task numbered in order.
     *
     * @return The formatted string of all tasks.
     */
    @Override
    public String toString() {
        return IntStream.range(0, this.list.size())
                .mapToObj(i -> (i + 1) + ". " + this.list.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a serialized representation of the entire task list, suitable for saving to storage.
     * Each task is placed on a new line.
     *
     * @return The serialized string of the task list.
     */
    public String serialize() {
        return this.list.stream().map(Task::serialize).reduce("", (a, b) -> a + "\n" + b).trim();
    }
}
