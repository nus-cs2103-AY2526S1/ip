package geegar.task;

import java.time.LocalDate;
import java.util.ArrayList;

import geegar.exception.InvalidTaskNumberException;

/**
 * Represents a list of tasks and provides operations to manage the lsit
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a tasklist from an existing list of tasks
     *
     * @param tasks the list of tasks to initialise with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the task list
     * @param task the task ot be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task at the specified index
     * Task index should be -1 of the user's input
     *
     * @param index index of the task to be deleted
     * @return the task that was deleted
     * @throws InvalidTaskNumberException if index is out of bounds
     */
    public Task delete(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        return this.tasks.remove(index);
    }

    /**
     * Getter for the specified task based on index
     *
     * @param index the index of the task
     * @return the task at that given index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the task at the specified index as done
     *
     * @param index the index of the task to mark
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public void markTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done
     *
     * @param index the index of the task to unmark
     * @throws InvalidTaskNumberException if the index is out of bounds
     */
    public void unmarkTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(index + 1));
        }
        tasks.get(index).markNotDone();
    }

    /**
     * Returns the number of tasks in the list currently
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns true if the task list is empty, otherwise it returns false
     *
     * @return if the list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the list of tasks that occur on a given date
     * This checks for deadline and Events tasks to see if there is any matching date
     *
     * @param date the date to filter the tasks by
     * @return the list of tasks occurring on that date
     */
    public ArrayList<Task> showTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.by.toLocalDate().equals(date)) {
                    tasksOnDate.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.from.toLocalDate().equals(date) || e.to.toLocalDate().equals(date)) {
                    tasksOnDate.add(e);
                }
            }
        }
        return tasksOnDate;
    }

    /**
     * Returns a list of task that contains the given keyword
     * @param keyword
     * @return
     */
    public ArrayList<Task> showTasksOnKeyword(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return matchedTasks;
        }
        String reference = keyword.trim().toLowerCase();
        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(reference)) {
                matchedTasks.add(t);
            }
        }
        return matchedTasks;
    }
}
