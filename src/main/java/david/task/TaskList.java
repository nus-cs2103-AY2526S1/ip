package david.task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
import david.storage.Storage;

public class TaskList {
    private ArrayList<Task> items;
    private Storage storage;

    /**
     * Initialises a TaskList from a storage object.
     *
     * @param storage Storage.
     */
    public TaskList(Storage storage) {
        assert storage != null : "storage should never be null";
        this.storage = storage;
        this.items = new ArrayList<>(this.storage.load());
    }

    /**
     * Initialises an empty TaskList.
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a Task into the list.
     * @param task Task.
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    public Task getTask(int i) {
        assert i >= 1 && i <= items.size() : "Index should never be out of bounds";
        return items.get(i - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.items;
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return Number of Tasks in list.
     */
    public int size() {
        return this.getTasks().size();
    }

    /**
     * Deletes the Task at index i - 1, returns the deleted Task.
     *
     * @param i index of Task.
     * @return deleted Task.
     */
    public Task deleteTask(int i) {
        Task deleted = this.items.remove(i - 1);
        assert deleted != null : "Deleted task should never be null";
        return deleted;
    }

    /**
     * Mark Task in list as done.
     *
     * @param i index of Task.
     */
    public void markAsDone(int i) {
        assert i >= 1 && i <= items.size() : "Index should never be out of bounds";
        this.getTask(i).markAsDone();
    }

    public void markAsUndone(int i) {
        assert i >= 1 && i <= items.size() : "Index should never be out of bounds";
        this.getTask(i).markAsUndone();
    }

    /**
     * Find Tasks that match the given keyword.
     *
     * @param keyword Keyword.
     * @return A TaskList containing matching Tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList temp = new TaskList();

        for (Task t : this.items) {
            String[] words = t.getText().split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    temp.addTask(t);
                    break;
                }
            }
        }
        return temp;
    }

    public void insertTask(int i, Task task) {
        this.items.add(i - 1, task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append("    ").append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}
