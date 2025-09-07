package lynx.storage;

import java.util.ArrayList;
import java.util.stream.Stream;

import objectclasses.task.Task;

/**
 * Contains methods that directly access the task list.
 */
public class LynxTaskList {

    private final ArrayList<Task> commands = new ArrayList<>(100);

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return Number of tasks.
     */
    public int getCount() {
        return commands.size();
    }

    /**
     * Returns the task list as a stream.
     *
     * @return Stream of tasks.
     */
    public Stream<Task> getAllTasks() {
        return commands.stream();
    }

    /**
     * Clears the task list of all tasks.
     */
    public void clearTasks() {
        commands.clear();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return String to represent task added.
     */
    public String addTask(Task task) {
        commands.add(task);
        return String.format("Added:%n     %s%nYou currently have %d task(s) in your list.",
                task, getCount());
    }

    /**
     * Removes a task from the task list.
     *
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        commands.remove(task);
    }

}
