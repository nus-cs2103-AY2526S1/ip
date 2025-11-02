package buddy.model;

import buddy.exception.BuddyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Mutable list of Tasks.
 */

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /**
     * Adds a task to the end of the list.
     */
    public void add(Task t) {
        assert t != null : "Cannot add null task";
        int before = tasks.size();
        tasks.add(t);
        assert tasks.size() == before + 1 : "TaskList size should increase by 1";
    }

    /**
     * Deletes the task at index.
     * Accounts for 1 based indexing used in input
     */

    public Task delete(int oneBasedIndex) throws BuddyException {
        int idx = oneBasedIndex - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new BuddyException("Invalid task index");
        }
        int before = tasks.size();
        Task removed = tasks.remove(oneBasedIndex - 1);
        assert tasks.size() == before - 1 : "TaskList size should decrease by 1";
        return removed;
    }

    /**
     * Returns a multi-line list of tasks (1-based).
     */

    public Task get(int oneBasedIndex) throws BuddyException {
        int idx = oneBasedIndex - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new BuddyException("Invalid task index");
        }
        return tasks.get(idx);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> toList() {
        return tasks;
    }

    public String listAsString() {
        if (tasks.isEmpty()) {
            return "Your list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /** Returns all tasks whose description contains the keyword. */
    public List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        assert keyword != null : "Search keyword cannot be null";
        List<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(k)) {
                matches.add(t);
            }
        }
        return matches;
    }

    /** Formats matches into a numbered list. */
    public String findAsString(String keyword) {
        List<Task> matches = find(keyword);
        if (matches.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
