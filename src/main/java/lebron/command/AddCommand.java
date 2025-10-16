package lebron.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private final String commandWord;
    private final String arguments;

    /**
     * Constructor for AddCommand class.
     *
     * @param arguments The arguments provided with the add command.
     */
    public AddCommand(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    /**
     * Determines the type of task to be added based on the command arguments.
     *
     * @return A string representing the task type.
     */
    public String taskType() {
        if (commandWord.equalsIgnoreCase(Constants.TODO_COMMAND)) {
            return Constants.TASK_TYPE_T;
        } else if (commandWord.equalsIgnoreCase(Constants.DEADLINE_COMMAND)) {
            return Constants.TASK_TYPE_D;
        } else if (commandWord.equalsIgnoreCase(Constants.EVENT_COMMAND)) {
            return Constants.TASK_TYPE_E;
        } else {
            return "";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = createTask();
        taskList.addTask(task);
        return formatSuccessMessage(task, taskList.getSize());
    }

    /**
     * Creates a task based on the command arguments.
     *
     * @return The created Task object.
     * @throws LeBronException If there is an error in task creation.
     */
    private Task createTask() throws LeBronException {
        switch (taskType()) {
        case Constants.TASK_TYPE_T:
            return createToDoTask();
        case Constants.TASK_TYPE_D:
            return createDeadlineTask();
        case Constants.TASK_TYPE_E:
            return createEventTask();
        default:
            throw new LeBronException(Constants.UNKNOWN_COMMAND_ERROR);
        }
    }

    /**
     * Creates a ToDo task.
     *
     * @return The created ToDo task.
     * @throws LeBronException If the description is empty.
     */
    private Task createToDoTask() throws LeBronException {
        String description = arguments.trim();
        validateNotEmpty(description, Constants.TODO_EMPTY_ERROR);
        return new ToDo(description);
    }

    /**
     * Creates a Deadline task.
     *
     * @return The created Deadline task.
     * @throws LeBronException If there is an error in parsing the date or if the description is empty.
     */
    private Task createDeadlineTask() throws LeBronException {
        // Check if /by delimiter exists
        if (!arguments.contains(Constants.BY_DELIMITER)) {
            throw new LeBronException(Constants.DEADLINE_BY_EMPTY_ERROR);
        }

        String deadlineDescription = arguments.substring(0, arguments.indexOf(Constants.BY_DELIMITER)).trim();
        String by = arguments.substring(arguments.indexOf(Constants.BY_DELIMITER)
                + Constants.BY_DELIMITER.length()).trim();
        validateNotEmpty(deadlineDescription, Constants.DEADLINE_EMPTY_ERROR);
        validateNotEmpty(by, Constants.DEADLINE_BY_EMPTY_ERROR);

        try {
            LocalDate date = LocalDate.parse(by);
            return new Deadline(deadlineDescription, date);
        } catch (DateTimeParseException e) {
            throw new LeBronException(Constants.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Creates an Event task.
     *
     * @return The created Event task.
     * @throws LeBronException If there is an error in parsing the dates or if any field is empty.
     */
    private Task createEventTask() throws LeBronException {
        // Check if required delimiters exist
        if (!arguments.contains(Constants.FROM_DELIMITER) || !arguments.contains(Constants.TO_DELIMITER)) {
            throw new LeBronException(Constants.EVENT_FORMAT_ERROR);
        }

        String eventDescription = arguments.substring(0, arguments.indexOf(Constants.FROM_DELIMITER)).trim();
        String start = arguments.substring(arguments.indexOf(Constants.FROM_DELIMITER)
                + Constants.FROM_DELIMITER.length(), arguments.indexOf(Constants.TO_DELIMITER)).trim();
        String end = arguments.substring(arguments.indexOf(Constants.TO_DELIMITER)
                + Constants.TO_DELIMITER.length()).trim();
        validateNotEmpty(eventDescription, Constants.EVENT_EMPTY_ERROR);
        validateNotEmpty(start, Constants.EVENT_START_EMPTY_ERROR);
        validateNotEmpty(end, Constants.EVENT_END_EMPTY_ERROR);

        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return new Event(eventDescription, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new LeBronException(Constants.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Validates that a string is not empty.
     *
     * @param value        The string to validate.
     * @param errorMessage The error message to throw if the string is empty.
     * @throws LeBronException If the string is empty.
     */
    private void validateNotEmpty(String value, String errorMessage) throws LeBronException {
        if (value.isEmpty()) {
            throw new LeBronException(errorMessage);
        }
    }

    /**
     * Formats the success message after adding a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks in the list.
     * @return A formatted success message.
     */
    private String formatSuccessMessage(Task task, int totalTasks) {
        return String.format(Constants.SUCCESS_MESSAGE_FORMAT, task, totalTasks);
    }
}
