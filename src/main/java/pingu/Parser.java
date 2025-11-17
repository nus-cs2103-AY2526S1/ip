package pingu;

import pingu.command.*;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and returns a corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user's full command string into a Command object.
     *
     * @param fullCommand The full input string from the user.
     * @return A Command object corresponding to the user's input.
     * @throws PinguException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws PinguException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        try {
            switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskIndex(parts));
            case "unmark":
                return new UnmarkCommand(parseTaskIndex(parts));
            case "delete":
                return new DeleteCommand(parseTaskIndex(parts));
            case "todo":
                return new AddCommand(parseTodo(parts));
            case "find":
                return new FindCommand(parseKeyword(parts));
            case "deadline":
                return new AddCommand(parseDeadline(parts));
            case "event":
                return new AddCommand(parseEvent(parts));
            default:
                throw new PinguException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new PinguException("Invalid date format. Please use d/M/yyyy HHmm (e.g., 2/12/2019 1800).");
        } catch (PinguException e) {
            throw e;
        }
    }
    
    private static int parseTaskIndex(String[] parts) throws PinguException {
        if (parts.length < 2) {
            throw new PinguException("Please specify the task number.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PinguException("Invalid task number. Please provide a valid number.");
        }
    }
    
    private static String parseKeyword(String[] parts) throws PinguException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PinguException("Please specify a keyword to search for.");
        }
        return parts[1].trim();
    }

    private static Task parseTodo(String[] parts) throws PinguException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PinguException("The description of a todo cannot be empty.");
        }
        return new Task(parts[1]);
    }

    private static Task parseDeadline(String[] parts) throws PinguException {
        if (parts.length < 2) {
            throw new PinguException("The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = parts[1].split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new PinguException("Invalid deadline format. Use 'deadline [description] /by [d/M/yyyy HHmm]'.");
        }
        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }
    
    private static Task parseEvent(String[] parts) throws PinguException {
        if (parts.length < 2) {
            throw new PinguException("The description of an event cannot be empty.");
        }
        String[] eventParts = parts[1].split(" /from | /to ");
        if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new PinguException("Invalid event format. Use 'event [description] /from [start] /to [end]'.");
        }
        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }
}
