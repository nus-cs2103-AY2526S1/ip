package jerome;

import jerome.task.Task;

import java.util.ArrayList;

// Note: The Javadocs for this class were generated with AI assistance
/**
 * Represents a list of tasks and provides operations to add, remove,
 * retrieve, and filter tasks.
 **/
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a {@code TaskList} with the given initial tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
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
     * @param index The index of the task to retrieve.
     * @return The task at the given index.
     * @throws JeromeException If the index is invalid.
     */
    public Task get(int index) throws JeromeException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JeromeException("Please provide a valid index");
        }
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return An {@code ArrayList} of all tasks.
     */
    public ArrayList<Task> getAll() {
        return this.tasks;
    }

    /**
     * Filters the task list for tasks that contain the given keyword
     * in their description and returns a formatted string of matches.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A string listing the tasks that match the keyword.
     */
    public String filter(String keyword) {
        TaskList matches = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                matches.add(this.tasks.get(i));
            }
        }
        return matches.listMatchedTasks();

    }
    /**
     * Returns a formatted string of all tasks in the list.
     * Each task is numbered starting from 1.
     *
     * @return A string listing all tasks in the task list.
     */
    public String listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + this.tasks.get(i) + "\n";
        }
        return output;
    }

    public String listMatchedTasks() {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + this.tasks.get(i) + "\n";
        }
        return output;
    }
}
