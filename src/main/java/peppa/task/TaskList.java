package peppa.task;

// import peppa.ui.Ui; (no longer needed)

import java.util.ArrayList;
import java.util.Collections;

/**
 * In-memory catalogue of {@link Task}s, offering add / list / mark / unmark / delete operations
 * while delegating all console output decorations to a {@link Ui} instance.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Wraps an existing task collection with a UI helper.
     *
     * @param tasks initial task data to manage
     * @param ui    CLI decorator responsible for printing divider lines
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.sortTasks();
    }

    /**
     * Parses a raw user command (<em>todo</em>, <em>deadline</em>, or <em>event</em>)
     * into a concrete {@link Task} and appends it to the list.
     *
     * @param task full command string typed by the user
     * @return {@code true} if parsing succeeded and the task was stored;
     *         {@code false} for malformed input
     */
    public String addTask(String task) {
        Task newTask;
        StringBuilder sb = new StringBuilder();
        if (task.contains("todo")) {
            newTask = new ToDo(task.substring(5, task.length()));
        } else if (task.contains("deadline")) {
            String str = task.substring(9);
            String[] arr = str.split("/by ");
            newTask = new Deadline(arr[0], arr[1]);
        } else if (task.contains("event")) {
            String str =  task.substring(6);
            int from = str.indexOf("/from");
            int to = str.indexOf("/to");
            String description = str.substring(0, from-1);
            String start = str.substring(from+6, to-1);
            String end = str.substring(to+4);
            newTask = new Event(description, start, end);
        } else {
            newTask = null;
        }
        if (newTask!=null) {
            tasks.add(newTask);
            sb.append("Got it. I've added this task:\n");
            sb.append(newTask).append("\n");
            sb.append("Now you have " + tasks.size() + " tasks in the list.\n");
            this.sortTasks();
        } else {
            sb.append("Invalid task format.\n");
        }
        return sb.toString();
    }

    /**
     * Prints every task with a 1-based index, then a divider line.
     */
    public String displayTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int num = i+1;
            sb.append(num).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the given task as done and confirms the action.
     *
     * @param num zero-based task index
     * @return {@code true} on success, {@code false} if the index is out of range
     */
    public String markTask(int idx) {
        StringBuilder sb = new StringBuilder();
        if (idx < tasks.size()) {
            tasks.get(idx).markAsDone();
            sb.append("Nice! I've marked this task as done:\n");
            sb.append(tasks.get(idx)).append("\n");
        } else {
            sb.append("Cannot mark task because task does not exist!\n");
        }
        return sb.toString();
    }

    /**
     * Reverses the done state of the given task and confirms the action.
     *
     * @param num zero-based task index
     * @return {@code true} on success, {@code false} if the index is out of range
     */
    public String unmarkTask(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < tasks.size()) {
            tasks.get(num).markAsUndone();
            sb.append("OK, I've marked this task as not done yet:\n");
            sb.append(tasks.get(num)).append("\n");
        } else {
            sb.append("Cannot unmark task because task does not exist!\n");
        }
        return sb.toString();
    }

    /**
     * Removes the indexed task from the list and prints a summary.
     *
     * @param num zero-based task index
     * @return {@code true} on success, {@code false} if the index is out of range
     */
    public String deleteTask(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < tasks.size()) {
            Task tbr = tasks.remove(num);
            sb.append("Noted. I've removed this task:\n");
            sb.append(tbr).append("\n");
            sb.append("Now you have " + tasks.size() + " in the list\n");
        } else {
            sb.append("Cannot delete task because task does not exist!\n");
        }
        return sb.toString();
    }

    /**
     * Replaces the current internal list with the supplied one.
     *
     * @param tasks new task collection
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Exposes the underlying mutable list for persistence.
     *
     * @return the current {@link ArrayList} of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public String findTask(String toFind) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.isMatch(toFind)) {
                sb.append(count).append(".").append(curr.toString()).append("\n");
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * Sorts the task list according to the task comparison rules:
     * 1. Tasks with deadlines/times come before those without
     * 2. Among tasks with deadlines/times, earlier dates come first
     * 3. Tasks without deadlines/times are sorted alphabetically
     */
    public void sortTasks() {
        Collections.sort(tasks);
    }

}
