package luna;

import java.io.IOException;
import java.util.ArrayList;

import luna.tasks.Task;

/**
 * Manages a list of tasks.
 * Methods for adding, deleting, marking, and unmarking tasks.
 * Saves changes to persistent storage after each modification.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Constructs a TaskList with the given list of tasks and storage.
     *
     * @param taskList The initial list of tasks.
     * @param storage The Storage object used to save tasks to disk.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    /**
     * Adds a task to the current taskList.
     * @param t The task to be added.
     */
    public String addTask(Task t) {
        taskList.add(t);
        saveTasks();
        return "Okay! I've added this task:\n" + "  " + t + "\n"
                + "Looks like you have " + taskList.size() + " tasks in the list...";
    }

    /**
     * Deletes a task from t he current taskList.
     * @param index The index of task to be deleted.
     */
    public String deleteTask(int index) {
        Task t = taskList.remove(index);
        saveTasks();
        return "Okay! I've removed this task:\n" + "  " + t + "\n"
                + "Looks like you have " + taskList.size() + " tasks in the list...";
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to be marked as completed.
     * @return
     */
    public String markTask(int index) {
        Task t = taskList.get(index);
        t.mark();
        saveTasks();
        return  "Yay!! I've marked this task as done:\n  " + " " + t;
    }

    /**
     * Unmarks a task as uncompleted.
     * @param index The index of the task to be unmarked as uncompleted.
     */
    public String unmarkTask(int index) {
        Task t = taskList.get(index);
        t.unmark();
        saveTasks();
        return "Oh no :( I've marked this task as not done yet:\n " + " " + t;
    }

    private void saveTasks() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
