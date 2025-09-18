package tony.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index - 1);
    }

    public void markDone(int index) {
        tasks.get(index - 1).markDone();
    }

    public void markUndone(int index) {
        tasks.get(index - 1).markUndone();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
