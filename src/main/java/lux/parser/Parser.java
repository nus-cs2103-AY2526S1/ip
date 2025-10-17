package lux.parser;

import lux.commands.AliasCommand;
import lux.commands.ByeCommand;
import lux.commands.Command;
import lux.commands.DeadlineCommand;
import lux.commands.DeleteCommand;
import lux.commands.EventCommand;
import lux.commands.FindCommand;
import lux.commands.ListCommand;
import lux.commands.MarkCommand;
import lux.commands.TodoCommand;
import lux.commands.UnmarkCommand;
import lux.data.AliasList;
import lux.exception.LuxException;

/**
 * Parses raw user input strings into {@link lux.commands.Command} objects.
 *
 * <p>The parser supports alias expansion via {@link lux.data.AliasList} and
 * maps the first token of the input to a specific Command subclass. It does
 * not execute commands; callers should invoke {@code execute} on the
 * returned Command.
 */
public class Parser {

    /**
     * Parse a user input string into a concrete Command instance.
     *
     * @param fullCommand raw user input (may include arguments)
     * @param aliases     alias mappings used to expand the command word
     * @return a Command ready to be executed by the application
     * @throws LuxException if the command is unknown or required arguments are
     *                      missing
     */
    public static Command parse(String fullCommand, AliasList aliases) throws LuxException {
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String commandWord = aliases.process(parts[0]);
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "alias":
            return new AliasCommand(arguments);
        case "list":
            return new ListCommand();
        case "todo":
            assert !arguments.isEmpty();
            return new TodoCommand(arguments);
        case "deadline":
            assert !arguments.isEmpty();
            return new DeadlineCommand(arguments);
        case "event":
            assert !arguments.isEmpty();
            return new EventCommand(arguments);
        case "mark":
            assert !arguments.isEmpty();
            return new MarkCommand(arguments);
        case "unmark":
            assert !arguments.isEmpty();
            return new UnmarkCommand(arguments);
        case "delete":
            assert !arguments.isEmpty();
            return new DeleteCommand(arguments);
        case "bye":
            return new ByeCommand();
        case "find":
            assert !arguments.isEmpty();
            return new FindCommand(arguments);
        default:
            throw new LuxException("Unknown command: " + commandWord);
        }
    }
}
