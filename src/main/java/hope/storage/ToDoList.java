package hope.storage;

import java.util.ArrayList;

import hope.tasks.Task;


/**
 * A class representing a to-do list that stores and manages a collection of tasks.
 * The tasks are stored in an ArrayList and can be added, retrieved, removed, and displayed.
 */
public class ToDoList {

    private ArrayList<Task> list;

    /**
     * Constructs a ToDoList with the specified list of tasks.
     *
     * @param list the ArrayList of Task objects to initialize the to-do list
     */
    public ToDoList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the to-do list.
     *
     * @param t the Task object to be added to the list
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Retrieves a task from the to-do list at the specified index.
     * The index is 1-based for user convenience, but internally adjusted to 0-based.
     *
     * @param i the 1-based index of the task to retrieve
     * @return the Task object at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Returns the number of tasks in the to-do list.
     *
     * @return the size of the task list
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes a task from the to-do list at the specified index.
     *
     * @param t the 0-based index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int t) {
        list.remove(t);
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    };
}
