package Chunky.Task;

import java.util.ArrayList;

/**
 * Contain List of main.java.Chunky.Chunky.main.java.Chunky.Task.Task
 * Supports operations of adding and deleting tasks from List
 */
public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.taskList = lst;
    }

    /**
     * Add task to taskList
     * @param t main.java.Chunky.Chunky.main.java.Chunky.Task.Task to be added inside tasklist
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Remove task from main.java.Chunky.Chunky.main.java.Chunky.Task.TaskList
     * @param index the task which the index contains
     * @throws IllegalArgumentException if index out of bounds or the list is empty
     */
    public void delete(int index) {
        if (taskList.size() < index || taskList.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        taskList.remove(index);
    }

    /**
     * Finds matching task based on keyword
     * @param keyword the keyword to match the tasks
     * @return the matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Find the size of the list
     * @return the size of the list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Return the task at given index
     * @param index the task which the index contains
     * @throws IllegalArgumentException if index out of bounds or the list is empty
     * @return the task at the index
     */
    public Task get(int index) {
        if (taskList.size() < index || taskList.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        return taskList.get(index);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }
}
