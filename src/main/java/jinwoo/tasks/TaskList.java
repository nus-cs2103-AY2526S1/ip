package jinwoo.tasks;

import java.util.ArrayList;
import java.util.Stack;

import jinwoo.exception.DukeException;

/**
 * TaskList class to manage a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    // Idea from copilot for undo feature
    private Stack<ArrayList<Task>> history;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor with existing tasks and initializes history stack
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.history = new Stack<>();
    }

    /**
     * Save current state to history stack
     */
    public void saveState() throws DukeException {
        ArrayList<Task> currentState = new ArrayList<>();
        for (Task task : tasks) {
            currentState.add(task.copyTask());
        }
        history.push(currentState);
    }

    /**
     * Undo to previous state
     */
    public void undoState() throws DukeException {
        if (history.isEmpty()) {
            throw new DukeException("Warning ??? : No actions to undo");
        }
        this.tasks = history.pop();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTasks(int taskNum) {
        return tasks.get(taskNum);
    }

    /**
     * Get size
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Mark
     */
    public void markTask(int taskNum) {
        tasks.get(taskNum).mark();
    }

    /**
     * Unmark
     */
    public void unmarkTask(int taskNum) {
        tasks.get(taskNum).unmark();
    }

    /**
     * Delete task
     */
    public Task deleteTask(int taskNum) {
        return tasks.remove(taskNum);
    }

    /**
     * Add task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

}
