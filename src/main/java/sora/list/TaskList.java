package sora.list;

import java.util.ArrayList;

import sora.task.Task;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index the index of the task to delete.
     * @return the task which is deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return the list of tasks as an {@link ArrayList}.
     */
    public ArrayList<Task> getFullTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task to mark.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the index of the task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Find and return all the tasks which contain the specified keyword
     *
     * @param keyword the keyword to search
     * @return an {@link ArrayList} of all tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

}
