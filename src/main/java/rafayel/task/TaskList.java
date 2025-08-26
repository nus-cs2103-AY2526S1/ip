package rafayel.task;

import java.util.ArrayList;

import rafayel.RafayelException;
import rafayel.Storage;

/**
 * ArrayList that stores a list of tasks.
 * Provides operations to:
 * Add new tasks.
 * Remove existing task.
 * Retrieve tasks by index.
 * Check for valid task numbers.
 * Display all tasks in the list.
 *
 * Some methods may throw RafayelException if
 * invalid task numbers are provided.
 */
public class TaskList {

    /* ArrayList that stores a list of tasks */
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the initial list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty Tasklist with the specified list of tasks.
     * Overloaded Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param newTask the task to be added to the list.
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Checks if the specified task number of valid for this list.
     * A valid task number must be between 0 (inclusive)
     * and the size of the list (exclusive).
     *
     * @param taskNumber the task number to check.
     * @return true if the task number is valid and in the right range.
     * @throws RafayelException if the task number is invalid.
     */
    public boolean checkTaskNumber(int taskNumber) throws RafayelException {
        if (taskNumber < 0 || taskNumber >= this.getSize()) {
            throw new RafayelException("Invalid task number.");
            // return false;
        } else {
            return true;
        }
    }

    /**
     * Checks and retrieves the Task based on the taskNumber.
     * Invalid taskNumber will throw RafayelException.
     *
     * @param taskNumber the task number to get.
     * @return Task with the taskNumber.
     * @throws RafayelException if the task number is invalid.
     */
    public Task get(int taskNumber) throws RafayelException {
        if (checkTaskNumber(taskNumber)) {
            return tasks.get(taskNumber);
        }
        return null;
    }

    /**
     * Checks and removes the Task based on the taskNumber.
     * Invalid taskNumber will throw RafayelException.
     *
     * @param taskNumber the task number to remove.
     * @return the deleted Task (of the taskNumber).
     * @throws RafayelException if the task number is invalid.
     */
    public Task remove(int taskNumber) throws RafayelException {
        if (checkTaskNumber(taskNumber)) {
            return tasks.remove(taskNumber);
        }
        return null;
    }

    /**
     * Displays all tasks in the list to the standard output.
     * If the list is empty, displays a message indicating this.
     * Tasks are displayed with their position number (starting from 1).
     *  1. [task]
     *  2. [task]
     */
    public void getTaskList() {
        if (this.getSize() == 0) {
            System.out.println("There's nothing in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.getSize(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
                // System.out.println(String.format("%d. %s", i + 1, data[i]));
            }
        }
    }

    /**
     * @return the Arraylist storing all the tasks
     */
    public ArrayList<Task> getAll() {
        return this.tasks;
    }

}
