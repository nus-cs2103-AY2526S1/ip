package evansbot.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import evansbot.Exceptions.InvalidTaskIndexException;

/**
 * Represents a list of tasks that can be added, listed, marked as done, unmarked, or deleted.
 * Handles saving the task list to persistent storage after each modification.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs an empty TaskList with a given storage.
     *
     * @param storage Storage used to persist tasks.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructs a TaskList with pre-existing tasks and storage.
     *
     * @param storage Storage used to persist tasks.
     * @param tasks Initial list of tasks.
     */
    public TaskList(Storage storage, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the task list and saves the updated list.
     *
     * @param task Task to be added.
     * @return String of Task added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        save();
        return "Got it. I have added this task: \n"
             + " " + task + "\n"
             + "Now you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Lists all tasks in the task list to the user.
     *
     * @return String list of tasks.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list \n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks a task as done based on the provided 1-based index.
     *
     * @param index 1-based index of the task to mark as done.
     * @return String with the marked task.
     * @throws InvalidTaskIndexException If the index is out of range.
     */
    public String markTask(int index) throws InvalidTaskIndexException {
        Task task = getTaskAtIndex(index);
        task.markAsDone();
        save();
        return "Good Job! I have marked this task as done: \n"
             + " " + task.toString();
    }

    /**
     * Unmarks a task as done based on the provided 1-based index.
     *
     * @param index 1-based index of the task to unmark.
     * @return String with the unmark task.
     * @throws InvalidTaskIndexException If the index is out of range.
     */
    public String unmarkTask(int index) throws InvalidTaskIndexException {
        Task task = getTaskAtIndex(index);
        task.unmarkDone();
        save();
        return "Oh no! I have unmarked this task for now: \n"
             + " " + task.toString();
    }

    /**
     * Deletes a task from the task list based on the provided 1-based index
     * and saves the updated list.
     *
     * @param index 1-based index of the task to delete.
     * @return String of the deleted task.
     */
    public String deleteTask(int index) throws InvalidTaskIndexException {
        Task task = getTaskAtIndex(index);
        tasks.remove(task);
        save();
        return "Okay! I will remove this task: \n"
             + " " + task.toString()
             + " \nNow there is " + (tasks.size()) + " tasks in the list!";
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return Number of tasks in the list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in an ArrayList.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Saves the current task list to storage.
     */
    private void save() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }

    /**
     * Returns the task at the given 1-based index after validating it.
     *
     * @param index 1-based index of the task.
     * @return Task at the specified index.
     * @throws InvalidTaskIndexException If the index is out of range.
     */
    private Task getTaskAtIndex(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException(tasks.size());
        }
        return tasks.get(index - 1);
    }

    /**
     * Returns the list of task that falls on that Date.
     * @param date Date which the task falls on.
     * @return ArrayList of task that falls on the date.
     */
    public String getTasksForDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your schedule for ").append(date).append("\n");
        int count = 1;
        for (Task task : tasks) {
            if (task.isOnDate(date)) {
                sb.append(count).append(". ").append(task).append("\n");
                count++;
            }
        }
        return sb.toString();
    }
}
