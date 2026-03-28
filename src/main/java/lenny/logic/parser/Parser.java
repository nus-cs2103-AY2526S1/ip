package lenny.logic.parser;

import lenny.logic.command.AddCommand;
import lenny.logic.command.Command;
import lenny.logic.command.DeleteCommand;
import lenny.logic.command.ExitCommand;
import lenny.logic.command.FindCommand;
import lenny.logic.command.ListCommand;
import lenny.logic.command.MarkCommand;
import lenny.logic.command.PriorityCommand;
import lenny.logic.command.UnmarkCommand;
import lenny.logic.exception.LennyExceptions;
import lenny.logic.task.Deadline;
import lenny.logic.task.Event;
import lenny.logic.task.Todo;



/**
 * Parses user input commands into Task objects or program actions.
 */

public class Parser {

    /**
     * Parse the user input and return the response as a String.
     */
    public static Command parse(String fullCommand) throws LennyExceptions {
        String cleaned = fullCommand.trim().replaceAll("\\s+", " "); // collapse multiple spaces
        if (cleaned.isEmpty()) {
            throw new LennyExceptions("OOPS!!! Command cannot be empty.");
        }
        String[] parts = cleaned.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            Todo todo = parseTodo(fullCommand);
            return new AddCommand(todo);
        case "deadline":
            Deadline deadline = parseDeadline(fullCommand);
            return new AddCommand(deadline);
        case "event":
            Event event = parseEvent(fullCommand);
            return new AddCommand(event);
        case "delete":
            int i = parseIndex(fullCommand);
            return new DeleteCommand(i);
        case "mark":
            int j = parseIndex(fullCommand);
            return new MarkCommand(j);
        case "unmark":
            int k = parseIndex(fullCommand);
            return new UnmarkCommand(k);
        case "find":
            String s = parseKeyword(fullCommand);
            return new FindCommand(s);
        case "priority":
            int p = parseIndex(fullCommand);
            return new PriorityCommand(p);
        default:
            throw new LennyExceptions("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    // helper methods

    /**
     * Parses a todo command string into a Todo object.
     *
     * @param input Full user input string.
     * @return A Todo task.
     * @throws LennyExceptions If description is missing.
     */
    public static Todo parseTodo(String input) throws LennyExceptions {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LennyExceptions("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(parts[1].trim(), false);
    }

    /**
     * Parses a deadline command string into a Deadline object.
     *
     * @param input Full user input string containing /by.
     * @return A Deadline task.
     * @throws LennyExceptions If description or /by part is missing.
     */
    public static Deadline parseDeadline(String input) throws LennyExceptions {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2 || !parts[1].contains("/by")) {
            throw new LennyExceptions("OOPS!!! The description of a deadline must include /by.");
        }
        String rhs = parts[1];
        int byIdx = rhs.indexOf("/by");
        if (byIdx < 0 || byIdx + 3 >= rhs.length()) {
            throw new LennyExceptions("OOPS!!! Provide a date/time after /by.");
        }
        String name = rhs.substring(0, byIdx).trim();
        String by = rhs.substring(byIdx + 3).trim();
        return new Deadline(name, by, false);
    }

    /**
     * Parses an event command string into an Event object.
     *
     * @param input Full user input string containing /from and /to.
     * @return An Event task.
     * @throws LennyExceptions If parts are missing.
     */
    public static Event parseEvent(String input) throws LennyExceptions {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new LennyExceptions("OOPS!!! The description of an event must include /from and /to.");
        }
        String rhs = parts[1];
        int fromIdx = rhs.indexOf("/from");
        int toIdx = rhs.indexOf("/to");
        if (fromIdx < 0 || toIdx < 0 || fromIdx >= toIdx) {
            throw new LennyExceptions("OOPS!!! Use: event NAME /from START /to END");
        }
        String name = rhs.substring(0, fromIdx).trim();
        String from = rhs.substring(fromIdx + 5, toIdx).trim();
        String to = rhs.substring(toIdx + 3).trim();
        return new Event(name, from, to, false);
    }

    /**
     * Parses a command string for an integer index for either 'mark', 'unmark' or 'delete' actions.
     *
     * @param input Full user input string.
     * @return An integer index.
     * @throws LennyExceptions If index is missing or invalid.
     */
    public static int parseIndex(String input) throws LennyExceptions {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new LennyExceptions("OOPS!!! Provide an index.");
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new LennyExceptions("OOPS!!! Index must be a number.");
        }
        if (index <= 0) {
            throw new LennyExceptions("OOPS!!! Index must be greater than zero.");
        }
        return index;
    }


    /**
     * Parses a find command for the keyword.
     *
     * @param input Full user input string containing the keyword.
     * @return A keyword.
     * @throws LennyExceptions If keyword is missing.
     */
    public static String parseKeyword(String input) throws LennyExceptions {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new LennyExceptions("OOPS!!! Provide a keyword.");
        }
        return parts[1];
    }

    /** For CLI exit condition */
    public static boolean isExit(String fullCommand) {
        return fullCommand.trim().equalsIgnoreCase("bye");
    }
}
