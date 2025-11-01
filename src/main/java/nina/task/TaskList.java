package nina.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of Task objects.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;
    private static final long serialVersionUID = 10L;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the total number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index of the task to be extracted from the list
     * @return the Task object with the same index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task at the given position and prints confirmation.
     *
     * @param number index of the task to delete
     * @return the task removed from the list
     */
    public Task delete(int number) {
        if (number < 1 || number > tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks.remove(number - 1);
    }

    /**
     * Prints the list of tasks.
     * If the list is empty, nothing will be printed.
     */
    public String showList() {
        if (!tasks.isEmpty()) {
            StringBuilder sb = new StringBuilder("Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(tasks.get(i));
            }
            return sb.toString();
        } else {
            return "The list is empty";
        }
    }

    /**
     * Finds duplicates of the input task in a tasklist
     * @param t new task to be searched for duplicates when it is being added to the tasklist
     * @return index of the first duplicate found, i
     */
    public int findDuplicated(Task t) {
        String key = t.deDupKey();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).deDupKey().equals((key))) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns an unmodifiable iterable view of the tasks.
     *
     * @return an iterable collection of tasks
     */
    public Iterable<Task> items() {
        return Collections.unmodifiableList(tasks);
    }
}
