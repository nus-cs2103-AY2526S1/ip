package beebong.task;

import java.util.ArrayList;
import java.util.List;

import beebong.storage.Storage;

/**
 * Represents a list of {@link Task} objects.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} from an existing list of tasks.
     * Used when loading tasks from storage.
     *
     * @param tasks list of tasks to initialize the {@code TaskList} with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks currently in the list.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the specified task as completed or incomplete.
     *
     * @param taskNum    the index of the task in the list (0-indexed).
     * @param isComplete {@code true} to mark as completed, {@code false} to mark as incomplete.
     * @throws IndexOutOfBoundsException if taskNum is invalid.
     */
    public void markTaskAs(int taskNum, boolean isComplete) throws IndexOutOfBoundsException {
        if (isComplete) {
            this.tasks.get(taskNum).markCompleted();
        } else {
            this.tasks.get(taskNum).markIncomplete();
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param taskNum the index of the task to delete (0-indexed).
     * @return the deleted task.
     * @throws IndexOutOfBoundsException if taskNum is invalid.
     */
    public Task deleteTask(int taskNum) throws IndexOutOfBoundsException {
        return this.tasks.remove(taskNum);
    }

    /**
     * Writes all tasks in this list to persistent storage.
     *
     * @param storage the {@link Storage} instance used by the chatbot.
     */
    public void writeTasksToFile(Storage storage) {
        storage.writeTasksToFile(tasks);
    }

    /**
     * Finds all tasks whose names contain the given keyword (case-insensitive).
     *
     * @param keyword the keyword to search for.
     * @return a new {@code TaskList} containing all matching tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task t : tasks) {
            // Make both to lowerCase to allow case-insensitive matching
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                newTaskList.addTask(t);
            }
        }
        return newTaskList;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return output.toString();
    }
}
