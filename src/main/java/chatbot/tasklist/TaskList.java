package chatbot.tasklist;

import java.util.ArrayList;

import chatbot.task.Task;



/**
 * Represents a list of tasks and provides methods to manage them.
 * Supports adding, retrieving, deleting, and displaying tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given ArrayList of tasks.
     *
     * @param tasks an ArrayList of Task objects to initialize the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the Task object to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the Task object at the specified index
     */
    public Task getTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index the index of the task to delete
     */
    public String deleteTask(int index) {
        assert index >= 0 : "Index cannot be negative";
        assert index < tasks.size() : "Index out of bounds";
        try {
            String removedTask = tasks.get(index).toString();
            tasks.remove(index);
            return "deleting this task" + "\n" + removedTask;
        } catch (IndexOutOfBoundsException e) {
            return "Index out of bounds BEEP B00P";
        }
    }

    /**
     * Clears the tasklist
     * @return String acknowledging action
     */
    public String reset() {
        tasks.clear();
        return "reset completed";
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() { return tasks.size(); }

    /**
     * Returns a string representation of the task list.
     *
     * @return a formatted string listing all tasks with their indices
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i));

            if (i < tasks.size() - 1) {
                result.append("\n"); // Add newline except for the last task
            }
        }

        return result.toString();
    }
}
