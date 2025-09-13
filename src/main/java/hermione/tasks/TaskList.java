package hermione.tasks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks in the Hermione application.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to be stored in the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param task The task to be deleted from the TaskList.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Gets a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";

        return this.tasks.get(index);
    }

    /**
     * Gets all tasks from the TaskList.
     *
     * @return A list of all tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets a list of tasks from the TaskList that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that contain the specified keyword.
     */
    public TaskList getTasksByKeyword(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return new TaskList(matchingTasks);
    }

    /**
     * Gets the number of completed tasks in the TaskList.
     *
     * @return The number of completed tasks in the TaskList.
     */
    public long getCompletedCount() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .count();
    }

    @Override
    public String toString() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                .collect(Collectors.joining("\n"));
    }
}
