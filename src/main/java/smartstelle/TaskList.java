package smartstelle;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the SmartStelle application.
 * <p>
 * This class manages an ArrayList of Task objects and provides
 * methods to add, display, mark, unmark, delete tasks, and
 * retrieve all tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int total = 0;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Initial tasks list must not be null";

        this.tasks = tasks;
        this.total = tasks.size();
    }

    /**
     * Adds a new task to the list.
     * <p>
     * Increments the total count and prints a message indicating the addition.
     *
     * @param task The task to add.
     */
    public String add(Task task) throws StelleException {
        assert task != null : "Task to add must not be null";

        if (this.tasks.contains(task)) {
            throw new StelleException("This task already exists in your list.");
        }

        this.total += 1;
        this.tasks.add(task);
        return "    added new task: " + task.getName() + ". Now you have " + total + " tasks in the list.";
    }

    /**
     * Displays all tasks in the list with numbering.
     */
    public String display() {
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            int num = i + 1;
            msg.append("    ").append(num).append(".").append(tasks.get(i).display());
        }

        return msg.toString();
    }

    /**
     * Marks a task as completed based on its position in the list.
     * <p>
     * 
     * @param num The 1-based index of the task to mark as completed.
     */
    public String mark(int num) {
        assert num > 0 && num <= tasks.size() : "Task number must be valid";

        this.tasks.get(num - 1).mark();
        return "Yay! I've marked this task as done:\n    " + this.tasks.get(num - 1).display();
    }

    /**
     * Marks a task as not completed based on its position in the list.
     * <p>
     * 
     * @param num The 1-based index of the task to unmark.
     */
    public String unmark(int num) {
        assert num > 0 && num <= tasks.size() : "Task number must be valid";

        this.tasks.get(num - 1).unmark();
        return "Okay, I've marked this task as not done:\n    " + this.tasks.get(num - 1).display();
    }

    /**
     * Deletes a task from the list based on its position.
     * <p>
     * Decrements the total count and prints a message indicating the deletion.
     *
     * @param num The 1-based index of the task to delete.
     * @throws StelleException If the task number is invalid.
     */
    public String delete(int num) throws StelleException {
        assert num > 0 : "Task number must be positive";

        if (num > this.tasks.size()) {
            throw new StelleException("That task doesn't even exist.");
        }

        Task task = this.tasks.get(num - 1);
        this.tasks.remove(num - 1);
        this.total -= 1;
        return "Okay, I've removed this task:\n    " + task.display() + ". Now you have " + total + " tasks in the list.";
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getAll() {
        return this.tasks;
    }

    public String getTasksWithKeyword(String keyword) {
        ArrayList<Task> selectedTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.matches(keyword)) {
                selectedTasks.add(task);
            }
        }

        TaskList selectedTasksList = new TaskList(selectedTasks);
        return selectedTasksList.display();
    }
}
