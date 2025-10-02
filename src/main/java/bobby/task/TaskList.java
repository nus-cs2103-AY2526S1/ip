package bobby.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Manages a list of Task objects, providing operations to add, delete, retrieve, and search tasks.
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
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the task to delete.
     * @return The deleted Task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return The Task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of all tasks.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword Keyword to search for.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                found.add(task);
            }
        }
        return found;
    }

    /**
     * Returns a list of tasks scheduled for the given date.
     * Includes Deadlines due on that date and Events occurring on that date.
     * ToDos are not included as they have no date.
     *
     * @param date The date to filter tasks by.
     * @return List of tasks scheduled for the date.
     */
    public ArrayList<Task> getTasksForDate(LocalDate date) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().toLocalDate().equals(date)) {
                    result.add(task);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                LocalDate from = e.getFrom().toLocalDate();
                LocalDate to = e.getTo().toLocalDate();
                if ((date.isEqual(from) || date.isAfter(from)) && (date.isEqual(to) || date.isBefore(to))) {
                    result.add(task);
                }
            }
        }
        return result;
    }

    /**
     * Checks if a duplicate of the given task exists in the list.
     *
     * @param task Task to check for duplication.
     * @return true if a duplicate exists, false otherwise.
     */
    public boolean containsDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }
}