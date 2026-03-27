package pepe.task.tasklist;

import java.util.ArrayList;
import java.util.stream.Collectors;

import pepe.exception.PepeExceptions;
import pepe.task.EmptyTask;
import pepe.task.Task;


/**
 * Represents a list of tasks in Pepe.
 * <p>
 * Provides methods to add, delete, and access tasks, as well as check list size or emptiness.
 */
@SuppressWarnings("checkstyle:Regexp")
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param taskList the initial list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList with an initial capacity of 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        taskList.add(task);
    }

    /**
     * Sets a task to null from the list at the specified index.
     *
     * @param index the index of the task to delete (0-based)
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds for deleteTask: " + index;
        Task task = taskList.get(index);
        taskList.set(index, new EmptyTask());
        return task;
    }
    /**
     * Permanently removes all tasks that have been marked as deleted (or set to {@code null})
     * from the task list.
     * <p>
     * This method should be called after performing deletions to compact the list and ensure
     * task indices remain consistent. Without calling {@code wipe()}, deleted tasks remain
     * as placeholders in the list.
     * <p>
     * Typical usage:
     * <pre>
     *     tasks.delete(1);
     *     tasks.delete(3);
     *     tasks.wipe(); //clears the deleted tasks from the list
     * </pre>
     */
    public void wipe() {
        this.taskList.removeIf(task -> task instanceof EmptyTask);
    }
    /**
     * Deletes multiple tasks from the task list at the specified indices.
     * <p>
     * Each task is first soft-deleted (replaced with an empty placeholder) using
     * {@link #deleteTask(int)}. After all deletions are processed, the task list is
     * compacted by invoking {@link #wipe()} to remove the placeholder entries.
     * <p>
     * This method ensures that index shifting issues are avoided when deleting
     * multiple tasks from the underlying {@code ArrayList}.
     *
     * @param indices the array of task indices to delete. Each index should be in
     *                the range {@code [0, size())} at the time of deletion.
     * @return an {@link ArrayList} containing the {@link Task} objects that were deleted,
     *         in the same order as the provided indices
     * @throws IndexOutOfBoundsException if any of the provided indices are invalid
     */
    public ArrayList<Task> deleteSpecificTasks(int[] indices) throws PepeExceptions {
        ArrayList<Task> deletedTasks = new ArrayList<>(this.size());
        if (validateIndices(indices)) {
            for (int index : indices) {
                Task deletedTask = this.deleteTask(index);
                deletedTasks.add(deletedTask);
            }
        }
        this.wipe();
        return deletedTasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list has no tasks, false otherwise
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     */
    public Task get(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds for get: " + index;
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return the ArrayList containing all tasks
     */
    public ArrayList<Task> getTaskList() {
        assert taskList != null : "Underlying task list should never be null";
        return this.taskList;
    }

    /**
     * Searches for tasks whose names contain the given keyword.
     * <p>
     * This method does not modify the original task list. It returns a new
     * {@link TaskList} containing only the tasks that match the search criterion.
     *
     * @param taskName the keyword to search for in task names
     * @return a new {@link TaskList} containing tasks whose names contain {@code taskName}
     */
    public TaskList findTask(String taskName) {
        return new TaskList(this.taskList.stream()
                .filter(task -> task.getName().contains(taskName))
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }
    /**
     * Marks multiple tasks in the task list as completed.
     * <p>
     * This method validates all provided indices first to ensure they are within
     * bounds. If any index is invalid, no tasks are marked and a
     * {@link pepe.exception.PepeExceptions} is thrown. If all indices are valid,
     * each corresponding {@link Task} is marked using {@link Task#markTask()}.
     * <p>
     * The method returns the list of tasks that were successfully marked.
     *
     * @param indices an array of task indices to be marked as completed
     * @return an {@link ArrayList} of {@link Task} objects that were marked
     * @throws PepeExceptions if any of the provided indices are out of range
     */
    public ArrayList<Task> markTasks(int[] indices) throws PepeExceptions {
        ArrayList<Task> markedTasks = new ArrayList<>(this.size());
        if (validateIndices(indices)) {
            for (int index : indices) {
                Task task = this.get(index);
                task.markTask();
                markedTasks.add(task);
            }
        }
        return markedTasks;
    }
    /**
     * Unmarks multiple tasks in the task list as completed.
     * <p>
     * This method validates all provided indices first to ensure they are within
     * bounds. If any index is invalid, no tasks are unmarked and a
     * {@link pepe.exception.PepeExceptions} is thrown. If all indices are valid,
     * each corresponding {@link Task} is unmarked using {@link Task#markTask()}.
     * <p>
     * The method returns the list of tasks that were successfully unmarked.
     *
     * @param indices an array of task indices to be unmarked as completed
     * @return an {@link ArrayList} of {@link Task} objects that were unmarked
     * @throws PepeExceptions if any of the provided indices are out of range
     */
    public ArrayList<Task> unmarkTasks(int[] indices) throws PepeExceptions {
        ArrayList<Task> unmarkedTasks = new ArrayList<>(this.size());
        if (validateIndices(indices)) {
            for (int index : indices) {
                Task task = this.get(index);
                task.unmarkTask();
                unmarkedTasks.add(task);
            }
        }
        return unmarkedTasks;
    }
    /**
     * Validates that all provided indices are within the bounds of the task list.
     * <p>
     * Iterates through the given array of indices and checks whether each index
     * is greater than or equal to 0 and less than the size of the task list.
     * If any index is invalid, a {@link PepeExceptions} is thrown with a
     * descriptive error message.
     *
     * @param indices an array of task indices to validate
     * @return {@code true} if all indices are valid
     * @throws PepeExceptions if any index is out of bounds
     */
    private boolean validateIndices(int[] indices) throws PepeExceptions {
        for (int index : indices) {
            if (index < 0 || index >= this.size()) {
                throw new PepeExceptions("There is no task at index: " + (index + 1)
                        + "!\nAborting all Operations...");
            }
        }
        return true;
    }

}
