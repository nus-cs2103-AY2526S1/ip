package steven.parser;

import steven.command.AddDeadlineTaskCommand;
import steven.command.AddEventTaskCommand;
import steven.command.AddFixedDurationTaskCommand;
import steven.command.AddToDoTaskCommand;
import steven.command.Command;
import steven.command.DeleteTaskCommand;
import steven.command.FindCommand;
import steven.command.ListTasksCommand;
import steven.command.MarkTaskCommand;
import steven.command.UnknownCommand;
import steven.command.UnmarkTaskCommand;

/**
 * Parses raw user input and returns the corresponding Command.
 * <p>
 * Supports commands like "todo", "deadline", "event", "fixed-duration",
 * "mark", "unmark", "delete", "list", and "find". Any unrecognized
 * command returns an UnknownCommand.
 * </p>
 */
public class Parser {

    /**
     * Parses the given user input string and returns the appropriate Command.
     *
     * @param input the raw user input string
     * @return the Command corresponding to the input
     */
    public Command parse(String input) {
        String command = input.split(" ")[0].toLowerCase();
        return switch (command) {
        case "todo" -> new AddToDoTaskCommand(input);
        case "deadline" -> new AddDeadlineTaskCommand(input);
        case "event" -> new AddEventTaskCommand(input);
        case "fixed-duration" -> new AddFixedDurationTaskCommand(input);
        case "mark" -> new MarkTaskCommand(input);
        case "unmark" -> new UnmarkTaskCommand(input);
        case "delete" -> new DeleteTaskCommand(input);
        case "list" -> new ListTasksCommand();
        case "find" -> new FindCommand(input);
        default -> new UnknownCommand();
        };
    }
}

