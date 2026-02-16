package guibot;

import java.util.ArrayList;

import guibot.exception.TaskNotFoundException;
import guibot.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     *
     * @return An empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        assert t != null : "Cannot add a null task";
        tasks.add(t);
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked as done (1 indexed).
     * @return String representation of the marked task.
     * @throws TaskNotFoundException If index does not correspond to a task on the list.
     */
    public String mark(int index) throws TaskNotFoundException {
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).mark();
            return tasks.get(index - 1).toString();
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index Index of task to be marked as not done (1 indexed).
     * @return String representation of the unmarked task.
     * @throws TaskNotFoundException If index does not correspond to a task on the list.
     */
    public String unmark(int index) throws TaskNotFoundException {
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).unmark();
            return tasks.get(index - 1).toString();
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Deletes a task.
     *
     * @param index Index of task to be deleted (1 indexed).
     * @return String representation of the deleted task.
     * @throws TaskNotFoundException If index does not correspond to a task on the list.
     */
    public String delete(int index) throws TaskNotFoundException {
        if (index > 0 && index <= tasks.size()) {
            return tasks.remove(index - 1).toString();
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Clears all tasks.
     */
    public void clear() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        assert tasks != null : "Cannot get size of null tasklist";
        return tasks.size();
    }


    /**
     * Returns a TaskList with all the tasks that contain the given string.
     *
     * @param string String to find.
     * @return Tasklist with all tasks that contain the given string.
     */
    public TaskList find(String string) {
        TaskList tasksContainingString = new TaskList();
        tasks.stream()
            .filter(task -> task.toString().contains(string))
            .forEach(task -> tasksContainingString.add(task));
        return tasksContainingString;
    }

    /**
     * Returns a string formatted for storing in a data file.
     */
    public String toStorageString() {
        return String.join("\n", tasks.stream().map(x -> x.toStorageString()).toArray(String[]::new));
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += String.format("\n%d.%s", i + 1, tasks.get(i).toString());
        }
        return s;
    }
}
