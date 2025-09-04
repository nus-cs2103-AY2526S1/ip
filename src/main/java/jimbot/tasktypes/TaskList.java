package jimbot.tasktypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jimbot.exceptions.NoSuchTaskException;
import jimbot.exceptions.TaskLimitException;

public class TaskList {
    protected List<Task> listOfTasks;

    /**
     * Default constructor when there is no data stored.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor used for when there is data to load.
     */
    public TaskList(List<Task> loadTasks) {
        this.listOfTasks = loadTasks;
    }

    /**
     * Returns the list of tasks as a List<Task>.
     */
    public List<Task> getTaskList() {
        return listOfTasks;
    }

    /**
     * Adds a given task to the list of tasks.
     *
     * @param task Task to be added.
     * @throws TaskLimitException If the current task count in the list is >= 100.
     */
    public void addToList(Task task) throws TaskLimitException {
        if (listOfTasks.size() >= 99) {
            throw new TaskLimitException();
        } else {
            listOfTasks.add(task);
        }
    }

    /**
     * Retrieves a specific task from the list of tasks.
     *
     * @param index Index of task to be retrieved, starting from 0.
     * @return Task from at given index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Removes a specific task from the list of tasks.
     *
     * @param task Task to be removed.
     */
    public void deleteFromList(Task task) {
        listOfTasks.remove(task);
    }

    /**
     * Returns the current number of tasks stored in the list.
     */
    public int getTaskCount() {
        return listOfTasks.size();
    }

    /**
     * Returns a list of tasks with description matching user input as a TaskList.
     *
     * @param description Description provided by user.
     * @return List of tasks whose description contains the given description.
     * @throws NoSuchTaskException If description does not match any task in the list.
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
     * Returns a list of tasks with date matching user input as a TaskList.
     *
     * @param date Date provided by user.
     * @return List of tasks whose date matches the given date.
     * @throws NoSuchTaskException If date does not match any tasks' date in the list.
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
