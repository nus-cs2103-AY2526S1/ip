package evansbot.task;

import java.io.IOException;
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
     */
    public void addTask(Task task) {
        tasks.add(task);
        save();
        System.out.println("############################################################");
        System.out.println("Got it. I have added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println("############################################################");
    }

    /**
     * Lists all tasks in the task list to the user.
     */
    public void listTasks() {
        System.out.println("############################################################");
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("############################################################");
    }

    /**
     * Marks a task as done based on the provided 1-based index.
     *
     * @param index 1-based index of the task to mark as done.
     * @throws InvalidTaskIndexException If the index is out of range.
     */
    public void markTask(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException(tasks.size());
        }
        Task task = tasks.get(index - 1);
        task.markAsDone();
        save();
        System.out.println("############################################################");
        System.out.println("Good Job! I have marked this task as done:");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    /**
     * Unmarks a task as done based on the provided 1-based index.
     *
     * @param index 1-based index of the task to unmark.
     * @throws InvalidTaskIndexException If the index is out of range.
     */
    public void unmarkTask(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException(tasks.size());
        }
        Task task = tasks.get(index - 1);
        task.unmarkDone();
        save();
        System.out.println("############################################################");
        System.out.println("Oh no! I have unmarked this task for now:");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    /**
     * Deletes a task from the task list based on the provided 1-based index
     * and saves the updated list.
     *
     * @param index 1-based index of the task to delete.
     */
    public void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        System.out.println("############################################################");
        System.out.println("Okay! I will remove this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now there is " + (tasks.size() - 1) + " tasks in the list!");
        System.out.println("############################################################");
        tasks.remove(index - 1);
        save();
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
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }
}
