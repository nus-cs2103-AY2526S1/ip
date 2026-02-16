package task;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.Storage;

/** A class storing a list of Tasks, is serializable. */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    private String saveFile;
    private boolean isSaveable = true;

    /**
     * Constructs an empty TaskList with the default save file "lmbd.save".
     */
    public TaskList() {
        this("lmbd.save");
    }

    /**
     * Constructs an empty TaskList with the specified save file.
     *
     * @param saveFile
     *            The name of the file to save the task list to.
     */
    public TaskList(String saveFile) {
        this.saveFile = saveFile;
        tasks = new ArrayList<>();
    }

    /**
     * Returns the name of the save file associated with this TaskList.
     *
     * @return The save file name.
     */

    public String getSaveFile() {
        return saveFile;
    }

    /** Get number of tasks in the list */
    public int getTaskSize() {
        return tasks.size();
    }

    /** Adds the given Task object to the list */
    public void addTask(Task t) {
        t.associateList(this);
        tasks.add(t);
        save();
    }

    /**
     * Marks or unmarks a task at the specified index.
     *
     * @param index
     *            The zero-based index of the task to mark/unmark.
     * @param isMarked
     *            True to mark the task as done, false to unmark it.
     * @return True if the mark status was changed, false otherwise.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range (index < 0 || index >=
     *             getTaskSize()).
     */
    public boolean mark(int index, boolean isMarked) {
        boolean m = tasks.get(index).isDone();
        tasks.get(index).mark(isMarked);
        save();

        return m != isMarked;
    }

    /**
     * Saves the current state of the TaskList to the associated save file. If
     * saving is not enabled (`isSaveable` is false), this method does nothing. If
     * an IOException occurs during saving, an error message is printed to the
     * console.
     */
    public void save() {
        if (!isSaveable) {
            return;
        }
        try {
            Storage.save(this);
        } catch (IOException e) {
            System.out.println("Unable to backup task list, your data might be lost.");
        }
    }

    /**
     * Checks if the task at the specified index is marked as done.
     *
     * @param index
     *            The zero-based index of the task to check.
     * @return True if the task is done, false otherwise.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range (index < 0 || index >=
     *             getTaskSize()).
     */
    public boolean isMarked(int index) {
        return tasks.get(index).isDone();
    }

    /**
     * Returns the title of the task at the specified index.
     *
     * @param index
     *            The zero-based index of the task.
     * @return The title of the task.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range (index < 0 || index >=
     *             getTaskSize()).
     */
    public String getTaskTitle(int index) {
        return tasks.get(index).getTaskTitle();
    }

    /**
     * Returns the string representation of the task at the specified index. This
     * typically includes its type, completion status, and description.
     *
     * @param index
     *            The zero-based index of the task.
     * @return The string representation of the task.
     * @throws IndexOutOfBoundsException
     *             If the index is out of range (index < 0 || index >=
     *             getTaskSize()).
     */
    public String getTaskToString(int index) {
        return tasks.get(index).toString();
    }

    /** Removes the task from the list at the given index */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Finds tasks in the list whose titles match the given pattern.
     *
     * @param pattern
     *            The string pattern to match against task titles.
     * @return A list of tasks that match the pattern.
     */
    public List<Task> find(String pattern) {
        return tasks.stream().filter(x -> x.match(pattern)).collect(Collectors.toList());
    }

    /**
     * Sets whether this TaskList can be saved. If `s` is false, calls to `save()`
     * will be ignored.
     *
     * @param s
     *            True to enable saving, false to disable.
     */
    public void setIsSaveable(boolean s) {
        this.isSaveable = s;
    }
}
