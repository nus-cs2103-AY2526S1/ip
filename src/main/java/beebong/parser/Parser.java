package beebong.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

// Import Commands
import beebong.command.AddDeadlineTaskCommand;
import beebong.command.AddEventTaskCommand;
import beebong.command.AddToDoTaskCommand;
import beebong.command.DeleteTaskCommand;
import beebong.command.FindTaskCommand;
import beebong.command.MarkTaskAsCommand;
import beebong.command.CommandKeyword;
import beebong.command.Command;
import beebong.command.ExitCommand;
import beebong.command.HelpCommand;
import beebong.command.ListAllTasksCommand;
import beebong.command.NullCommand;
// Import Exceptions
import beebong.exception.BBongException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.exception.InvalidDateException;
// Import Utils
import beebong.util.DateTimeUtil;

public class Parser {
    public Command parseCommand(String input) throws BBongException {
        // Check for Commands
        String[] commandParts = input.split(" ", 2);
        CommandKeyword command;

        // Convert command into Command enum
        command = CommandKeyword.stringToCommand(commandParts[0].toLowerCase());
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
        // Mark Tasks
        case MARK:
        case UNMARK:
            // Check if params were provided
            if (params == null) {
                throw new InvalidTaskDetailsException("You forgot the task number!");
            }

            // Parse params into taskNum
            try {
                int taskNum = Integer.parseInt(params) - 1;
                // Mark the task as complete or incomplete
                return new MarkTaskAsCommand(taskNum, command == CommandKeyword.MARK);
            } catch (NumberFormatException e) {
                throw new InvalidTaskDetailsException("That task number doesn’t exist. Try a real one!");
            }
        // Add Tasks
        case TODO:
            // Check if details is empty
            if (params == null || params.isEmpty()) {
                throw new InvalidTaskDetailsException("Missing Task Details!");
            }

            // Add New ToDoTask
            return new AddToDoTaskCommand(params);
        case DEADLINE:
            // Check if details is empty
            if (params == null || params.isEmpty()) {
                throw new InvalidTaskDetailsException("Missing Task Details!");
            }

            // Parse params into task details
            try {
                String[] taskInfo = convertDetailsToDeadlineTaskInfo(params);
                LocalDateTime deadline = DateTimeUtil.parseDateTime(taskInfo[1]);
                // Add New Deadline Task
                return new AddDeadlineTaskCommand(taskInfo[0], deadline);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        case EVENT:
            // Check if details is empty
            if (params == null || params.isEmpty()) {
                throw new InvalidTaskDetailsException("Missing Task Details!");
            }

            // Parse params into task details
            try {
                String[] taskInfo = convertDetailsToEventTaskInfo(params);
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
        // Delete Tasks
        case DELETE:
            // Check if params were provided
            if (params == null) {
                throw new InvalidTaskDetailsException("You forgot the task number!");
            }

            // Parse params into taskNum
            try {
                int taskNum = Integer.parseInt(params) - 1;
                // Delete Task
                return new DeleteTaskCommand(taskNum);
            } catch (NumberFormatException e) {
                throw new InvalidTaskDetailsException("That task number doesn’t exist. Try a real one!");
            }
        // Find Tasks
        case FIND:
            // Check if params were provided
            if (params == null) {
                throw new InvalidTaskDetailsException("You forgot the task number!");
            }

            // Find Tasks with keyword
            return new FindTaskCommand(params);
        // Unknown Commands
        default:
            return new NullCommand();
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
