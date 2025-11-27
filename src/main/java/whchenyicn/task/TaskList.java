package whchenyicn.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A List class to contain the tasks to be done.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates empty tasklist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates TaskList with a limit to the number of tasks.
     *
     * @param limit Max number of tasks.
     */
    public TaskList(int limit) {
        this.taskList = new ArrayList<>(limit);
    }

    /**
     * Creates a TaskList with given list, used to load from txt file.
     *
     * @param tlist Given tlist to load from.
     */
    public TaskList(List<Task> tlist) {
        this.taskList = tlist;
    }

    /**
     * Returns number of task in the list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns task at given index of taskList.
     *
     * @param index Index of task.
     * @return Task at the index.
     */
    public Task get(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        return this.taskList.get(index);
    }

    /**
     * Adds task to taskList.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        assert t != null : "Task to add cannot be null";
        this.taskList.add(t);
    }

    /**
     * Removes all tasks from list.
     */
    public void clear() {
        this.taskList.clear();
    }

    /**
     * Adds all task in given list to the list.
     *
     * @param tlist List of tasks to add.
     */
    public void addAll(List<Task> tlist) {
        this.taskList.addAll(tlist);
    }

    /**
     * Returns whether list have no tasks.
     *
     * @return Returns true if no tasks, false if it has tasks.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Converts TaskList to List.
     *
     * @return List of Tasks.
     */
    public List<Task> asList() {
        return taskList;
    }

    /**
     * Removes Task at index from taskList.
     *
     * @param index Index of task to be removed.
     * @return Task that was removed.
     */
    public Task remove(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds in remove()";
        return this.taskList.remove(index);
    }
}
