package LunarBot;

import LunarBot.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to taskList
     * @param task task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete task at index i
     * @param i index to delete from taskList
     * @return returns task deleted
     */
    public Task delete(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Returns the task at index i
     * @param i index to get from taskList
     * @return returns task at index i
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns size of taskList
     * @return returns the size of taskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a list of all tasks
     * @return list of all tasks
     */
    public List<Task> all() {
        return this.tasks;
    }
}
