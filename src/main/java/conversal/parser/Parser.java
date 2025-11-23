package conversal.parser;

import conversal.command.ByeCommand;
import conversal.command.Command;
import conversal.command.DeadlineCommand;
import conversal.command.DeleteCommand;
import conversal.command.DoWithinCommand;
import conversal.command.EventCommand;
import conversal.command.ListCommand;
import conversal.command.MarkAsCompleteCommand;
import conversal.command.MarkAsIncompleteCommand;
import conversal.command.TodoCommand;
import conversal.exception.ConversalException;

/**
 * Deals with interpreting user input strings
 * and converting them into Command objects.
 *
 * The parser takes the given input and decides which command to instantiate.
 * If the input does not match any known command, a {@code ConversalException} is thrown.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input the raw input string entered by the user
     * @return the appropriate {@link Command} to execute
     * @throws ConversalException if the input does not match any known command
     */
    public static Command parse(String input) throws ConversalException {
        assert input != null : "input must not be null";
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkAsCompleteCommand(input);
        case "unmark":
            return new MarkAsIncompleteCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "do-within":
            return new DoWithinCommand(input);
        default:
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
