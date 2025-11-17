package pagrobot.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pagrobot.tasks.Deadline;
import pagrobot.tasks.Event;
import pagrobot.tasks.Task;

/**
 * Represents a list of tasks with helper methods for marking, unmarking, and
 * deleting tasks.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Creates a TaskList pre-filled with the given tasks.
     *
     * @param tasks the initial list of tasks; must not be null.
     * @throws AssertionError if tasks is null and assertions are enabled.
     */
    public TaskList(List<Task> tasks) {
        super(tasks);
        assert tasks != null : "Task list must not be null";
    }

    /**
     * Converts the TaskList into a string representation.
     *
     * Returns a numbered list of tasks followed by a line separator.
     *
     * @return the string representation of the TaskList.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            Task task = super.get(i);
            assert task != null : "Task at index " + i + " should never be null";
            sb.append(i + 1).append(". ").append(super.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task at the given index as done.
     *
     * Returns the marked task.
     *
     * @param idx the index of the task to mark.
     * @return the marked Task.
     */
    public Task mark(int idx) {
        return super.get(idx).mark();
    }

    /**
     * Unmarks the task at the given index.
     *
     * Returns the unmarked task.
     *
     * @param idx the index of the task to unmark.
     * @return the unmarked Task.
     */
    public Task unmark(int idx) {
        return super.get(idx).unmark();

    }

    /**
     * Deletes the task at the given index from the TaskList.
     *
     * Returns the deleted task.
     *
     * @param idx the index of the task to delete.
     * @return the deleted Task.
     */
    public Task delete(int idx) {
        return super.remove(idx);
    }

    /**
     * Finds if any task name contains taskName. Returns the task if any.
     *
     * @param taskName the name to search.
     * @return the task if any.
     */
    public TaskList findTask(String taskName) {
        assert taskName != null && !taskName.isBlank() : "Search query must not be null/blank";
        return new TaskList(super.stream().filter(t -> t.getName().contains(taskName)).collect(Collectors.toList()));
    }

    /**
     * Gets the relevant time of a task.
     *
     * @param t the task.
     * @return the deadline/event time, or null if none.
     */
    private static LocalDateTime timeOf(Task t) {
        if (t instanceof Deadline deadline) {
            return deadline.getDeadline();
        }
        if (t instanceof Event event) {
            return event.getFrom();
        }
        return null;
    }

    /**
     * Compares tasks by time if both have one; otherwise by name.
     *
     * @param a first task.
     * @param b second task.
     * @return comparison result.
     */
    private static int compareSmart(Task a, Task b) {
        LocalDateTime ta = timeOf(a);
        LocalDateTime tb = timeOf(b);
        if (ta != null && tb != null) {
            int c = ta.compareTo(tb);
            if (c != 0) {
                return c;
            }
        }
        // mixed types or equal times → compare by name
        return a.getName().compareToIgnoreCase(b.getName());
    }

    /**
     * Returns a new TaskList sorted inplace by time if available, else by name.
     *
     * @return sorted TaskList.
     */
    public TaskList sortSmart() {
        super.sort(TaskList::compareSmart);
        return this;
    }
}
