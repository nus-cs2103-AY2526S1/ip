package alex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the user's list of tasks. Provides operations to add, remove,
 * mark, unmark, search, and generate string representations of tasks.
 */
public class TaskList {
    List<Task> taskList;

    /**
     * Constructs a <code>TaskList</code> with an existing list of tasks.
     *
     * @param taskList Pre-existing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty <code>TaskList</code>.
     */
    public TaskList() {
        this.taskList = null;
    }

    /**
     * Marks the task at the specified position as done.
     *
     * @param index Position of task in the list (1-based index).
     * @return Confirmation message including the task marked as done.
     */
    public String mark(int index) {
        taskList.get(index - 1).markTask();
        return "Nice! I've marked this task as done:\n" + taskList.get(index - 1) + "\n";
    }

    /**
     * Marks the task at the specified position as not done.
     *
     * @param index Position of task in the list (1-based index).
     * @return Confirmation message including the task marked as not done.
     */
    public String unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkTask();
        return "Ok, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Position of task to be removed in the list (0-based index).
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Finds all tasks containing the specified keyword.
     *
     * @param find Keyword to search for in tasks.
     * @return A string containing all matching tasks in numbered order.
     */
    public String findMatch(String find) {
        String ans = "";
        int i = 1;
        for (Task task : taskList) {
            if (task.toString().contains(find)) {
                ans = ans + i + "." + task + "\n";
                i++;
            }
        }
        return ans;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Generates a string representation of the task list for display.
     *
     * @return Formatted string of all tasks in the list.
     */
    public String generateTaskList() {
        int i = 1;
        String ans = "Here are the tasks in your list:\n";
        for (Task t : taskList) {
            ans = ans + i + "." + t + "\n";
            i++;
        }

        return ans;
    }

    /**
     * Generates a string representation of the task list for saving to disk.
     *
     * @return String containing all tasks in a format suitable for storage.
     */
    public String toSaveList() {
        StringBuilder list = new StringBuilder();
        for (Task t : taskList) {
            list.append(t.toFileString()).append(System.lineSeparator());
        }
        return String.valueOf(list);
    }
}
