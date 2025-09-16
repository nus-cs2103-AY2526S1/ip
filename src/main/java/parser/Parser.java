package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UndoCommand;
import commands.UnmarkCommand;
import exception.RainyException;

/**
 * Parses user input strings into executable commands.
 */
public class Parser {

    /**
     * Parses a full user command into a corresponding command
     *
     * @param fullCommand the user input string
     * @return parsed command
     * @throws RainyException if the command is invalid or missing arguments
     */
    public static Command parse(String fullCommand) throws RainyException {
        String[] words = fullCommand.trim().split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
        case "bye": return new ByeCommand();
        case "list": return new ListCommand();
        case "mark": return parseMark(words);
        case "unmark": return parseUnmark(words);
        case "todo": return parseTodo(words);
        case "deadline": return parseDeadline(words);
        case "event": return parseEvent(words);
        case "delete": return parseDelete(words);
        case "find": return parseFind(words);
        case "undo": return new UndoCommand(null);
        default: throw new RainyException("oh no!!! idk what that means... :-(");
        }
    }

    private static Command parseMark(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify which task number to mark.");
        }
        int index = Integer.parseInt(words[1]) - 1;
        return new MarkCommand(index);
    }

    private static Command parseUnmark(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify which task number to unmark.");
        }
        int index = Integer.parseInt(words[1]) - 1;
        return new UnmarkCommand(index);
    }

    private static Command parseTodo(String[] words) throws RainyException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RainyException("oh no!!! the description of a todo cannot be empty.");
        }
        return new AddTodoCommand(words[1].trim());
    }

    private static Command parseDeadline(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify task and deadline.");
        }
        String[] parts = words[1].split(" /by ");
        if (parts.length < 2) {
            throw new RainyException("oh no!!! please specify task and deadline.");
        }
        return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private static Command parseEvent(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify task from when to when.");
        }
        String[] parts = words[1].split(" /from | /to ");
        if (parts.length < 3) {
            throw new RainyException("oh no!!! please specify task from when to when.");
        }
        return new AddEventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    private static Command parseDelete(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify which task number to delete.");
        }
        int index = Integer.parseInt(words[1]) - 1;
        return new DeleteCommand(index);
    }

    private static Command parseFind(String[] words) throws RainyException {
        if (words.length < 2) {
            throw new RainyException("oh no!!! please specify a keyword to search.");
        }
        return new FindCommand(words[1].trim());
    }
}
