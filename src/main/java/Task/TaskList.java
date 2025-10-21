package Task;

import JohnException.JohnException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Mutable collection of task items with convenience operations used by commands,
 * such as add/remove/mark/unmark, indexed access, searching, and safe viewing.
 */
public class TaskList {
    private final ArrayList<TaskItem> items = new ArrayList<>();
    private int size = 0;

    /**
     * Returns a read-only snapshot view of the tasks in insertion order.
     *
     * @return Unmodifiable list of tasks.
     */
    public List<TaskItem> view() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns integer size of tasklist.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Returns a task at a given 0-index.
     *
     * @param index Position of item to be retrieved.
     * @return Task at index position.
     */
    public TaskItem getItem(int index) {
        assert index >= 0 && index < size : "TaskList.get: index out of bounds " + index;
        return items.get(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param t Task to add.
     */
    public void add(TaskItem t) {
        assert t != null : "TaskList.add: cannot add null task";
        items.add(t);
        size = getSize();
    }

    /**
     * Removes the task at the given 0-based index.
     *
     * @param index Zero-based index of the task to remove.
     */
    public TaskItem remove(int index) {
        assert index >= 0 && index < size : "TaskList.remove: index out of bounds " + index;
        size = getSize();
        return items.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Zero-based index of the task to mark.
     */
    public void mark(int index) {
        TaskItem marked = items.get(index);
        marked.markDone();
    }

    /**
     * Unmarks the task at the given index as not done.
     *
     * @param index Zero-based index of the task to unmark.
     */
    public void unmark(int index) {
        TaskItem unmarked = items.get(index);
        unmarked.markUndone();
    }

    /**
     * Returns tasks whose names contain the given keyword (case-insensitive).
     *
     * @param keyword Substring to match.
     * @throws JohnException If keyword is null or blank or if no matching
     * keyword is found.
     */
    public TaskList find(String keyword) throws JohnException {
        if (keyword == null || keyword.isBlank()) {
            throw new JohnException("Keyword cannot be empty.");
        }
        String q = keyword.toLowerCase();

        TaskList temp = new TaskList();
        for (TaskItem t : items) {
            if (t.name.toLowerCase().contains(q)) {
                temp.items.add(t);
                temp.size = temp.items.size();
            }
        }

        if (temp.items.isEmpty()) {
            throw new JohnException("No matching tasks found for: " + keyword);
        }

        return temp;
    }

    /**
     * Comparator for sorting method sortByDate().
     */
    private static class DateComparator implements Comparator<TaskItem> {
        @Override
        public int compare(TaskItem a, TaskItem b) {
            int cmp = Comparator.comparing(
                    (TaskItem t) -> t.getDate().orElse(null),
                    Comparator.nullsLast(Comparator.naturalOrder())
            ).compare(a, b);

            if (cmp != 0) {
                return cmp;
            }

            // Tie-breaker: fall back to name
            return a.name.compareToIgnoreCase(b.name);
        }
    }

    /**
     * Sorts tasks by date, then by name.
     */
    public void sortByDate() {
        items.sort(new DateComparator());
    }
}