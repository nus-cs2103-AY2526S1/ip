package lyra.util;

import lyra.command.Command;
import lyra.command.ExitCommand;
import lyra.command.HelpCommand;
import lyra.command.ListCommand;
import lyra.command.AddTodoCommand;
import lyra.command.AddDeadlineCommand;
import lyra.command.AddEventCommand;
import lyra.command.MarkCommand;
import lyra.command.UnmarkCommand;
import lyra.command.DeleteCommand;
import lyra.command.FindCommand;
import lyra.command.SortCommand;
import lyra.exception.LyraException;

/**
 * Utility class for parsing user input commands and converting them to Command objects.
 * Handles the parsing of various command types including task management commands
 * and provides appropriate error messages for invalid inputs.
 */
public class Parser {
    
    /**
     * Parses a full command string and returns the appropriate Command object.
     * Supports commands: bye, list, todo, deadline, event, mark, unmark, delete, find, sort.
     *
     * @param fullCommand the complete command string entered by the user
     * @return a Command object representing the parsed command
     * @throws LyraException if the command is empty, unrecognized, or has invalid arguments
     */
    public static Command parse(String fullCommand) throws LyraException {
        assert fullCommand != null : "fullCommand must not be null";
        String trimmedCommand = fullCommand.trim();
        
        if (trimmedCommand.isEmpty()) {
            throw new LyraException("Please enter a command. Type 'help' to see all available commands, or try: list, todo, deadline, event, mark, unmark, delete, find, sort, bye.");
        }
        
        String[] parts = trimmedCommand.split(" ", 2);
        assert parts.length >= 1 : "split must produce at least one part";
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        
        switch (command) {
            case "bye":
                return new ExitCommand();
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return parseTodoCommand(arguments);
            case "deadline":
                return parseDeadlineCommand(arguments);
            case "event":
                return parseEventCommand(arguments);
            case "mark":
                return parseMarkCommand(arguments);
            case "unmark":
                return parseUnmarkCommand(arguments);
            case "delete":
                return parseDeleteCommand(arguments);
            case "find":
                return parseFindCommand(arguments);
            case "sort":
                return parseSortCommand(arguments);
            default:
                throw new LyraException("Sorry, I couldn't recognize that command. Type 'help' to see all available commands, or try: list, todo, deadline, event, mark, unmark, delete, find, sort, bye.");
        }
    }
    
    /**
     * Parses arguments for a todo command and creates an AddTodoCommand.
     * Requires a non-empty description.
     *
     * @param arguments the arguments string for the todo command
     * @return an AddTodoCommand with the parsed description
     * @throws LyraException if the description is empty
     */
    private static Command parseTodoCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, a todo needs a description. Try: todo <description>");
        }
        return new AddTodoCommand(arguments.trim());
    }
    
    /**
     * Parses arguments for a deadline command and creates an AddDeadlineCommand.
     * Requires description and /by date separated by '/by'.
     *
     * @param arguments the arguments string for the deadline command
     * @return an AddDeadlineCommand with the parsed description and deadline
     * @throws LyraException if the arguments are missing or malformed
     */
    private static Command parseDeadlineCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        }
        
        String[] split = arguments.split("/by", 2);
        String description = split[0].trim();
        String by = split.length > 1 ? split[1].trim() : "";
        
        if (description.isEmpty() || by.isEmpty()) {
            throw new LyraException("Sorry, a deadline needs a description and '/by'. Try: deadline <description> /by <when>");
        }
        
        return new AddDeadlineCommand(description, by);
    }
    
    /**
     * Parses arguments for an event command and creates an AddEventCommand.
     * Requires description, /from start date, and /to end date.
     *
     * @param arguments the arguments string for the event command
     * @return an AddEventCommand with the parsed description and time period
     * @throws LyraException if the arguments are missing or malformed
     */
    private static Command parseEventCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        }
        
        String[] splitFrom = arguments.split("/from", 2);
        String description = splitFrom[0].trim();
        String from = "";
        String to = "";
        
        if (splitFrom.length > 1) {
            String[] splitTo = splitFrom[1].split("/to", 2);
            from = splitTo[0].trim();
            if (splitTo.length > 1) {
                to = splitTo[1].trim();
            }
        }
        
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new LyraException("Sorry, an event needs a description and times. Try: event <description> /from <start> /to <end>");
        }
        
        return new AddEventCommand(description, from, to);
    }
    
    /**
     * Parses arguments for a mark command and creates a MarkCommand.
     * Requires a valid task number (converted to 0-based index).
     *
     * @param arguments the arguments string for the mark command
     * @return a MarkCommand with the parsed task index
     * @throws LyraException if the task number is missing or invalid
     */
    private static Command parseMarkCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Invalid task number.");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }
    
    /**
     * Parses arguments for an unmark command and creates an UnmarkCommand.
     * Requires a valid task number (converted to 0-based index).
     *
     * @param arguments the arguments string for the unmark command
     * @return an UnmarkCommand with the parsed task index
     * @throws LyraException if the task number is missing or invalid
     */
    private static Command parseUnmarkCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Invalid task number.");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }
    
    /**
     * Parses arguments for a delete command and creates a DeleteCommand.
     * Requires a valid task number (converted to 0-based index).
     *
     * @param arguments the arguments string for the delete command
     * @return a DeleteCommand with the parsed task index
     * @throws LyraException if the task number is missing or invalid
     */
    private static Command parseDeleteCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Please specify a task number to delete. Try: delete <task_number>");
        }
        
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new LyraException("Invalid task number.");
        }
    }

    /**
     * Parses arguments for a find command and creates a FindCommand.
     * Requires a non-empty search keyword.
     *
     * @param arguments the arguments string for the find command
     * @return a FindCommand with the parsed search keyword
     * @throws LyraException if the keyword is empty
     */
    private static Command parseFindCommand(String arguments) throws LyraException {
        assert arguments != null : "arguments must not be null";
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Please provide a keyword to find. Try: find <keyword>");
        }
        return new FindCommand(arguments.trim());
    }

    private static Command parseSortCommand(String arguments) throws LyraException {
        if (arguments.trim().isEmpty()) {
            throw new LyraException("Please specify a sort criteria. Available options: description, deadline, event, type, status");
        }
        
        String criteria = arguments.trim().toLowerCase();
        String[] validCriteria = {"description", "deadline", "event", "type", "status"};
        
        for (String valid : validCriteria) {
            if (criteria.equals(valid)) {
                return new SortCommand(criteria);
            }
        }
        
        throw new LyraException("Invalid sort criteria. Available options: description, deadline, event, type, status");
    }
}
