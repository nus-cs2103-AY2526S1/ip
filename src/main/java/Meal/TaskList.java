package Meal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> items;

    /**
     * Initialises an empty task list
     */
    public TaskList() {
        this.items = new ArrayList<>();
        assert items != null : "Items list must be initialised";
    }

    /**
     * Initialises a task list with the given task
     * @param initial list of initial tasks to add
     */
    public TaskList(List<Task> initial) {
        this.items = new ArrayList<>();
        if (initial != null) this.items.addAll(initial);
        assert items != null : "Items list must be initialised";
    }

    /**
     * Returns number of tasks in the list
     * @return number of tasks in the list
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns the task at the index (zero based)
     * @param indexZeroBased the index
     * @return the task at that index
     */
    public Task get(int indexZeroBased) {
        assert indexZeroBased >= 0 : "Index must be more than zero";
        assert indexZeroBased < items.size() : "Index must be within bounds";
        return items.get(indexZeroBased);
    }

    /**
     * Returns all tasks in the list (read only)
     * @return all tasks in the list (read only)
     */
    public List<Task> all() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Adds a task to the list
     * @param t task to be added
     * @return the task
     */
    public Task add(Task t) {
        items.add(t);
        return t;
    }

    /**
     * Deletes a task from the list at the one based index
     * @param oneBasedIndex index
     * @return the task that was removed
     */
    public Task delete(int oneBasedIndex) {
        int i = toZeroBased(oneBasedIndex);
        return items.remove(i);
    }

    /**
     * Marks the task at the given index as done
     * @param oneBasedIndex index
     * @return confirmation message saying its marked
     */
    public String mark(int oneBasedIndex) {
        int i = toZeroBased(oneBasedIndex);
        return items.get(i).mark();
    }

    /**
     * Marks the task at the given index as not done
     * @param oneBasedIndex index
     * @return confirmation message saying unmarked
     */
    public String unmark(int oneBasedIndex) {
        int i = toZeroBased(oneBasedIndex);
        return items.get(i).unmark();
    }

    /**
     * Finds all tasks which contain the given keyword
     * @param keyword the keyword to search for
     * @return a list of all matching tasks
     */
    public java.util.List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        java.util.ArrayList<Task> out = new java.util.ArrayList<>();
        for (Task t : items) {
            if (t.getTaskName().toLowerCase().contains(k)) {
                out.add(t);
            }
        }
        return out;
    }

    /**
     * Converts one based index to zero based index
     * @param oneBased index to be converted
     * @return the corresponding zero base index
     */
    private int toZeroBased(int oneBased) {
        int i = oneBased - 1;
        if (i < 0 || i >= items.size()) throw new BadInputException("Number is out of range");
        return i;
    }
}
