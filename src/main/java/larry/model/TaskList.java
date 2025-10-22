package larry.model;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory list of tasks with 1-based indexing for user-facing operations.
 */
public class TaskList {

    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /** Number of tasks currently in the list. */
    public int size() {
        return tasks.size();
    }

    /** Underlying list view used by UI and Storage. */
    public List<Task> asList() {
        return java.util.Collections.unmodifiableList(tasks);
    }

    /** Appends the given task to the end of the list. */
    public void add(Task t) {
        assert t != null : "task must not be null";
        tasks.add(t);
    }

    /** Removes and returns the task at the given 1-based index (callers validate first). */
    public Task delete(int oneBasedIndex) {
        assert oneBasedIndex >= 1 && oneBasedIndex <= tasks.size() : "index out of range";
        return tasks.remove(oneBasedIndex - 1);
    }

    /** Returns the task at the given 1-based index (callers validate first). */
    public Task get(int oneBasedIndex) {
        assert oneBasedIndex >= 1 && oneBasedIndex <= tasks.size() : "index out of range";
        return tasks.get(oneBasedIndex - 1);
    }

    /** Returns true if {@code oneBasedIndex} is within [1, size()]. */
    public boolean isValidIndex(int oneBasedIndex) {

        return oneBasedIndex >= 1 && oneBasedIndex <= tasks.size();
    }

    /** Returns tasks whose string form contains the keyword. */
    public java.util.List<Task> find(String keyword) {
        java.util.List<Task> matches = new java.util.ArrayList<>();
        for (Task t : tasks) {
            if (containsIgnoreCase(t.toString(), keyword)) {
                matches.add(t);
            }
        }
        return matches;
    }

    private static boolean containsIgnoreCase(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return false;
        }
        return haystack.toLowerCase().contains(needle.toLowerCase());
    }
}
