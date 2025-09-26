package winnie.parser;

import winnie.command.*;
import winnie.exception.WinnieException;
import winnie.exception.EmptyDescriptionException;
import winnie.exception.MissingTimeException;

/**
 * Parses user input into commands.
 */
public class Parser {

    private static final int MARK_COMMAND_LENGTH = 4;
    private static final int UNMARK_COMMAND_LENGTH = 6;
    private static final int DELETE_COMMAND_LENGTH = 6;
    private static final int TODO_COMMAND_LENGTH = 4;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int FIND_COMMAND_LENGTH = 4;
    private static final int SNOOZE_COMMAND_LENGTH = 6;

    /**
     * Parses the user input into a command.
     *
     * @param fullCommand The full user input command.
     * @return The parsed Command object.
     * @throws WinnieException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws WinnieException {
        assert fullCommand != null : "Command string cannot be null";
        String commandWord = getCommandWord(fullCommand);
        CommandEnum command = CommandEnum.fromString(commandWord);

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return parseMarkCommand(fullCommand);
        case UNMARK:
            return parseUnmarkCommand(fullCommand);
        case DELETE:
            return parseDeleteCommand(fullCommand);
        case TODO:
            return parseTodoCommand(fullCommand);
        case DEADLINE:
            return parseDeadlineCommand(fullCommand);
        case EVENT:
            return parseEventCommand(fullCommand);
        case FIND:
            return parseFindCommand(fullCommand);
        case SNOOZE:
            return parseSnoozeCommand(fullCommand);
        default:
        case UNKNOWN:
            return new UnknownCommand();
        }
    }

    private static String getCommandWord(String fullCommand) {
        String[] parts = fullCommand.trim().split("\\s+");
        return parts[0].toLowerCase();
    }

    private static Command parseMarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("mark")) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        String numberStr = commandInput.substring(MARK_COMMAND_LENGTH + 1).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseUnmarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("unmark")) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        String numberStr = commandInput.substring(UNMARK_COMMAND_LENGTH + 1).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseDeleteCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("delete")) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        String numberStr = commandInput.substring(DELETE_COMMAND_LENGTH + 1).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseTodoCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("todo")) {
            throw new EmptyDescriptionException("todo");
        }
        String description = commandInput.substring(TODO_COMMAND_LENGTH).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new TodoCommand(description);
    }

    private static Command parseDeadlineCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("deadline")) {
            throw new EmptyDescriptionException("deadline");
        }

        String args = commandInput.substring(DEADLINE_COMMAND_LENGTH).trim();
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        int byIndex = args.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingTimeException("deadline", "/by time");
        }

        String description = args.substring(0, byIndex).trim();
        String by = args.substring(byIndex + 3).trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (by.isEmpty()) {
            throw new MissingTimeException("deadline", "time value");
        }

        return new DeadlineCommand(description, by);
    }

    private static Command parseEventCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("event")) {
            throw new EmptyDescriptionException("event");
        }

        String args = commandInput.substring(EVENT_COMMAND_LENGTH).trim();
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new MissingTimeException("event", "/from and /to times");
        }

        String description = args.substring(0, fromIndex).trim();
        String from = args.substring(fromIndex + 5, toIndex).trim();
        String to = args.substring(toIndex + 3).trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new MissingTimeException("event", "start and end times");
        }

        return new EventCommand(description, from, to);
    }

    private static Command parseFindCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("find")) {
            throw new WinnieException("Please specify a keyword to search for. Example: find book");
        }

        String keyword = commandInput.substring(FIND_COMMAND_LENGTH).trim();
        if (keyword.isEmpty()) {
            throw new WinnieException("Please specify a keyword to search for. Example: find book");
        }

        return new FindCommand(keyword);
    }

    private static Command parseSnoozeCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("snooze")) {
            throw new WinnieException("Please specify a task number and snooze time. "
                    + "Example: snooze 1 2023-12-25 18:00");
        }

        String args = commandInput.substring(SNOOZE_COMMAND_LENGTH).trim();
        if (args.isEmpty()) {
            throw new WinnieException("Please specify a task number and snooze time. "
                    + "Example: snooze 1 2023-12-25 18:00");
        }

        String[] parts = args.split("\\s+", 2);
        if (parts.length < 2) {
            throw new WinnieException("Please specify both task number and snooze time. "
                    + "Example: snooze 1 2023-12-25 18:00");
        }

        try {
            int taskNumber = Integer.parseInt(parts[0]);
            String snoozeUntil = parts[1];
            return new SnoozeCommand(taskNumber, snoozeUntil);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + parts[0]);
        }
    }
}
