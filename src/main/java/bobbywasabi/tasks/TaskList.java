package bobbywasabi.tasks;

import java.util.ArrayList;

/**
 * Represents a list of Task objects with utility methods to manipulate and query tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param indx The index of the task to retrieve.
     * @return The Task object at the given index.
     */
    public Task get(int indx) {
        return this.tasks.get(indx);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param indx The index of the task to remove.
     */
    public void remove(int indx) {
        this.tasks.remove(indx);
    }

    /**
     * Finds all tasks that match a given keyword.
     * Matches are determined by the Task's find method.
     *
     * @param keyword The keyword to search for in tasks.
     * @return A formatted string containing all matching tasks.
     */
    public String findTasksThatMatchKeyword(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task cur = this.tasks.get(i);
            if (cur.find(keyword)) {
                tasks.add(cur);
            }
        }

        return this.convertTasksToString(tasks);
    }

    /**
     * Converts a single task to a formatted string with its index.
     *
     * @param index The 1-based index of the task.
     * @param task  The Task to convert.
     * @return A formatted string representing the task.
     */
    public String convertTaskToString(int index, Task task) {
        return String.format("%d. %s\n",
                index, task);
    }

    /**
     * Converts a list of tasks into a single formatted string.
     *
     * @param tasks The list of Task objects to convert.
     * @return A formatted string representing all tasks.
     */
    public String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder textList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task cur = tasks.get(i);
            textList.append(convertTaskToString(i + 1, cur));
        }
        return textList.toString();
    }

    /**
     * Returns a string representation of all tasks stored in the TaskList.
     *
     * @return A formatted string listing all tasks.
     */
    @Override
    public String toString() {
        return this.convertTasksToString(this.tasks);
    }
}
