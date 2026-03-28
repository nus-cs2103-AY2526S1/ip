package dume.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks, providing operations for adding, removing,
 * searching, and retrieving tasks by index.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Finds all tasks whose details contain the given query (case-insensitive).
     *
     * @param query the keyword to search for
     * @return a list of matching tasks; empty if none found
     */
    public List<Task> find(String query) {
        List<Task> out = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matches(query)) {
                out.add(task);
            }
        }
        return out;
    }

    /**
     * Creates a TaskList initialized with existing tasks.
     *
     * @param initial initial list of tasks
     */
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return list of tasks
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param id zero-based index
     * @return task at that index
     */
    public Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Adds a task.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index.
     *
     * @param id zero-based index
     * @return removed task
     */
    public Task remove(int id) {
        return tasks.remove(id);
    }

    /**
     * Sorts tasks chronologically (deadlines and events by date, todos last).
     * Deadlines are sorted by their due date, events by their start time,
     * and todos appear at the end in their current order.
     */
    public void sortChronologically() {
        tasks.sort((task1, task2) -> {
            // Get sort keys for comparison
            String key1 = getChronologicalSortKey(task1);
            String key2 = getChronologicalSortKey(task2);
            
            // Compare the keys
            return key1.compareTo(key2);
        });
    }

    /**
     * Sorts tasks alphabetically by description.
     */
    public void sortAlphabetically() {
        tasks.sort((task1, task2) -> 
            task1.details.compareToIgnoreCase(task2.details));
    }

    /**
     * Sorts tasks by completion status (incomplete tasks first, then completed).
     */
    public void sortByStatus() {
        tasks.sort((task1, task2) -> {
            if (task1.isDone() && !task2.isDone()) return 1;
            if (!task1.isDone() && task2.isDone()) return -1;
            return 0;
        });
    }

    /**
     * Gets a chronological sort key for a task.
     * Returns date/time for deadlines and events, "zzz" for todos (to put them last).
     */
    private String getChronologicalSortKey(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return ((Event) task).getFrom();
        } else {
            return "zzz"; // Put todos at the end
        }
    }
}
