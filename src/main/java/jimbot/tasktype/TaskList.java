package jimbot.tasktype;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jimbot.exception.NoSuchTaskException;
import jimbot.exception.TaskLimitException;

/**
 * Represents a list of tasks stored by Jimbot.
 * Provides methods to add, delete, retrieve, and search tasks.
 */
public class TaskList {
    protected List<Task> listOfTasks;

    /**
     * Constructs a new empty TaskList when there is no data stored.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks loaded from storage.
     *
     * @param loadTasks List of tasks to load.
     */
    public TaskList(List<Task> loadTasks) {
        this.listOfTasks = loadTasks;
    }

    /**
     * Returns the list of tasks as a List<Task>.
     *
     * @return List of tasks stored in this TaskList.
     */
    public List<Task> getTaskList() {
        return listOfTasks;
    }

    /**
     * Adds a given task to the list.
     *
     * @param task Task to be added.
     * @throws TaskLimitException If the current task count exceeds 99.
     */
    public void addToList(Task task) throws TaskLimitException {
        if (listOfTasks.size() >= 99) {
            throw new TaskLimitException();
        } else {
            listOfTasks.add(task);
        }
    }

    /**
     * Retrieves a task from the list by index.
     *
     * @param index Index of task to be retrieved (starting from 0).
     * @return Task at specified index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Removes a specific task from the list.
     *
     * @param task Task to be removed.
     */
    public void deleteFromList(Task task) {
        listOfTasks.remove(task);
    }

    /**
     * Returns the number of tasks currently stored in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getTaskCount() {
        return listOfTasks.size();
    }

    /**
     * Finds tasks in the list whose description contains the given string.
     *
     * @param description Description to match tasks against.
     * @return TaskList containing matching tasks.
     * @throws NoSuchTaskException If no tasks match the description.
     * */
    public TaskList findTasks(String description) throws NoSuchTaskException {
        List<Task> result = new ArrayList<>();

        if (description.isEmpty()) {
            throw new NoSuchTaskException();
        }

        for (Task task : listOfTasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getDescription().contains(description)) {
                    result.add(task);
                }
            } else if (task instanceof Event event) {
                if (event.getDescription().contains(description)) {
                    result.add(task);
                }
            } else if (task instanceof ToDo todo) {
                if (todo.getDescription().contains(description)) {
                    result.add(task);
                }
            }
        }

        if (result.isEmpty()) {
            throw new NoSuchTaskException();
        } else {
            return new TaskList(result);
        }
    }

    /**
     * Find tasks that occur on a specific date.
     *
     * @param date Date to match tasks against.
     * @return TaskList containing tasks matching the given date.
     * @throws NoSuchTaskException If no tasks match the date.
     * */
    public TaskList findTasksAtDate(LocalDate date) throws NoSuchTaskException {
        List<Task> result = new ArrayList<>();

        for (Task task : listOfTasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getDate().equals(date)) {
                    result.add(task);
                }
            } else if (task instanceof Event event) {
                if ((event.getFrom().equals(date) || date.isAfter(event.getFrom()))
                        && (event.getTo().equals(date) || date.isBefore(event.getTo()))) {
                    result.add(task);
                }
            }
        }

        if (result.isEmpty()) {
            throw new NoSuchTaskException();
        } else {
            return new TaskList(result);
        }
    }
}
