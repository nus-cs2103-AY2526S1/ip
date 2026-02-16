package angus.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import angus.exception.AngusException;
import angus.ui.Ui;

/**
 * Represents a list of tasks and provides operations to manage tasks.
 * <p>
 * This class provides operations on tasks such as adding, deleting, marking and unmarking tasks.
 */
public class TaskList {
    private final List<Task> taskList;
    private final Ui ui;

    /**
     * Creates an empty TaskList.
     * @param ui The user interface to interact with the user.
     */
    public TaskList(Ui ui) {
        this.taskList = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Creates a TaskList with an existing list of tasks.
     * @param ui The user interface to interact with the user.
     * @param taskList The provided list of existing tasks to initialize with.
     */
    public TaskList(Ui ui, List<Task> taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public Task getTask(int taskNum) {
        return taskList.get(taskNum);
    }

    public int getSize() {
        return taskList.size();
    }

    public String getTaskList() throws AngusException {
        if (taskList.isEmpty()) {
            throw new AngusException("Your task list is empty!");
        }
        return ui.printTaskList(this.taskList);
    }

    /**
     * Marks a task as done.
     * @param taskNum Index of the task (0-th index).
     * @throws AngusException If the task index is invalid.
     */
    public String markTask(int taskNum) throws AngusException {
        assert taskList != null : "taskList should not be null";
        if (taskNum >= taskList.toArray().length) {
            throw new AngusException("Task does not exist!"
                    + Ui.LINE_BREAK
                    + "Usage: mark [task number]");
        }
        Task curTask = taskList.get(taskNum);
        boolean result = curTask.markDone();
        return ui.printMarkTask(result, curTask);
    }

    /**
     * Marks a task as not complete (unmarks a task).
     * @param taskNum Index of the task(0-th index)
     * @throws AngusException If the task index is invalid.
     */
    public String unmarkTask(int taskNum) throws AngusException {
        assert taskList != null : "taskList should not be null";
        if (taskNum >= taskList.toArray().length) {
            throw new AngusException("Task does not exist!"
                    + Ui.LINE_BREAK
                    + "Usage: unmark [task number]");
        }
        Task curTask = taskList.get(taskNum);
        boolean result = curTask.markNotDone();
        return ui.printUnmarkTask(result, curTask);
    }

    /**
     * Adds a new ToDo task to the list.
     * @param todoName The name of the ToDo.
     */
    public String addTodo(String todoName) {
        assert taskList != null : "taskList should not be null";
        ToDo newTodo = new ToDo(todoName);
        taskList.add(newTodo);
        return ui.printAddTodo(newTodo, taskList.size());
    }

    /**
     * Adds a new Deadline to the list.
     * @param deadlineName The name of the deadline.
     * @param endDate The deadline's end date.
     */
    public String addDeadline(String deadlineName, LocalDate endDate) {
        assert taskList != null : "taskList should not be null";
        Deadline newDeadline = new Deadline(deadlineName, endDate);
        taskList.add(newDeadline);
        return ui.printAddDeadline(newDeadline, taskList.size());
    }

    /**
     * Adds a new Event to the list.
     * @param eventName The name of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public String addEvent(String eventName, LocalDate startDate, LocalDate endDate) {
        assert taskList != null : "taskList should not be null";
        Event newEvent = new Event(eventName, startDate, endDate);
        taskList.add(newEvent);
        return ui.printAddEvent(newEvent, taskList.size());
    }

    /**
     * Deletes an event from the list.
     * @param taskNum Index of the task to be deleted (0-th index)
     * @throws AngusException If the task index does not exist.
     */
    public String deleteTask(int taskNum) throws AngusException {
        assert taskList != null : "taskList should not be null";
        if (taskNum >= taskList.size()) {
            throw new AngusException("Task does not exist!"
                    + Ui.LINE_BREAK
                    + "Usage: delete [task number]");
        }
        Task removedTask = taskList.get(taskNum);
        taskList.remove(taskNum);
        return ui.printDeleteTask(removedTask, taskList.size());
    }

    /**
     * Finds the tasks that has the word filter in its description.
     * @param filter The word to filter the list of tasks.
     * @throws AngusException If the filtered tasks has no tasks.
     */
    public String findTask(String filter) throws AngusException {
        assert taskList != null : "taskList should not be null";
        List<Task> filteredTasks = this.taskList.stream()
                .filter(t -> t.getDescription().contains(filter))
                .collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            throw new AngusException("There is no task with the matching keyword!");
        }
        return ui.printFilteredTasks(filteredTasks);
    }

    /**
     * Sorts the list based on either by deadlines or events, and return the result
     * @param filter The type of task to sort by.
     * @return The result from sorting the list.
     * @throws AngusException If trying to sort an empty list or sort todo.
     */
    public String getSortedList(String filter) throws AngusException {
        if (taskList.isEmpty()) {
            throw new AngusException("Your task list is empty!");
        }
        switch (filter) {
        case "todo":
            throw new AngusException("Todo cannot be sorted!");
        case "event":
            List<Task> eventList = taskList.stream()
                    .filter(t -> t instanceof Event)
                    .toList();
            if (eventList.isEmpty()) {
                throw new AngusException("There is no event task in your list!");
            }
            return ui.printSortedEventList(eventList);
        case "deadline":
            List<Task> deadlineList = taskList.stream()
                    .filter(t -> t instanceof Deadline)
                    .toList();
            if (deadlineList.isEmpty()) {
                throw new AngusException("There is no deadline task in your list!");
            }
            return ui.printSortedDeadlineList(deadlineList);
        default:
            throw new AngusException("Unknown error occurred!");
        }
    }
}
