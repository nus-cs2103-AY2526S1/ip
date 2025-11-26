package aries.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aries.AriesException;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Task> tasks;
    private HashSet<String> keys = new HashSet<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        assert tasks == null : "Task list should be null before initialization.";
        this.tasks = new ArrayList<>();
        this.keys = new HashSet<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) throws AriesException {
        assert t != null : "Task cannot be null";
        int sizeBeforeAdd = tasks.size();
        String key = t.getKey();

        if (keys.contains(key)) {
            throw new AriesException("Duplicate task detected: " + key);
        }
        keys.add(key);
        tasks.add(t);

        assert tasks.size() == sizeBeforeAdd + 1 : "Task list size should increase by 1 after adding a task";
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        int sizeBeforeRemove = tasks.size();
        Task removedTask = tasks.remove(index);
        keys.remove(removedTask.getKey());
        assert tasks.size() == sizeBeforeRemove - 1 : "Task list size should decrease by 1 after removing a task";
    }

    /**
     * Rebuilds the keys set from the current tasks in the list.
     * This is useful after loading tasks from storage to ensure no duplicates exist.
     */
    public void rebuildKeys() throws AriesException {
        if (keys == null) {
            keys = new HashSet<>();
        } else {
            keys.clear();
        }

        for (Task t : tasks) {
            String key = t.getKey();
            if (keys.contains(key)) {
                throw new AriesException("Duplicate task detected during key rebuild: " + key);
            }
            keys.add(key);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks in the list that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTaskByKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        return sb.toString().trim();
    }

}
