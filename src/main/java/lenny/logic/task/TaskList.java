package lenny.logic.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks with operations to add, remove, and modify tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a TaskList with existing tasks.
     *
     * @param initial Existing list of tasks.
     */
    public TaskList(ArrayList<Task> initial) {
        this.list = new ArrayList<>(initial);
    }

    /**
     * Returns the list of tasks.
     *
     * @return All tasks in this TaskList.
     */
    public ArrayList<Task> asList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    /**
     * Retrieves the task at the given index (1-based).
     *
     * @param oneBasedIndex Index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > list.size()) {
            throw new IndexOutOfBoundsException("Index " + oneBasedIndex + " out of range (1-" + list.size() + ")");
        }
        return list.get(oneBasedIndex - 1);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes the task at the given index (1-based).
     *
     * @param oneBasedIndex Index of the task to remove (1-based).
     *
     */
    public Task delete(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > list.size()) {
            throw new IndexOutOfBoundsException("Index " + oneBasedIndex + " out of range (1-" + list.size() + ")");
        }
        return list.remove(oneBasedIndex - 1);
    }

    /**
     * Marks the task at the given index (1-based).
     *
     * @param oneBasedIndex Index of the task to remove (1-based).
     *
     */
    public void mark(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > list.size()) {
            throw new IndexOutOfBoundsException("Index " + oneBasedIndex + " out of range (1-" + list.size() + ")");
        }
        Task t = list.get(oneBasedIndex - 1);
        t.mark();
    }

    /**
     * Unmarks the task at the given index (1-based).
     *
     * @param oneBasedIndex Index of the task to remove (1-based).
     *
     */
    public void unmark(int oneBasedIndex) {
        if (oneBasedIndex < 1 || oneBasedIndex > list.size()) {
            throw new IndexOutOfBoundsException("Index " + oneBasedIndex + " out of range (1-" + list.size() + ")");
        }
        Task t = list.get(oneBasedIndex - 1);
        t.unmark();
    }

    /**
     * Prints the current tasks in the list.
     *
     */
    public String show() {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            response += ((i + 1) + ". " + list.get(i) + "\n");
            response += ("This task has priority level: " + list.get(i).getPriority() + "\n");
        }
        return response;
    }

    /**
     * Prints the current tasks in the list that matches the priority.
     *
     */
    public String showPriority(int priority) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list with priority " + priority + ":\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPriority() == priority) {
                response.append((i + 1)).append(". ").append(list.get(i)).append("\n");
            }
        }
        return response.toString();
    }
}
