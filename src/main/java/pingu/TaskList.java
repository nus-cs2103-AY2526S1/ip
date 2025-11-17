package pingu;

import java.util.ArrayList;

/**
 * Represents the list of tasks and provides operations to manage them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param taskIndex The 0-based index of the task to delete.
     * @return The deleted task.
     * @throws PinguException If the index is out of bounds.
     */
    public Task deleteTask(int taskIndex) throws PinguException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new PinguException("Task number is out of bounds.");
        }
        return tasks.remove(taskIndex);
    }
    
    /**
     * Marks a task as done.
     *
     * @param taskIndex The 0-based index of the task to mark.
     * @return The task that was marked as done.
     * @throws PinguException If the index is out of bounds.
     */
    public Task markTask(int taskIndex) throws PinguException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new PinguException("Task number is out of bounds.");
        }
        Task task = tasks.get(taskIndex);
        task.toggleStatus(true);
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param taskIndex The 0-based index of the task to unmark.
     * @return The task that was marked as not done.
     * @throws PinguException If the index is out of bounds.
     */
    public Task unmarkTask(int taskIndex) throws PinguException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new PinguException("Task number is out of bounds.");
        }
        Task task = tasks.get(taskIndex);
        task.toggleStatus(false);
        return task;
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     * 
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Generates a formatted string of matching tasks for display.
     * 
     * @param matchingTasks The list of matching tasks.
     * @return A formatted string for display.
     */
    public String getMatchingTasksString(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(".").append(matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Generates a formatted string of all tasks for display.
     *
     * @return A formatted string of the task list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }
}