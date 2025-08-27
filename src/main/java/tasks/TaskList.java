package tasks;

import exception.RainyException;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /** Constructs an empty TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks the list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     * @return deleted task
     * @throws RainyException if the index is invalid
     */
    public Task deleteTask(int index) throws RainyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RainyException("oh no!!! that task number doesn’t exist :c");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the index
     * @throws RainyException if the index is invalid
     */
    public Task getTask(int index) throws RainyException {
        if (index < 0 || index >= tasks.size()) {
            throw new RainyException("oh no!!! that task number doesn’t exist :c");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task
     * @throws RainyException if the index is invalid
     */
    public void markTask(int index) throws RainyException {
        getTask(index).markAsDone();
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param index the index of the task
     * @throws RainyException if the index is invalid
     */
    public void unmarkTask(int index) throws RainyException {
        getTask(index).unmark();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Prints the task list.
     */
    public void printList() {
        System.out.println("here's your list so far:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
