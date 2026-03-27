package sunday;

import java.util.ArrayList;
import java.util.List;

import task.Task;

/**
 * Encapsulates the list of tasks and operations on them.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        assert initial != null : "initial list must not be null";
        this.taskList = new ArrayList<>(initial);
    }

    /**
     * Gets the task at a given index.
     *
     * @param i index (0-based)
     * @return the task
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * @return number of tasks in the list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Adds a task to the list and saves to storage.
     *
     * @param task    task to add
     * @param storage storage to persist changes
     */
    public void add(Task task, Storage storage) {
        assert task != null : "Task list must not be null";
        assert storage != null : "Storage must not be null";
        taskList.add(task);
        storage.save(this.taskList);
    }

    /**
     * Deletes a task at the given index and saves.
     *
     * @param index      index (0-based)
     * @param storage storage to persist changes
     * @return deleted task
     */
    public Task delete(int index, Storage storage) {
        assert storage != null : "Storage must not be null";
        Task deleted = taskList.remove(index);
        storage.save(this.taskList);
        return deleted;
    }

    /**
     * Inserts the given task at the specified 0-based {@code index}, shifting subsequent tasks to the right,
     * and persists the updated list via {@code storage}.
     *
     * <p>This is used by update operations to replace a task in place while preserving the original order.</p>
     *
     * @param index   position (0-based) at which to insert; must be between {@code 0} and {@code size()} inclusive
     * @param task    task to insert; must not be {@code null}
     * @param storage persistence component to save the updated list; must not be {@code null}
     * @throws IndexOutOfBoundsException if {@code index < 0} or {@code index > size()}
     */
    public void insertAt(int index, Task task, Storage storage) {
        assert storage != null : "Storage must not be null";
        taskList.add(index, task);
        storage.save(this.taskList);
    }

    /**
     * Marks/unmarks a task and saves.
     *
     * @param index      index (0-based)
     * @param done    true = done, false = undone
     * @param storage storage to persist changes
     */
    public void setAsDone(int index, boolean done, Storage storage) {
        assert storage != null : "Storage must not be null";
        if (done) {
            this.taskList.get(index).markAsDone();
        } else {
            this.taskList.get(index).markAsUndone();
        }
        storage.save(this.taskList);
    }

    /**
     * Finds all tasks whose description contains the given keyword (case-insensitive).
     *
     * @param keyword search string
     * @return matching tasks in their existing order
     */
    public List<Task> findByKeyword(String keyword) {
        String key = keyword == null ? "" : keyword.trim().toLowerCase();
        List<Task> matches = new ArrayList<>();

        if (key.isEmpty()) {
            return matches;
        }

        for (Task t : this.taskList) {
            String desc = t.getTaskName();
            if (desc != null && desc.toLowerCase().contains(key)) {
                matches.add(t);
            }
        }
        return matches;
    }
}
