package tweety.tasks;

import tweety.exceptions.InvalidTaskNumberException;
import tweety.exceptions.TweetyException;

import java.util.ArrayList;

/**
 * Manages a collection of tasks for the Tweety application.
 * Provides functionality to add, delete, mark, unmark and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initialises the task list with an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     * Initialises the task list with the provided ArrayList of tasks.
     *
     * @param tasks The existing list of tasks to manage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified position.
     *
     * @param taskNumber The 1-based index of the task to delete.
     * @return The deleted task.
     * @throws InvalidTaskNumberException If the task number is invalid or out of range.
     */
    public Task deleteTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Edits and returns the task at the specified position.
     *
     * @param taskNumber The 1-based index of the task to edit.
     * @param field The field to be edited.
     * @param newValue The new value to the field.
     * @throws InvalidTaskNumberException If the task number is invalid or out of range.
     */
    public Task editTask(int taskNumber, String field, String newValue) throws TweetyException {
        Task task = retrieveTask(taskNumber);
        task.editField(field, newValue);
        return task;
    }


    /**
     * Marks the specified task as completed.
     *
     * @param taskNumber The 1-based index of the task to mark as done.
     * @return The marked task.
     * @throws InvalidTaskNumberException If the task number is invalid or out of range.
     */
    public Task markTask(int taskNumber) throws InvalidTaskNumberException {
        Task task = retrieveTask(taskNumber);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the specified task as not completed.
     *
     * @param taskNumber The 1-based index of the task to unmark.
     * @return The unmarked task.
     * @throws InvalidTaskNumberException If the task number is invalid or out of range.
     */
    public Task unmarkTask(int taskNumber) throws InvalidTaskNumberException {
        Task task = retrieveTask(taskNumber);
        task.unmark();
        return task;
    }

    /**
     * Helper method to check and retrieve task with taskNumber
     *
     * @param taskNumber The 1-based index of the task to unmark.
     * @return Task that is allocated to taskNumber
     * @throws InvalidTaskNumberException If the task number is invalid or out of range.
     */
    public Task retrieveTask(int taskNumber) throws InvalidTaskNumberException{
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        return tasks.get(taskNumber - 1);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

}
