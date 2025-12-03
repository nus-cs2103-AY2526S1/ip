package aqua.command.parser;

import aqua.command.AddDeadlineCommand;
import aqua.command.AddEventCommand;
import aqua.command.AddTodoCommand;
import aqua.command.Command;
import aqua.command.DeleteCommand;
import aqua.command.ExitCommand;
import aqua.command.FindCommand;
import aqua.command.ListCommand;
import aqua.command.MarkDoneCommand;
import aqua.command.MarkNotDoneCommand;
import aqua.command.SetPriorityCommand;
import aqua.exception.InvalidCommandException;
import aqua.exception.StorageException;

/**
 * Parses user input into commands.
 */
public class CommandParser {
    /**
     * Parses command given a input string.
     *
     * @param input The user's input
     * @return Command specified by the first word of input
     * @throws InvalidCommandException If the command is not recognized
     * @throws StorageException If there is an error with storage
     */
    public static Command parse(String input) throws InvalidCommandException, StorageException {
        String[] parts = input.split("\\s+", 2);
        assert parts.length == 2 : "Input should contains 2 parts";
        String command = parts.length > 0 ? parts[0].toLowerCase() : "";
        String commandArgs = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "todo" -> {
            return new AddTodoCommand(commandArgs);
        }
        case "deadline" -> {
            return new AddDeadlineCommand(commandArgs);
        }
        case "event" -> {
            return new AddEventCommand(commandArgs);
        }
        case "mark" -> {
            return new MarkDoneCommand(commandArgs);
        }
        case "unmark" -> {
            return new MarkNotDoneCommand(commandArgs);
        }
        case "list" -> {
            return new ListCommand();
        }
        case "delete" -> {
            return new DeleteCommand(commandArgs);
        }
        case "bye" -> {
            return new ExitCommand();
        }
        case "find" -> {
            return new FindCommand(commandArgs);
        }
        case "priority" -> {
            return new SetPriorityCommand(commandArgs);
        }
        default -> throw new InvalidCommandException("Sorry! I don't understand what you mean.");
        }
    }
}
