package minhgpt.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for all operations done on the list of tasks.
 */
public class TaskList {
    /** The list of tasks itself. */
    private ArrayList<Task> tasks;

    /**
     * Initialises the task list with 'initialTasks'.
     */
    public TaskList(ArrayList<Task> initialTasks) {
        assert (initialTasks != null);

        tasks = new ArrayList<>(initialTasks);
    }

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * @return Number of tasks in list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * @return Task at index 'index' in list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds 'task' to list.
     *
     * @return The recently added task.
     */
    public Task add(Task task) {
        assert (task != null);

        tasks.add(task);
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Marks task with index 'index' in list.
     *
     * @return The recently marked task.
     */
    public Task mark(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmarks task with index 'index' in list.
     *
     * @return The recently unmarked task.
     */
    public Task unmark(int index) {
        tasks.get(index).markAsUndone();
        return tasks.get(index);
    }

    /**
     * Deletes task with index 'index' in list.
     *
     * @return The recently deleted task.
     */
    public Task delete(int index) {
        Task toBeDeleted = tasks.get(index);
        tasks.remove(index);
        return toBeDeleted;
    }

    /**
     * @return Input commands to re-create this list of tasks.
     */
    public String toCommands() {
        return tasks
                .stream()
                .map(Task::toCommands)
                .flatMap(List::stream)
                .reduce("", (cmds1, cmds2) -> cmds1 + cmds2 + "\n");
    }

    /**
     * @return A new TaskList that has its tasks sorted
     *         according to the date in Task::getSortingDate().
     */
    public TaskList sort() {
        TaskList sorted = new TaskList(tasks);
        sorted.tasks.sort((task1, task2) -> task1.getSortingDate().compareTo(task2.getSortingDate()));
        return sorted;
    }
}
