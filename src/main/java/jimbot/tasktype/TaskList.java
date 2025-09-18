package jimbot.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jimbot.exception.EmptyListException;
import jimbot.exception.NoSuchTaskException;
import jimbot.exception.TaskLimitException;

/**
 * Represents a list of tasks stored by Jimbot.
 * Provides methods to add, delete, retrieve, and search tasks.
 *
 * @author limjimin-nus
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
        assert loadTasks != null : "Task list cannot be null";
        this.listOfTasks = loadTasks;
    }

    /**
     * Returns the list of tasks as a {@Link List<Task>}.
     *
     * @return List of tasks stored in this TaskList.
     */
    public List<Task> getTaskList() {
        assert listOfTasks != null : "Internal task list should never be null";
        sortTasks();

        return listOfTasks;
    }

    /**
     * Adds a given task to the list.
     *
     * @param task Task to be added.
     * @throws TaskLimitException If the current task count exceeds 99.
     */
    public void addToList(Task task) throws TaskLimitException {
        assert task != null : "Cannot add null task to TaskList";
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
        assert index >= 0 && index < listOfTasks.size() : "Task index out of bounds";
        return listOfTasks.get(index);
    }

    /**
     * Removes a specific task from the list.
     *
     * @param task Task to be removed.
     */
    public void deleteFromList(Task task) {
        assert task != null : "Cannot delete null task from TaskList";
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
     * Clears the task list of all tasks.
     *
     * @throws EmptyListException If list is already empty.
     */
    public void clearList() throws EmptyListException {
        if (listOfTasks.isEmpty()) {
            throw new EmptyListException("clear");
        }
        listOfTasks.clear();
    }

    /**
     * Finds tasks in the list whose description contains the given string.
     *
     * @param description Description to match tasks against.
     * @return TaskList containing matching tasks.
     * @throws NoSuchTaskException If no tasks match the description.
     * */
    public TaskList findTasks(String description) throws NoSuchTaskException {
        assert description != null : "Search description should not be null";
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
            for (Task t : result) {
                assert t.getDescription().contains(description)
                        : "Filtered task does not match search description";
            }
            return new TaskList(result);
        }
    }

    /**
     * Find tasks that occur on a specific date.
     *
     * @param date Date to match tasks against.
     * @return TaskList containing tasks matching the given date.
     * @throws EmptyListException If no tasks match the date.
     * */
    public TaskList findTasksAtDate(LocalDate date) throws EmptyListException {
        assert date != null : "Search date should not be null";
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
            if (date.equals(LocalDate.now())) {
                throw new EmptyListException("do today");
            } else {
                throw new EmptyListException("do that day");
            }
        } else {
            for (Task t : result) {
                assert t instanceof Deadline || t instanceof Event : "Unexpected task type in date search result";
            }
            return new TaskList(result);
        }
    }

    /**
     * Sorts the tasks in the list chronologically.
     * - Events are sorted by their start time.
     * - Deadlines are sorted by their due time.
     * - ToDos are placed after all dated tasks.
     */
    public void sortTasks() {
        listOfTasks.sort((t1, t2) -> {
            LocalDateTime dt1 = getTaskDateTime(t1);
            LocalDateTime dt2 = getTaskDateTime(t2);

            if (dt1 == null && dt2 == null) {
                return 0; // both are ToDos, keep insertion order
            } else if (dt1 == null) {
                return 1; // t1 is ToDo, push after t2
            } else if (dt2 == null) {
                return -1; // t2 is ToDo, push after t1
            } else {
                return dt1.compareTo(dt2); // compare actual dates
            }
        });
    }

    /**
     * Returns the date/time used for sorting the task.
     *
     * @param task Task to get date/time for
     * @return LocalDateTime for deadlines and events; null for ToDos
     */
    private LocalDateTime getTaskDateTime(Task task) {
        if (task instanceof Deadline deadline) {
            return deadline.getDateTime(); // should return LocalDateTime
        } else if (task instanceof Event event) {
            return event.getFromDateTime(); // start time
        } else {
            return null; // ToDos have no date/time
        }
    }
}
