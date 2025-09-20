package duke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Contains the task list and has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the given list of tasks.
     * @param tasks The list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to add
     */
    public void add(Task task) {
        assert task != null : "Task to add cannot be null";
        tasks.add(task);
        assert tasks.size() > 0 : "Task list should not be empty after adding a task";
    }

    /**
     * Removes a task from the task list.
     * @param index The index of the task to remove
     * @return The removed task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the task list.
     * @param index The index of the task to get
     * @return The task at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the given index is valid.
     * @param index The index to check
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark
     * @return The marked task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task markTask(int index) {
        assert isValidIndex(index) : "Index must be valid before marking task";
        Task task = tasks.get(index);
        task.setDone(true);
        assert task.getDone() : "Task should be marked as done after calling markTask";
        return task;
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to unmark
     * @return The unmarked task
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task unmarkTask(int index) {
        assert isValidIndex(index) : "Index must be valid before unmarking task";
        Task task = tasks.get(index);
        task.setDone(false);
        assert !task.getDone() : "Task should be marked as not done after calling unmarkTask";
        return task;
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     * @param keyword The keyword to search for
     * @return A new TaskList containing matching tasks
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Gets the underlying ArrayList of tasks.
     * @return The ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sorts the task list with deadlines in chronological order (latest to earliest).
     * Non-deadline tasks remain in their original positions relative to each other.
     */
    public void sortByDeadline() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // If both are deadlines, sort by date (latest to earliest)
                if (t1 instanceof Deadline && t2 instanceof Deadline) {
                    Deadline d1 = (Deadline) t1;
                    Deadline d2 = (Deadline) t2;
                    return d1.getBy().compareTo(d2.getBy()); // Latest to earliest
                // If only one is a deadline, deadline comes first
                } else if (t1 instanceof Deadline && !(t2 instanceof Deadline)) {
                    return -1;
                } else if (!(t1 instanceof Deadline) && t2 instanceof Deadline) {
                    return 1;
                } else {
                    // If neither are deadlines, maintain original order
                    return 0;
                }
            }
        });
    }
}
