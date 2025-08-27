package zell.task;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Represents a task list which stores task that the user creates in the Zell chatbot
 */
public class TaskList {
    /** ArrayList object which stores all the tasks */
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task in the task list as done
     *
     * @param index The index of the task to be marked as done
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index - 1).setDone(true);
    }

    /**
     * Marks a task in the task list as not done
     *
     * @param index The index of the task to be marked as not done
     */
    public void markTaskAsNotDone(int index) {
        this.tasks.get(index - 1).setDone(false);
    }

    /**
     * Retrieves a task based on the index
     *
     * @param index The index of the task to be retrieved
     * @return The task to be retrieved based on the index provided
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Removes a task based on the index
     *
     * @param index The index of the task to be removed
     */
    public void removeTask (int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Goes through every task in the task list and formats it properly for the list command
     *
     * @return The string which displays all the tasks which are properly formatted for the list command
     */
    public String listAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            String taskFormatted = String.format(" %d. %s\n", i + 1, currentTask);
            stringBuilder.append(taskFormatted);
        }

        return stringBuilder.toString();
    }

    /**
     * Converts every task in the task list to its string format which we use for local storage
     *
     * @return A list of every task in its string format for local storage
     */
    public List<String> getAllTasksInString() {
        List<String> tasks = new ArrayList<>();

        for (Task task: this.tasks) {
            tasks.add(task.taskToString());
        }

        return tasks;
    }

    /**
     * Checks if a task exists based on the index provided. This is done by checking if 0 <= index <= size - 1
     * @param index The index of the task
     * @return A boolean indicating if the task exists
     */
    public boolean checkIfTaskExists(int index) {
        return index > 0 && index <= getNumberOfTask();
    }

    /**
     * Retrieves the number of task in the task list
     * @return The number of task in the task list
     */
    public int getNumberOfTask() {
        return this.tasks.size();
    }

    /**
     * Overrides the default toString
     * <p>
     * Displays a message indicating the number of task in the task list which is used after adding or removing
     * a task.
     * </p>
     * @return The toString format of TaskList
     */
    public String toString() {
        return String.format("\nThere are currently %d task in the list.", getNumberOfTask());
    }
}
