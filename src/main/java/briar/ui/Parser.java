package briar.ui;

import java.time.format.DateTimeParseException;

import briar.command.AddCommand;
import briar.command.Command;
import briar.command.DeleteCommand;
import briar.command.ExitCommand;
import briar.command.FindCommand;
import briar.command.HelpCommand;
import briar.command.ListCommand;
import briar.command.MarkCommand;
import briar.command.UnmarkCommand;
import briar.exception.BriarException;
import briar.exception.EmptyCommandException;
import briar.exception.InvalidCommandException;
import briar.exception.InvalidDateException;
import briar.exception.NonNumberException;
import briar.exception.WrongFormatException;
import briar.task.Task;

/**
 * Represents the parser to parse strings into commands for the chatbot Briar.
 */
public class Parser {

    /**
     * Converts the string command into a Command to be executed.
     * @param command String to be parsed and interpreted by the parser.
     * @return Command to be executed by the chatbot Briar.
     * @throws BriarException If the command cannot be parsed properly.
     */
    public static Command parse(String command) throws BriarException {
        assert command != null : "command should not be null";
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
            // No break as code either returns
        case "list":
            return new ListCommand();
            // No break as code either returns
        case "mark":
            return parseMarkCommand(splitCommand);
            // No break as code either returns or throws exception
        case "unmark":
            return parseUnmarkCommand(splitCommand);
            // No break as code either returns or throws exception
        case "todo":
            return parseTodoCommand(splitCommand, command);
            // No break as code either returns or throws exception
        case "deadline":
            return parseDeadlineCommand(splitCommand, command);
            // No break as code either returns or throws exception
        case "event":
            return parseEventCommand(splitCommand, command);
            // No break as code either returns or throws exception
        case "delete":
            return parseDeleteCommand(splitCommand);
            // No break as code either returns or throws exception
        case "find":
            return parseFindCommand(splitCommand);
        case "help":
            return new HelpCommand();
        default:
            throw new InvalidCommandException();
            // No break as code throws exception
        }
    }

    private static MarkCommand parseMarkCommand(String[] splitCommand)
            throws EmptyCommandException, NonNumberException {
        if (splitCommand.length <= 1) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        try {
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException exception) {
            throw new NonNumberException(splitCommand[0]);
        }
    }

    private static UnmarkCommand parseUnmarkCommand(String[] splitCommand)
            throws EmptyCommandException, NonNumberException {
        if (splitCommand.length <= 1) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        try {
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException exception) {
            throw new NonNumberException(splitCommand[0]);
        }
    }

    private static AddCommand parseTodoCommand(String[] splitCommand, String command)
            throws EmptyCommandException, WrongFormatException {
        if (splitCommand.length <= 1) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        String subCommand = command.substring(splitCommand[0].length() + 1);
        return new AddCommand(Task.createTask(Task.TaskType.TODO, subCommand));
    }

    private static AddCommand parseDeadlineCommand(String[] splitCommand, String command)
            throws EmptyCommandException, WrongFormatException, InvalidDateException {
        if (splitCommand.length <= 2) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        try {
            String subCommand = command.substring(splitCommand[0].length() + 1);
            return new AddCommand(Task.createTask(Task.TaskType.DEADLINE, subCommand));
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException();
        }
    }

    private static AddCommand parseEventCommand(String[] splitCommand, String command)
            throws EmptyCommandException, WrongFormatException {
        if (splitCommand.length <= 3) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        String subCommand = command.substring(splitCommand[0].length() + 1);
        return new AddCommand(Task.createTask(Task.TaskType.EVENT, subCommand));
    }

    private static DeleteCommand parseDeleteCommand(String[] splitCommand)
            throws EmptyCommandException, NonNumberException {
        if (splitCommand.length <= 1) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        try {
            return new DeleteCommand(Integer.parseInt(splitCommand[1]) - 1);
        } catch (NumberFormatException exception) {
            throw new NonNumberException(splitCommand[0]);
        }
    }

    private static FindCommand parseFindCommand(String[] splitCommand)
            throws EmptyCommandException, NonNumberException {
        if (splitCommand.length <= 1) {
            throw new EmptyCommandException(splitCommand[0]);
        }
        return new FindCommand(splitCommand[1]);
    }
}
