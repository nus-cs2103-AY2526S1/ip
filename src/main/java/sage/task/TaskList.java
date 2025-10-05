package sage.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import sage.util.UiMessages;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     * @param tasks The ArrayList of Task objects to initialize the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list to initialize cannot be null";
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added cannot be null";
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * @param taskIndex The zero-based index of the task to be deleted.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        return tasks.remove(taskIndex);
    }

    /**
     * Retrieves a task from the list at the specified index.
     * @param taskIndex The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        return tasks.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Each task is numbered and displayed on a new line.
     * @return A string containing the numbered list of tasks.
     */
    public String getTasksAsString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty! Add some tasks to get started.";
        }
        StringBuilder sb = new StringBuilder(UiMessages.TASKS_LIST_HEADER);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks a task as done at the specified index.
     * @param taskIndex The zero-based index of the task to mark as done.
     */
    public void markTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Marks a task as not done at the specified index.
     * @param taskIndex The zero-based index of the task to mark as not done.
     */
    public void unmarkTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index is out of bounds";
        tasks.get(taskIndex).unmarkAsDone();
    }

    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a list of Deadline tasks that are due within the next 24 hours and are not done.
     * @return An ArrayList of upcoming Deadline tasks.
     */
    public ArrayList<Deadline> getUpcomingDeadlines() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursLater = now.plus(24, ChronoUnit.HOURS);

        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> !deadline.isDone() && deadline.getBy().isAfter(now) && deadline.getBy().isBefore(twentyFourHoursLater))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
