package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Contains the task list and operations to add/delete tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified position
     *
     * @param index the index of the task to return
     * @return the task at the specified position
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the end of the list
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified position
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks or unmarks a task at the specified position
     *
     * @param index  the index of the task to mark/unmark
     * @param isDone true to mark as done, false to mark as not done
     */
    public void markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        task.setIsDone(isDone);
    }

    /**
     * Returns a copy of all tasks in the list
     *
     * @return a list containing all tasks
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a copy of all tasks in the list
     *
     * @return a list containing all tasks
     */
    public List<Task> filterTasksByKeyword(String keyword) {
        List<Task> copy = new ArrayList<>(tasks);
        Stream<Task> filteredStream = copy.stream().filter(task -> task.getName().contains(keyword));
        return filteredStream.toList();
    }

    /**
     * Replaces the current task list with a new set of tasks
     *
     * @param tasks the new list of tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
