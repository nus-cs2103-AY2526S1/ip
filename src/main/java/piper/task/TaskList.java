package piper.task;

import java.util.ArrayList;

/**
 * A mutable list of Task objects with operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks.
     *
     * @return list size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param index position in the list.
     * @return the task at index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index position in the list.
     * @return deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns all tasks whose description contains the given keyword.
     * The search is case-insensitive and matches if the keyword appears anywhere within the task description.
     * The order of tasks returned is the same as in the original task list.
     *
     * @param keyword keyword to search for.
     * @return a list of tasks whose description contains the keyword.
     */
    public TaskList find(String keyword) {
        assert keyword != null : "String keyword should be non-null";
        TaskList matches = new TaskList();
        String kw = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(kw)) {
                matches.addTask(task);
            }
        }
        return matches;
    }

}
