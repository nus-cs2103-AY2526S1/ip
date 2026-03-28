package cattis;

import java.util.Scanner;

import cattis.command.AddDeadlineTaskCommand;
import cattis.command.AddEventTaskCommand;
import cattis.command.AddTodoTaskCommand;
import cattis.command.CalendarCommand;
import cattis.command.Command;
import cattis.command.DeleteTaskCommand;
import cattis.command.ExitCommand;
import cattis.command.FindTaskCommand;
import cattis.command.ListCommand;
import cattis.command.MarkCommand;
import cattis.command.UnmarkCommand;
import cattis.command.ViewScheduleCommand;
import cattis.exception.CattisException;
import cattis.exception.CattisInvalidCommandException;
import cattis.exception.CattisParseException;

/**
 * Represents the object that parses the input string into {@code Command}.
 */
public class Parser {
    private static final int DEFAULT_TASK_INDEX = -1;

    /**
     * Converts plain string to {@code Command} object in order
     * to be used later in the {@code Main} class.
     *
     * @param payload the string content to parse
     * @return Command the result command
     * @throws CattisException for parsing error
     */
    public Command parse(String payload) throws CattisException {
        Scanner scanner = new Scanner(payload);
        String command = scanner.next();
        int taskIndex = DEFAULT_TASK_INDEX;
        String remainingInput;
        switch (command) {
        case "calendar":
            return new CalendarCommand();
        case "view":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new ViewScheduleCommand(remainingInput);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            if (!scanner.hasNextInt()) {
                throw new CattisParseException(command);
            }
            taskIndex = scanner.nextInt();
            assert taskIndex != DEFAULT_TASK_INDEX;
            return new MarkCommand(taskIndex);
        case "unmark":
            if (!scanner.hasNextInt()) {
                throw new CattisParseException(command);
            }
            taskIndex = scanner.nextInt();
            return new UnmarkCommand(taskIndex);
        case "delete":
            if (!scanner.hasNextInt()) {
                throw new CattisParseException(command);
            }
            taskIndex = scanner.nextInt();
            assert taskIndex != DEFAULT_TASK_INDEX;
            return new DeleteTaskCommand(taskIndex);
        case "todo":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new AddTodoTaskCommand(remainingInput);
        case "find":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new FindTaskCommand(remainingInput);
        case "deadline":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new AddDeadlineTaskCommand(remainingInput);
        case "event":
            if (!scanner.hasNextLine()) {
                throw new CattisParseException(command);
            }
            remainingInput = scanner.nextLine();
            return new AddEventTaskCommand(remainingInput);
        default:
            throw new CattisInvalidCommandException();
        }
    }
}
