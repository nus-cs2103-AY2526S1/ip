package bytebot.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bytebot.ByteException;

/**
 * List of tasks with basic operations and persistence.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with existing tasks.
     *
     * @param initialTasks Tasks to fill the list
     */
    public TaskList(List<Task> initialTasks) {
        this.tasks = new ArrayList<>(initialTasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return No. of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list
     *
     * @return List of tasks
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Gets a task by index.
     *
     * @param index Index to get
     * @return Task at the given index
     * @throws ByteException If index is invalid
     */
    public Task get(int index) throws ByteException {
        if (index < 0 || index >= tasks.size()) {
            throw new ByteException("Task number is invalid");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add
     * @return The same task for chaining
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task at index.
     *
     * @param index Index to delete
     * @return The removed task
     * @throws ByteException If index is invalid
     */
    public Task delete(int index) throws ByteException {
        Task removed = get(index);
        tasks.remove(index);
        return removed;
    }

    /**
     * Marks the task at index as done.
     *
     * @param index Index to mark
     * @throws ByteException If index is invalid
     */
    public void mark(int index) throws ByteException {
        Task task = get(index);
        task.mark();
    }

    /**
     * Marks the task at index as not done.
     *
     * @param index Index to unmark
     * @throws ByteException If index is invalid
     */
    public void unmark(int index) throws ByteException {
        Task task = get(index);
        task.unmark();
    }

    /**
     * Writes tasks to a file in a line-based format.
     *
     * @param writer Open writer
     * @throws IOException If writing fails
     */
    public void writeToFile(FileWriter writer) throws IOException {
        for (Task task : tasks) {
            String status = task.isDone ? "1" : "0";
            writer.write(status + " | " + task + "\n");
        }
    }
}


