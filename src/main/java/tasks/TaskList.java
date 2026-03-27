package tasks;

import java.util.ArrayList;

import amogus.AmogusException;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Getter for Task object.
     * @param idx index of task in the TaskList
     * @return Task object
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Adds task into arraylist
     *
     * @param task Task object
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from arraylist
     * @param idx index of task in arraylist to be removed
     */
    public void delete(int idx) {
        tasks.remove(idx);
    }

    /**
     * Marks task in arraylist
     * @param idx index of task in arraylist to be marked
     */
    public void mark(int idx) {
        tasks.get(idx).mark();
    }

    /**
     * Unmarks task in arraylist
     * @param idx index of task in arraylist to be unmarked
     */
    public void unmark(int idx) {
        tasks.get(idx).unmark();
    }

    /**
     * Tags the task in arraylist
     * @param idx index of task in arraylist
     * @param tag tag description
     */
    public void tag(int idx, String tag) throws AmogusException {
        tasks.get(idx).tag(tag);
    }

    /**
     * Gets task description of the object.
     * @param idx index of task in arraylist
     * @return String representation of task
     */
    public String getTaskDesc(int idx) {
        return tasks.get(idx).toString();
    }

    /**
     * Returns true or false if arraylist is currently empty
     * @return boolean
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Getter for number of tasks.
     * @return number of tasks in arraylist
     */
    public int getSize() {
        return tasks.size();
    }
}
