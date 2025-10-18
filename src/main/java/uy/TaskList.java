package uy;

import java.util.ArrayList;

/**
 * Container for Task objects. Provides basic list operations used by the app.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Construct a TaskList with an existing list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Construct an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a TaskList containing tasks whose descriptions contain the keyword.
     *
     * @param keyword search keyword
     * @return TaskList of matching tasks
     */
    public TaskList findTasks(String keyword) {
        TaskList result = new TaskList();
        for(Task task : this.tasks) {
            if(task.getTask_name().contains(keyword)) {
                result.addTask(task);
            }
        }

        return result;
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index zero-based index
     * @return task at index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as done.
     */
    /**
     * Marks the task at the given index as done.
     */
    public void markTask(int index) {
        assert (index >= 0 && index < tasks.size());
        tasks.get(index).mark();
    }

    /**
     * Adds a task to the task list and sorts the list.
     */
    /**
     * Adds a task to the list and sorts the list.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        tasks.sort(null);
    }

    /**
     * Removes a task from the task list and sorts the list.
     */
    /**
     * Removes a task from the list and sorts the list.
     *
     * @param task task to remove
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
        tasks.sort(null);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Marks the task at the given index as not done.
     */
    /**
     * Marks the task at the given index as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
