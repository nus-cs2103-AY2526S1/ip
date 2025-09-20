package zell.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the TaskList which stores tasks
 */
public class TaskList {
    // Arraylist which stores all the tasks
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs TaskList with a ArrayList of tasks.
     * @param tasks The ArrayList of tasks to initialize our TaskList with.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task List given is null";
        this.tasks = tasks;
    }

    /**
     * Stores a task in our TaskList.
     * @param task The task to store.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task in the TaskList as done.
     * @param index The index of a task to mark as done.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index - 1).setDone(true);
    }

    /**
     * Marks a task in the TaskList as not done.
     * @param index The index of a task to mark as not done.
     */
    public void markTaskAsNotDone(int index) {
        this.tasks.get(index - 1).setDone(false);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Removes a task from the TaskList.
     * @param index The index of a task to remove.
     */
    public void removeTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Returns all tasks as a string which is properly formatted.
     * @return A string of all tasks.
     */
    public String listAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            stringBuilder.append(formatTask(i, currentTask));
        }

        return stringBuilder.toString();
    }

    /**
     * Returns all tasks as a string which is properly formatted that contains the word.
     * @param word The word to match.
     * @return A string of all that contains the word.
     */
    public String listAllTasksContainingWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            String taskToString = currentTask.toString();

            if (taskToString.contains(word)) {
                stringBuilder.append(formatTask(count, currentTask));
                count++;
            }

        }

        return stringBuilder.toString();
    }

    /**
     * Formats a task in the proper format which we are going to print
     * @param num The task number.
     * @param task The task to format.
     * @return The properly formatted task as a string.
     */
    public String formatTask(int num, Task task) {
        return String.format(" %d. %s\n", num + 1, task);
    }

    public List<String> getAllTasksInString() {
        List<String> tasks = new ArrayList<>();

        for (Task task: this.tasks) {
            tasks.add(task.taskToString());
        }

        return tasks;
    }

    /**
     * Checks if a task exists.
     * @param index The index that we use to check.
     * @return A boolean indicating if a task exists.
     */
    public boolean doesTaskExist(int index) {
        return index > 0 && index <= getNumberOfTask();
    }

    public int getNumberOfTask() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        return String.format("\nThere are currently %d task in the list.", getNumberOfTask());
    }
}
