package king.task;

import java.util.ArrayList;

import king.KingException;
import king.storage.KingStorage;

/**
 * Tasklist for the King to help with task management and storage
 */
public class KingTaskList {
    private final ArrayList<Task> tasks;
    private final KingStorage kingStorage = new KingStorage();

    /**
     * Instantiates a task list with data loaded from the database
     */
    public KingTaskList() throws KingException {
        ArrayList<Task> tasks = kingStorage.loadFile();
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Instantiates an empty task list with the data from the database reset
     *
     * @param needsReset If reset is true, reset the database.
     */
    public KingTaskList(boolean needsReset) throws KingException {
        this();
        if (needsReset) {
            resetList();
        }
    }

    /**
     * Gets the task list
     *
     * @return Tasklist of user
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list and database
     *
     * @param newTask New task to be added into the task list and database
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        kingStorage.addToFile(newTask);
    }

    /**
     * Gets the task at specific index of the task list
     *
     * @param idx Index to get task from
     * @return Task at index
     */
    public Task getTask(int idx) {
        assert tasks != null;
        return tasks.get(idx);
    }

    /**
     * Marks the task at specific index of the task list done
     *
     * @param idx Index to get task
     */
    public void markDone(int idx) {
        assert tasks != null;
        tasks.get(idx).markDone();
        kingStorage.markDone(idx);
    }

    /**
     * Unmarks the task at specific index of the task list done
     *
     * @param idx Index to get task
     */
    public void unmarkDone(int idx) {
        assert tasks != null;
        tasks.get(idx).unmarkDone();
        kingStorage.unmarkDone(idx);
    }

    /**
     * Deletes the task at specific index of the task list
     *
     * @param idx Index to get task
     */
    public Task deleteTask(int idx) {
        assert tasks != null;
        Task deletedTask = tasks.remove(idx);
        kingStorage.remove(idx);
        return deletedTask;
    }

    /**
     * Resets the task list in the database and array
     */
    private void resetList() {
        assert tasks != null;
        tasks.clear();
        kingStorage.resetFile();
    }

    /**
     * Clears all tasks from the list and storage.
     */
    public void clearAll() {
        resetList();
    }

    /**
     * Gets the size of the task list
     *
     * @return Size of task list
     */
    public int getSize() {
        assert tasks != null;
        return tasks.size();
    }
}
