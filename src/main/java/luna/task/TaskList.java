package luna.task;

import java.io.Serializable;
import java.util.ArrayList;

import luna.exception.LunaException;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the {@code String} representation of the {@code TaskList}.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            string.append(i + 1).append(". ").append(tasks.get(i));
            if (i != taskCount - 1) {
                string.append("\n");
            }
        }
        return string.toString();
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a {@code Task} to the {@code TaskList}.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the {@code Task} deleted from the {@code TaskList}.
     *
     * @param taskNumber Task number of the task to be deleted.
     */
    public Task delete(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new LunaException("Task " + taskNumber + " doesn't exist.");
        }
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the {@code Task} that was marked as done.
     *
     * @param taskNumber Task number of the task to be marked as done.
     */
    public Task markAsDone(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new LunaException("Task " + taskNumber + " doesn't exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the {@code Task} that was unmarked as done.
     *
     * @param taskNumber Task number of the task to be unmarked as done.
     */
    public Task unmarkAsDone(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new LunaException("Task " + taskNumber + " doesn't exist.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        return task;
    }

    public TaskList find(String search) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(search)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}