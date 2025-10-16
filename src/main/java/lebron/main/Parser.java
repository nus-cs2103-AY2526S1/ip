package lebron.main;

import java.time.LocalDate;

import lebron.command.AddCommand;
import lebron.command.Command;
import lebron.command.DeleteCommand;
import lebron.command.ExitCommand;
import lebron.command.FindCommand;
import lebron.command.ListCommand;
import lebron.command.MarkCommand;
import lebron.command.UnmarkCommand;
import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.ToDo;

/**
 * The Parser class is responsible for parsing user input and data file lines
 * to create appropriate Command and Task objects.
 */
public class Parser {
    /**
     * Reads tasks from the data file for populating the task list.
     *
     * @param line A line from the data file representing a task.
     * @return A Task object created from the line; null if the line is invalid.
     */
    public static Task parseTask(String line) {
        String[] parts = splitTaskIfValid(line);
        String type = parts[0];
        boolean isDone = parts[1].equals(Constants.DONE_FLAG); // "1" means done, "0" means not done
        String description = parts[2];

        Task task = createTaskByType(type, description, parts);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Splits a task line into its components and validates the format.
     *
     * @param line A line from the data file representing a task.
     * @return An array of strings containing the task components.
     * @throws IllegalArgumentException If the task format is invalid.
     */
    private static String[] splitTaskIfValid(String line) {
        String[] parts = line.split(Constants.STORAGE_SEPARATOR);
        if (parts.length < 3) {
            throw new IllegalArgumentException(Constants.ERROR_INVALID_TASK_FORMAT);
        }
        return parts;
    }
    /**
     * Creates a Task object based on its type and other details.
     *
     * @param type        The type of task ("T", "D", or "E").
     * @param description The description of the task.
     * @param parts       The array of task components from the data file line.
     * @return A Task object corresponding to the type and details.
     * @throws IllegalArgumentException If the task type is invalid.
     */
    private static Task createTaskByType(String type, String description, String[] parts) {
        switch (type) {
        case Constants.TASK_TYPE_T:
            return new ToDo(description);
        case Constants.TASK_TYPE_D:
            return new Deadline(description, LocalDate.parse(parts[3]));
        case Constants.TASK_TYPE_E:
            return new Event(description, LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
        default:
            throw new IllegalArgumentException(Constants.ERROR_INVALID_TASK_FORMAT);
        }
    }

    /**
     *  Parses user input to create the appropriate Command object.
     *
     * @param fullCommand The full command input by the user.
     * @return A Command object corresponding to the user's command.
     * @throws LeBronException If the command is unrecognized or invalid.
     */
    public static Command parse(String fullCommand) throws LeBronException {
        assert !fullCommand.trim().isEmpty() : "User input should not be empty";
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        return createCommand(commandWord, arguments);
    }
    /**
     * Creates a Command object based on the command word and its arguments.
     *
     * @param commandWord The command word (e.g., "list", "mark", "todo").
     * @param arguments   The arguments associated with the command.
     * @return A Command object corresponding to the command word and arguments.
     * @throws LeBronException If the command word is unrecognized.
     */
    private static Command createCommand(String commandWord, String arguments) throws LeBronException {
        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo", "event", "deadline":
            return new AddCommand(commandWord, arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new LeBronException(Constants.ERROR_UNKNOWN_COMMAND);
        }
    }
}
