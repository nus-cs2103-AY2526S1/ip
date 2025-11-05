package uxie.interfaces;

import java.util.ArrayList;
import java.util.List;

import uxie.exceptions.UxieIllegalOpException;
import uxie.tasks.Task;

/**
 * Represents a list of Tasks.
 *
 * @author junyan-k
 */
public class TaskList {

    /** List of tasks. */
    private List<Task> tasks;

    /**
     * Generates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Generates initialized TaskList.
     *
     * @param tasks initial list of Tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to end of list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task matching index from list.
     *
     * @param taskIndex index of task to delete (0-indexed)
     * @return description of deleted task
     * @throws UxieIllegalOpException taskIndex is out of bounds in list
     */
    public String deleteTask(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task removedTask = tasks.get(taskIndex);
        assert removedTask != null : "TaskList#deleteTask: removedTask is null";
        tasks.remove(taskIndex);
        return removedTask.getDesc();
    }

    /**
     * Find tasks from search String.
     * Tasks are selected if their description contains exactly the search String.
     *
     * @return list of indices matching selected Tasks.
     */
    public List<Integer> findContainingString(String search) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(search)) { // contains exactly
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Return task matching index.
     *
     * @param taskIndex index of task in list to return (0-indexed)
     * @return task matching index
     * @throws UxieIllegalOpException when taskIndex is out of bounds.
     */
    public Task getTask(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Returns size of list (no. of tasks).
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks task matching index as complete.
     *
     * @param taskIndex index of task to mark complete (0-indexed)
     * @return description of task marked complete.
     * @throws UxieIllegalOpException if task index is out of bounds or task is already complete.
     */
    public String markCompleted(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        assert t != null : "TaskList#deleteTask: task to mark complete is null";
        t.markCompleted();
        return t.getDesc();
    }

    /**
     * Marks task matching index as incomplete.
     *
     * @param taskIndex index of task to mark incomplete (0-indexed)
     * @return description of task marked incomplete.
     * @throws UxieIllegalOpException if task index is out of bounds or task is already incomplete.
     */
    public String markIncomplete(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        assert t != null : "TaskList#deleteTask: task to mark incomplete is null";
        t.markIncomplete();
        return t.getDesc();
    }

    /**
     * Tags task matching index with given String.
     *
     * @param taskIndex index of Task to tag (0-indexed)
     * @param tag String to tag Task with.
     * @return description of task tagged.
     * @throws UxieIllegalOpException when taskIndex is out of bounds.
     */
    public String tagTask(int taskIndex, String tag) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        assert t != null : "TaskList#tagTask: task to tag is null";
        t.setTag(tag);
        return t.getDesc();
    }
}
