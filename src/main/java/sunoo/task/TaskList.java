package sunoo.task;

import java.util.ArrayList;

/**
 * Represents a list that stores tasks.
 */
public class TaskList {

    /** ArrayList object that stores the current tasks */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return Task added.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the task list corresponding to the given index.
     * Indexing starts from 1.
     *
     * @param taskIndex Index of the task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int taskIndex) { //indexing starts from 1
        return tasks.remove(taskIndex - 1);
    }

    /**
     * Marks a task as done in the task list corresponding to the given index.
     * Indexing starts from 1.
     *
     * @param taskIndex Index of the task to be marked as done.
     */
    public void markTask(int taskIndex) { //indexing starts from 1
        tasks.get(taskIndex - 1).markAsDone();
    }

    /**
     * Marks a task as not done in the task list corresponding to the given index.
     * Indexing starts from 1.
     *
     * @param taskIndex Index of the task to be marked as not done.
     */
    public void unmarkTask(int taskIndex) { //indexing starts from 1
        tasks.get(taskIndex - 1).markAsNotDone();
    }

    /**
     * Returns the list of current tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task from the task list corresponding to the given index.
     * Indexing starts from 1.
     *
     * @param index Index of the task to be returned.
     * @return Task of the specified index,
     */
    public Task getTask(int index) { //indexing starts from 1
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }
}
