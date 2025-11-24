package cheryl.util;

import cheryl.command.AddDeadlineCommand;
import cheryl.command.AddEventCommand;
import cheryl.command.AddTodoCommand;
import cheryl.command.Command;
import cheryl.command.DeleteCommand;
import cheryl.command.ExitCommand;
import cheryl.command.FindCommand;
import cheryl.command.ListCommand;
import cheryl.command.MarkCommand;
import cheryl.command.ScheduleCommand;
import cheryl.command.UnmarkCommand;

/**
 * Parses user input strings into Command objects.
 * Responsible for determining which command the user wants to execute.
 */
public class Parser {

    /**
     * Parses the full command string into a specific Command object.
     *
     * @param fullCommand The full user input string.
     * @return A Command object corresponding to the user input.
     * @throws DukeException if the input is empty or the command is unknown.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand == null) {
            throw new DukeException("Command cannot be null.");
        }

        String trimmed = fullCommand.trim();
        if (trimmed.isEmpty()) {
            throw new DukeException("Command cannot be empty.");
        }

        // Split into first word (command) and the rest (arguments)
        String commandWord;
        String arguments;

        int firstSpace = indexOfFirstSpace(trimmed);
        if (firstSpace == -1) {
            commandWord = trimmed.toLowerCase();
            arguments  = "";
        } else {
            commandWord = trimmed.substring(0, firstSpace).toLowerCase();
            arguments   = trimmed.substring(firstSpace + 1).trim();
        }

        switch (commandWord) {
        case "bye":
        case "exit":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(arguments);

        case "unmark":
            return new UnmarkCommand(arguments);

        case "delete":
            return new DeleteCommand(arguments);

        case "find":
            return new FindCommand(arguments);

        case "todo":
            return new AddTodoCommand(arguments);

        case "deadline":
            return new AddDeadlineCommand(arguments);

        case "event":
            return new AddEventCommand(arguments);

        case "schedule":
            return new ScheduleCommand(arguments);

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int indexOfFirstSpace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}

