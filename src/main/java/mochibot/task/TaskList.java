package mochibot.task;

import java.util.ArrayList;

/**
 * This class represents an {@code ArrayList} of {@code Task} objects.
 * <p>
 *     The {@code TaskList} class provides methods to manipulate and retrieve tasks in the list.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int taskIndex) {
        return this.tasks.remove(taskIndex);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
