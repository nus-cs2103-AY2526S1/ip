package hermione.parsers;

import hermione.commands.Command;
import hermione.commands.DeadlineCommand;
import hermione.commands.DeleteCommand;
import hermione.commands.EventCommand;
import hermione.commands.ExitCommand;
import hermione.commands.FindCommand;
import hermione.commands.FixedDurationTaskCommand;
import hermione.commands.HelpCommand;
import hermione.commands.ListCommand;
import hermione.commands.MarkCommand;
import hermione.commands.ToDoCommand;
import hermione.commands.UnmarkCommand;
import hermione.exceptions.InvalidCommandException;
import hermione.storage.TaskStorage;

/**
 * Parses user input commands and arguments to create Command objects.
 */
public class CommandParser {

    /**
     * Parses the command and argument to create the appropriate Command object.
     * This method uses a switch statement to determine the type of command based on the input strings.
     * It creates and returns a specific Command object corresponding to the command type.
     * If the command is not recognized, it throws an InvalidCommandException.
     *
     * @param command  Command string to parse.
     * @param argument Argument string associated with the command.
     * @param storage  TaskStorage instance used to manage tasks.
     * @return Command object corresponding to the parsed command.
     * @throws InvalidCommandException If the command is not recognized.
     */
    public static Command parse(String command, String argument, TaskStorage storage) {
        return switch (command.toLowerCase()) {
            case "deadline", "dl" -> new DeadlineCommand(storage, argument);
            case "todo", "t" -> new ToDoCommand(storage, argument);
            case "event", "e" -> new EventCommand(storage, argument);
            case "fixed", "fi" -> new FixedDurationTaskCommand(storage, argument);
            case "delete", "d" -> new DeleteCommand(storage, argument);
            case "mark", "m" -> new MarkCommand(storage, argument);
            case "unmark", "um" -> new UnmarkCommand(storage, argument);
            case "list", "l" -> new ListCommand(storage, argument);
            case "find", "fd" -> new FindCommand(storage, argument);
            case "bye", "b" -> new ExitCommand(storage, argument);
            case "help", "h" -> new HelpCommand(storage, argument);
            default -> throw new InvalidCommandException(command);
        };
    }
}
