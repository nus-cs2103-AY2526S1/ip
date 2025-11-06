package yappy.backend;

import java.util.ArrayList;

import yappy.exception.YappyException;
import yappy.task.Task;

/**
 * Represents the TaskList populated with tasks from Yappy. A <code>TaskList</code> object corresponds to
 * a list of tasks represented by an ArrayList
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor that takes no arguments.
     * Initialises an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that takes in an ArrayList of Tasks and makes a copy of it.
     * @param tasks The tasks that have been stored in Yappy until now.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the ArrayList
     * @param task The new task to be added to the task list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from a specific index in the ArrayList.
     *
     * @param index Index of the task that is to be deleted.
     * @return Task that was deleted.
     * @throws YappyException If index >= tasks.size() or index < 0.
     */
    public Task delete(int index) throws YappyException {
        if (tasks.isEmpty()) {
            throw new YappyException("You have no tasks in your list!");
        } else {
            try {
                return this.tasks.remove(index);
            } catch (IndexOutOfBoundsException e) {
                throw new YappyException("Please specify a valid index!");
            }
        }
    }

    /**
     * Marks the task at position index in the ArrayList as completed.
     *
     * @param index Index of the task that is to be marked as completed.
     * @return Task that was marked as completed.
     * @throws YappyException If index >= tasks.size() or index < 0.
     */
    public Task mark(int index) throws YappyException {
        if (tasks.isEmpty()) {
            throw new YappyException("You have no tasks in your list!");
        } else {
            try {
                Task task = this.tasks.get(index);
                task.setCompleted();
                return task;
            } catch (IndexOutOfBoundsException e) {
                throw new YappyException("Please specify a valid index!");
            }
        }
    }

    /**
     * Marks the task at position index in the ArrayList as uncompleted.
     *
     * @param index Index of the task that is to be marked as uncompleted.
     * @return Task that was marked as uncompleted.
     * @throws YappyException If index >= tasks.size() or index < 0.
     */
    public Task unmark(int index) throws YappyException {
        if (tasks.isEmpty()) {
            throw new YappyException("You have no tasks in your list!");
        } else {
            try {
                Task task = this.tasks.get(index);
                task.setUncompleted();
                return task;
            } catch (IndexOutOfBoundsException e) {
                throw new YappyException("Please specify a valid index!");
            }
        }
    }

    /**
     * Returns an ArrayList of tasks that have added to the ArrayList.
     *
     * @return Current tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }
}
