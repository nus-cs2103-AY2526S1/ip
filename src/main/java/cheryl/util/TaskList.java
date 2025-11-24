package cheryl.util;

import cheryl.task.Deadline;
import cheryl.task.Event;
import cheryl.task.Task;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * Provides methods to manipulate the list such as adding, deleting, or marking tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        int before = tasks.size();
        tasks.add(task);
        assert tasks.size() == before + 1 : "addTask must increase size by 1";
        assert tasks.contains(task) : "addTask must result in the list containing new task";
    }

    /**
     * Deletes the task at the given 1-based index.
     *
     * @param index Index of the task to delete (1-based)
     * @return The removed Task
     * @throws IndexOutOfBoundsException If the index is invalid
     */
    public Task deleteTask(int index) {
        int adjustedIndex = index - 1; // shift from 1-based to 0-based
        if (adjustedIndex < 0 || adjustedIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return tasks.remove(adjustedIndex);
    }

    /**
     * Returns the task at the given 1-based index.
     *
     * @param index Index of the task (1-based)
     * @return The Task at the given index
     */
    public Task getTask(int index) {
        int adjustedIndex = index - 1;
        if (adjustedIndex < 0 || adjustedIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return tasks.get(adjustedIndex);
    }

    /**
     * Marks the task at the given 1-based index as done.
     *
     * @param index Index of the task to mark (1-based)
     */
    public void markTask(int index) {
        int adjustedIndex = index - 1;
        Task t = tasks.get(adjustedIndex);
        t.mark();
        assert t.isDone() : "markTask must change status to done";
    }

    /**
     * Marks the task at the given 1-based index as not done.
     *
     * @param index Index of the task to unmark (1-based)
     */
    public void unmarkTask(int index) {
        int adjustedIndex = index - 1;
        tasks.get(adjustedIndex).unmark();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns tasks that match the given date.
     * - Deadlines with matching due date
     * - Events where start or end date equals the given date
     *
     * @param date The date to match
     * @return List of matching tasks
     */
    public List<Task> getTasksForDate(LocalDate date) {
        return tasks.stream()
                .filter(t -> {
                    if (t instanceof Deadline) {
                        return ((Deadline) t).getDueDate().equals(date);
                    } else if (t instanceof Event) {
                        Event e = (Event) t;
                        return e.getFrom().equals(date) || e.getTo().equals(date);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
