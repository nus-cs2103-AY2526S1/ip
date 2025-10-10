package HawkerUncle.task;

import java.util.ArrayList;

/**
 * Represents a list of task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public int size() {
        return tasks.size();
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public Task get(int idx) {
        return tasks.get(idx);
    }
    public Task remove(int idx) {
        return tasks.remove(idx);
    }
    public boolean contains(Task task) {
        return tasks.contains(task);
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
