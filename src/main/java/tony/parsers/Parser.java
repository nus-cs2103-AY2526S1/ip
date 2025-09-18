package tony.parsers;

import tony.commands.Command;
import tony.commands.DeadlineCommand;
import tony.commands.DeleteCommand;
import tony.commands.EventCommand;
import tony.commands.ExitCommand;
import tony.commands.FindCommand;
import tony.commands.ListCommand;
import tony.commands.MarkCommand;
import tony.commands.ShowTasksOnDateCommand;
import tony.commands.ToDoCommand;
import tony.commands.UnmarkCommand;
import tony.exceptions.InvalidCommandException;
import tony.exceptions.TonyException;

/**
 * Processes the user input and decides which command to execute.
 */
public class Parser {

    /**
     * Processes the command string and decides which command to execute.
     * @param input The command given by the user.
     * @return The {@link Command} that corresponds to the input.
     * @throws TonyException If command does not exist in the given list of commands.
     */
    public static Command parse(String input) throws TonyException {
        String[] parts = input.trim().split(" ", 2);
        String keyword = parts[0];
        String args = (parts.length < 2) ? "" : parts[1];

        switch (keyword) {
        case "todo":
            return new ToDoCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "event":
            return new EventCommand(args);
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "show":
            return new ShowTasksOnDateCommand(args);
        case "find":
            return new FindCommand(args);
        case "bye":
            return new ExitCommand();
        default:
            throw new InvalidCommandException("Hey man, I don't know what you're saying.");
        }
    }
}
