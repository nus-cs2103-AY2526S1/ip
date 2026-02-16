package botchat.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * The data structure to store tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Contructs a list of tasks.
     * @param tasks the list of tasks to be abstracted away.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To get all the tasks in the tasklist
     * @return a list of all the tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the list of tasks.
     * @return size of list of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * To get a task at index i.
     * @param i the index of the task to retrieve.
     * @return the task at index i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Add a task to the list.
     * @param task the task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete the tasks.
     * @param i the index of the task to delete.
     * @return the task which was deleted.
     */
    public Task deleteTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Find other tasks with description containing user input.
     * @param find string to match description with.
     * @return list of tasks containing matching description to input.
     */
    public TaskList find(String find) {
        TaskList result = new TaskList();
        for (Task task: tasks) {
            if (task.getDescription().contains(find)) {
                result.addTask(task);
            }
        }
        return result;
    }

}
