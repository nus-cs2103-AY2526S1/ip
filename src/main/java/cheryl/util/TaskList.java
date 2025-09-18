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
     * Deletes the task at the given index.
     *
     * @param index Index of the task to delete
     * @throws IndexOutOfBoundsException If the index is invalid
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "deleteTask : index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Index of the task to mark
     * @throws IndexOutOfBoundsException If the index is invalid
     */
    public void markTask(int index) {
        Task t = tasks.get(index);
        boolean before = t.isDone();
        t.mark();

        assert t.isDone() : "markTask must change status to done";
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index Index of the task to unmark
     * @throws IndexOutOfBoundsException If the index is invalid
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
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
