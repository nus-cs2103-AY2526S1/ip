package david.parser;

import david.command.Command;
import david.command.CommandHistory;
import david.command.DeadlineCommand;
import david.command.DeleteCommand;
import david.command.EventCommand;
import david.command.ExitCommand;
import david.command.FindCommand;
import david.command.ListCommand;
import david.command.MarkCommand;
import david.command.TodoCommand;
import david.command.UndoCommand;
import david.exception.DavidException;
import david.exception.NoCommandException;

/**
 * Processes the command string and decides which command to execute.
 */
public class Parser {
    /**
     * Processes the command string and decides which command to execute.
     *
     * @param fullCommand Command string.
     * @return Command.
     * @throws DavidException If command does not exist in the given list of commands.
     */
    public static Command parse(String fullCommand, CommandHistory history) throws DavidException {
        assert fullCommand!= null : "Full command should never be null";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String args;

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            args = (parts.length < 2 ? "" : parts[1]);
            return new MarkCommand(args);
        case "deadline":
            args = (parts.length < 2 ? "" : parts[1]);
            return new DeadlineCommand(args);
        case "event":
            args = (parts.length < 2 ? "" : parts[1]);
            return new EventCommand(args);
        case "todo":
            args = (parts.length < 2 ? "" : parts[1]);
            return new TodoCommand(args);
        case "delete":
            args = (parts.length < 2 ? "" : parts[1]);
            return new DeleteCommand(args);
        case "find":
            args = (parts.length < 2 ? "" : parts[1]);
            return new FindCommand(args);
        case "undo":
            return new UndoCommand(history);
        case "bye":
            return new ExitCommand();
        default:
            throw new NoCommandException("Eh idk what you saying bro, use one of the commands");
        }
    }
}
