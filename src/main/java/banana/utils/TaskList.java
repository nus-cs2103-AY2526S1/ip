package banana.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import banana.exceptions.BananaException;
import banana.task.Deadline;
import banana.task.Event;
import banana.task.Task;

/**
 * Manages a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "TaskList initialization failed: tasks array is null";
    }
    /**
     * Adds a new task to the task list.
     *
     * @param task    The task to add.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task to TaskList";
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete (0-based).
     * @return
     * @throws BananaException If the index is invalid.
     */
    public Task deleteTask(int index) throws BananaException {
        assert !tasks.isEmpty() : "Cannot delete from an empty TaskList";
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        } else {
            throw new BananaException("Invalid task number!");
        }
    }
    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks.
     */
    public String listTasks() {
        assert tasks != null : "TaskList is null when trying to list tasks";
        if (tasks.isEmpty()) {
            return "You have no tasks in your list.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index   The index of the task to mark as done (0-based).
     * @throws BananaException If the index is invalid.
     */
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public void markTask(int index) throws BananaException {
        //assert index >= 0 && index < tasks.size() : "Invalid index " + index + " for TaskList size " + tasks.size();
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (!task.isDone()) {
                task.markAsDone();
            }
        } else {
            throw new BananaException("Invalid task number!");
        }
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param index   The index of the task to unmark (0-based).
     * @throws BananaException If the index is invalid.
     */
    public void unmarkTask(int index) throws BananaException {
        //assert index >= 0 && index < tasks.size() : "Invalid index " + index + " for TaskList size " + tasks.size();
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.isDone()) {
                task.markAsNotDone();
            }
        } else {
            throw new BananaException("Invalid task number!");
        }
    }

    public Task getTask(int index) throws BananaException {
        assert index >= 0 && index < tasks.size() : "Invalid index " + index + " for TaskList size " + tasks.size();
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.isDone()) {
                task.markAsDone();
            }
        } else {
            throw new BananaException("Invalid task number!");
        }
        return tasks.get(index);
    }
    public ArrayList<Task> getAllTasks() {
        assert tasks != null : "TaskList is null when trying to get all tasks";
        return tasks;
    }
    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword for finding tasks cannot be null or empty";
        TaskList foundtasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundtasks.addTask(task);
            }
        }
        return foundtasks;
    }
    /**
     * Finds and returns a list of tasks that occur on the specified date.
     *
     * @param dateStr The date to search for in yyyy-mm-dd format.
     */
    public TaskList findTasksOnDate(String dateStr) {
        assert dateStr != null && !dateStr.isEmpty() : "Date string for finding tasks cannot be null or empty";
        TaskList foundtasks = new TaskList();
        for (Task task : tasks) {
            if (task instanceof banana.task.Deadline) {
                banana.task.Deadline deadline = (banana.task.Deadline) task;
                if (deadline.getBy().toLocalDate().toString().equals(dateStr)) {
                    foundtasks.addTask(task);
                }
            } else if (task instanceof banana.task.Event) {
                banana.task.Event event = (banana.task.Event) task;
                if (event.getFrom().toLocalDate().toString().equals(dateStr)
                        || event.getTo().toLocalDate().toString().equals(dateStr)) {
                    foundtasks.addTask(task);
                }
            }
        }
        return foundtasks;
    }
    /**
     * Sorts the tasks in the task list by their date/time.
     * Tasks without a date/time are pushed to the end of the list.
     */
    public void sortByDate() {
        tasks.sort(Comparator.comparing(task -> {
            if (task instanceof Deadline) {
                return ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                return ((Event) task).getFrom(); // Use start date for sorting
            }
            return LocalDateTime.MAX; // Push non-date tasks to the end
        }));
    }

    public int size() {
        return tasks.size();
    }
}
