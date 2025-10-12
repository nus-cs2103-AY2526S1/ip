package com.oadultradeepfield.smartotter.command;

import java.util.List;
import java.util.Optional;

import com.oadultradeepfield.smartotter.task.Task;

/**
 * Context passed to commands, containing the current task list. Provides methods to access and
 * query tasks in various ways.
 */
public record CommandContext(List<Task> tasks) {
    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> tasks() {
        return tasks;
    }

    /**
     * Returns a task by index, if present.
     *
     * @param index the zero-based index of the task
     * @return an Optional containing the task if found, empty otherwise
     */
    public Optional<Task> getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return Optional.empty();
        }
        return Optional.of(tasks.get(index));
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the context.
     *
     * @return the total number of tasks
     */
    public int taskCount() {
        return tasks.size();
    }

    /**
     * Returns a list of tasks scheduled for today.
     *
     * @return a filtered list containing only today's tasks
     */
    public List<Task> listToday() {
        return tasks.stream().filter(Task::isToday).toList();
    }

    /**
     * Returns a list of tasks containing the given keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return a filtered list of tasks containing the keyword
     */
    public List<Task> findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
            .filter(task -> task.toString().toLowerCase().contains(lowerKeyword))
            .toList();
    }
}
