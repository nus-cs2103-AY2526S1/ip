package beebong.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

// Import Commands
import beebong.command.AddDeadlineTaskCommand;
import beebong.command.AddEventTaskCommand;
import beebong.command.AddToDoTaskCommand;
import beebong.command.Command;
import beebong.command.CommandKeyword;
import beebong.command.DeleteTaskCommand;
import beebong.command.ExitCommand;
import beebong.command.FindTaskCommand;
import beebong.command.HelpCommand;
import beebong.command.ListAllTasksCommand;
import beebong.command.MarkTaskAsCommand;
import beebong.command.NullCommand;
// Import Exceptions
import beebong.exception.InvalidDateException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.exception.UnknownCommandException;
// Import Utils
import beebong.util.DateTimeUtil;

/**
 * Handles the parsing of user input into executable {@link Command}.
 */
public class Parser {
    /**
     * Parses a user input string into a {@link Command}.
     * <p>
     * This method identifies the command keyword in the user input and
     * then parses the user input into its respective command.
     * </p>
     *
     * @param input the string representing user input.
     * @return the parsed {@link Command}.
     * @throws UnknownCommandException If input is not a valid command.
     * @throws InvalidTaskDetailsException If input does not contain valid details for the command.
     * @throws InvalidDateException If input does not provide properly formatted dates.
     */
    public Command parseCommand(String input) throws UnknownCommandException,
            InvalidTaskDetailsException, InvalidDateException {
        // Check for Commands
        String[] commandParts = input.split(" ", 2);
        // Convert command into Command enum
        CommandKeyword command = CommandKeyword.stringToCommand(commandParts[0].toLowerCase());
        String params = (commandParts.length) > 1 ? commandParts[1] : null;

        switch (command) {
        // Exit
        case BYE:
            return new ExitCommand();
        // Help
        case HELP:
            return new HelpCommand();
        // List Tasks
        case LIST:
            // List all Tasks
            return new ListAllTasksCommand();
        // Add Tasks
        case TODO:
            // Add New ToDoTask
            return new AddToDoTaskCommand(validateParams(params, "Missing Task Details!"));
        case DEADLINE:
            // Parse params into task details
            try {
                String[] taskInfo = convertDetailsToDeadlineTaskInfo(validateParams(params, "Missing Task Details!"));
                assert taskInfo.length == 2 : "Deadline task must have exactly 2 parts (desc and date)";
                LocalDateTime deadline = DateTimeUtil.parseDateTime(taskInfo[1]);
                // Add New Deadline Task
                return new AddDeadlineTaskCommand(taskInfo[0], deadline);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        case EVENT:
            // Parse params into task details
            try {
                String[] taskInfo = convertDetailsToEventTaskInfo(validateParams(params, "Missing Task Details!"));
                assert taskInfo.length == 3 : "Deadline task must have exactly 3 parts (desc, start date, end date)";
                LocalDateTime startDate = DateTimeUtil.parseDateTime(taskInfo[1]);
                LocalDateTime endDate = DateTimeUtil.parseDateTime(taskInfo[2]);
                // Make sure startDate <= endDate
                if (startDate.isAfter(endDate)) {
                    throw new InvalidDateException("Invalid Dates Provided! Start Date cannot be after End Date!");
                }
                // Add New Event Task
                return new AddEventTaskCommand(taskInfo[0], startDate, endDate);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        // Mark Tasks
        case MARK:
        case UNMARK:
            return new MarkTaskAsCommand(parseTaskIndex(
                    validateParams(params, "You forgot the task number!")),
                    command == CommandKeyword.MARK);
        // Delete Tasks
        case DELETE:
            return new DeleteTaskCommand(parseTaskIndex(
                    validateParams(params, "You forgot the task number!")));
        // Find Tasks
        case FIND:
            // Find Tasks with keyword
            return new FindTaskCommand(validateParams(params, "You forgot the task number!"));
        // Unknown Commands
        default:
            return new NullCommand();
        }
    }

    private String validateParams(String params, String errorMsg) {
        // Check if params were provided
        if (params == null || params.isEmpty()) {
            throw new InvalidTaskDetailsException(errorMsg);
        }

        return params;
    }

    private int parseTaskIndex(String params) throws InvalidTaskDetailsException {
        try {
            int taskNum = Integer.parseInt(params) - 1;
            if (taskNum < 0) {
                throw new InvalidTaskDetailsException("Task number must be positive!");
            }
            return taskNum;
        } catch (NumberFormatException e) {
            throw new InvalidTaskDetailsException("That task number doesn’t exist. Try a real one!");
        }
    }

    private String[] convertDetailsToDeadlineTaskInfo(String details) throws InvalidTaskDetailsException {
        // e.g. "return book /by Sunday"
        String[] taskInfo = details.split(" /by ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (taskInfo.length != 2) {
            throw new InvalidTaskDetailsException("Invalid Task Details for Deadline Task!");
        }
        return taskInfo;
    }

    private String[] convertDetailsToEventTaskInfo(String details) throws InvalidTaskDetailsException {
        // e.g. "project meeting /from Mon 2pm /to 4pm"
        String[] result = new String[] {"", "", ""}; // name, from, to
        // Split string based on /from
        String[] temp = details.split(" /from ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) {
            throw new InvalidTaskDetailsException("Invalid Task Details for Event Task!");
        }
        result[0] = temp[0];
        // Split string based on /to
        temp = temp[1].split(" /to ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) {
            throw new InvalidTaskDetailsException("Invalid Task Details for Event Task!");
        }
        result[1] = temp[0];
        result[2] = temp[1];
        return result;
    }
}
