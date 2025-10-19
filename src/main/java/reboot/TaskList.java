package reboot;

import java.util.ArrayList;
import java.util.List;

import reboot.task.Deadline;
import reboot.task.Event;
import reboot.task.Task;
import reboot.task.Todo;
import reboot.task.recurring.RecurringDeadline;
import reboot.task.recurring.RecurringEvent;
import reboot.task.recurring.RecurringTodo;

/**
 * Represents a tasklist that will store all the user tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new tasklist that can be used to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a tasklist from a list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a specified task to the tasklist.
     * @param t task to be added to tasklist.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Clears the tasklist.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Removes a specified task to the tasklist.
     * @param t task to be removed from tasklist.
     */
    public void remove(Task t) {
        assert t != null : "Task out of bounds";
        tasks.remove(t);
    }

    /**
     * Returns the size of the tasklist.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task in the specified index.
     * @param index Index of the specified task in tasklist
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the whole tasklist.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Returns true if tasklist is empty and false if otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns true if specified index is out of range of tasklist
     * and false if otherwise.
     * @param n Index of input task.
     */
    public boolean isOutOfRange(int n) {
        return n > tasks.size();
    }

    /**
     * Finds tasks in tasklist which contains the specified keyword.
     * @param keyword Keyword to search for in the tasklist.
     */
    public TaskList findTasks(String keyword) {
        TaskList results = new TaskList();

        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(t);
            }
        }

        return results;
    }

    /**
     * Updates recurring tasks to their updated deadlines.
     */
    public void updateRecurringTasks() {
        for (Task task : tasks) {
            if (task instanceof Todo) {
                updateRecurringTodo((Todo) task);
            } else if (task instanceof Deadline) {
                updateRecurringDeadline((Deadline) task);
            } else {
                updateRecurringEvent((Event) task);
            }
        }
    }
    private static void updateRecurringTodo(Todo t) {
        if (t instanceof RecurringTodo) {
            ((RecurringTodo) t).updateDateIfOverdue();
        }
    }

    private static void updateRecurringDeadline(Deadline d) {
        if (d instanceof RecurringDeadline) {
            if (d.getDueDate() != null) {
                ((RecurringDeadline) d).updateDateIfOverdue();
            } else {
                ((RecurringDeadline) d).updateDateTimeIfOverdue();
            }
        }
    }

    private static void updateRecurringEvent(Event e) {
        if (e instanceof RecurringEvent) {
            if (e.getStartDate() != null) {
                ((RecurringEvent) e).updateDateIfOverdue();
            } else {
                ((RecurringEvent) e).updateDateTimeIfOverdue();
            }
        }
    }
}
