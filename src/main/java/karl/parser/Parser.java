package karl.parser;

import karl.command.AddDeadlineCommand;
import karl.command.AddEventCommand;
import karl.command.AddTodoCommand;
import karl.command.Command;
import karl.command.DeleteCommand;
import karl.command.ExitCommand;
import karl.command.FindCommand;
import karl.command.HelpCommand;
import karl.command.ListCommand;
import karl.command.MarkCommand;
import karl.command.UnmarkCommand;
import karl.exception.KarlException;

/**
 * Parses user input strings into corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the raw user input, determines the command
     * type and constructs the corresponding Command object.
     *
     * @param input The raw command line input from user.
     * @return The Command object representing the input.
     * @throws KarlException If the input does not match any known commands.
     */
    public static Command parse(String input) throws KarlException {
        // Parse input string and return a Command object accordingly
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            throw new KarlException("Karl didn’t understand that command 😅");
        }
    }
}
