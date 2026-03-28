package vicky.tasklist;

import java.util.ArrayList;

/**
 * TaskList represents a task list of Tasks.
 *
 * @author Rachel
 *
 */
public class TaskList {
    public static final String INDENT = "    ";
    private static int counter;
    final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class, initializes the TaskList task with an arraylist of tasks..
     *
     * @param tasks The arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        counter = tasks.size();
    }

    /**
     * Overloaded constructor for TaskList class, initializes the TaskList task with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        counter = 0;
    }

    /**
     * Returns number of tasks in the tasks array.
     * @return counter
     */
    public int len() {
        return counter;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if task list is empty.
     */
    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean hasTask(int number) {
        return number <= counter;
    }

    /**
     * Returns task at specified index.
     * @param i index of specified task.
     * @return task.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * @inheritDoc Returns the string representation of the tasklist.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= counter; i++) {
            result = result + String.format(INDENT + "%d. %s\n", i, tasks.get(i - 1));
        }
        return result;
    }

    /**
     * Unmarks task at specified index and returns specified task.
     *
     * @param index index of task to unmark.
     * @return unmarked task.
     * @throws IndexOutOfBoundsException if index is larger than or equals to counter.
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index < counter) {
            tasks.get(index).unmark();
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("CHIBAI THE INDEX OUT OF BOUNDS LA");
        }
    }

    /**
     * Marks task at specified index and returns specified task.
     *
     * @param index index of task to mark.
     * @return marked task.
     * @throws IndexOutOfBoundsException if index is larger than or equals to counter.
     */
    public Task markTask(int index) throws IndexOutOfBoundsException {
        if (index < counter) {
            tasks.get(index).mark();
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("CHIBAI THE INDEX OUT OF BOUNDS LA");
        }
    }

    /**
     * Adds Task into tasks ArrayList.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        counter++;
    }

    /**
     * Deletes specified task from tasklist.
     *
     * @param i index of task to delete.
     * @return deleted task.
     * @throws IndexOutOfBoundsException if index is larger than or equals to counter.
     */
    public Task deleteTask(int i) throws IndexOutOfBoundsException {
        if (i >= counter) {
            throw new IndexOutOfBoundsException("Nah cuh your list too short. Try again hoe!");
        } else {
            Task t = tasks.remove(i);
            counter--;
            return t;
        }
    }

    /**
     * Returns a task list containing tasks in this task list that whose descriptions contain the keyword str.
     *
     * @param str The keyword.
     * @return A task list containing matching tasks.
     */
    public TaskList matchingTasks(String str) {
        TaskList matching = new TaskList();
        for (Task t: this.tasks) {
            if (t.contains(str)) {
                matching.addTask(t);
            }
        }
        return matching;
    }

    /**
     * Deletes all tasks from task list.
     */
    public void clearAllTasks() {
        this.tasks.clear();
        counter = 0;
    }

}
