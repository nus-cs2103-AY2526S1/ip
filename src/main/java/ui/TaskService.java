package ui;

import java.util.ArrayList;
import java.util.List;

import model.Task;

/**
 * Some operations dealing with task
 */
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int counter = 0;

    /**
     * Get all the existing tasks
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns task in a index
     *
     * @param index  index of task in tasklist
     * @return the task
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Get all tasks with its description contains given taskName
     *
     * @param taskName  The name target task contains
     * @return all matched tasks
     */
    public List<Task> searchTask(String taskName) {
        List<Task> selectedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(taskName)) {
                selectedTasks.add(task);
            }
        }
        return selectedTasks;
    }

    /**
     * Add a task to tasklist
     *
     * @param task  The task added
     */
    public void addTask(Task task) {
        tasks.add(task);
        counter++;
    }

    /**
     * Remove a task in tasklist
     *
     * @param index  The index of task in tasklist to remove
     */
    public void removeTask(int index) {
        tasks.remove(index);
        counter--;
    }

    /**
     * Get total number of task existed
     *
     * @return the current number of tasks
     */
    public int getTaskCount() {
        return counter;
    }

    /**
     * Mark specific task as done
     *
     * @param index  The index of task to be marked
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmark specific task that been marked
     *
     * @param index  The index of task to be unmark
     */
    public void unmarkTasks(int index) {
        tasks.get(index).unmark();
    }
}
