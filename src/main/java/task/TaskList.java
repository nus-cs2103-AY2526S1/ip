package task;

import java.util.ArrayList;

import misc.TaskBotException;

/**
 * Manages task list and the operations performed on it
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises task list (empty)
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises task list with a list of existing tasks
     * @param tasks list of existing tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds input task to task list
     * @param t task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves task at index i of task list
     * @param i target task index
     * @return task at index i
     */
    public Task getTask(int i) throws TaskBotException {
        if (i < 0 || i >= tasks.size()) {
            throw new TaskBotException("OOPS!! Target task index out of range.");
        }
        return tasks.get(i);
    }

    /**
     * Removes task at index i of task list
     * @param i target task index
     * @return removed task
     * @throws TaskBotException
     */
    public Task removeTask(int i)throws TaskBotException {
        if (i < 0 || i >= tasks.size()) {
            throw new TaskBotException("OOPS!! Target task index out of range.");
        }
        return tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns the whole task list
     * @return task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
