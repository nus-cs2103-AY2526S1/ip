package chani;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import chani.commands.AddCommand;
import chani.commands.Command;
import chani.commands.DeleteCommand;
import chani.commands.ExitCommand;
import chani.commands.FindCommand;
import chani.commands.ListCommand;
import chani.commands.MarkCommand;
import chani.commands.UnmarkCommand;


/**
 * Parses user input strings into {@link Command} objects.
 */
public class Parser {
    private static final int COMMAND_NAME = 0;
    private static final int COMMAND_ARGS = 1;
    /**
     * Parses the input string and returns the corresponding {@link Command}.
     * @param input The user input string.
     * @return The {@link Command} object representing the input.
     * @throws ChaniException if the input is invalid or the command is unknown.
     */
    public static Command parse(String input) throws ChaniException {
        String[] commandWithMaybeArgs = parseInput(input);
        String command = commandWithMaybeArgs[COMMAND_NAME];
        String rawArgs = commandWithMaybeArgs.length > 1 ? commandWithMaybeArgs[COMMAND_ARGS] : "";

        return switch (command) {
        case "list" -> {
            requireNoArgs(rawArgs, command);
            yield new ListCommand(command);
        }
        case "bye" -> {
            requireNoArgs(rawArgs, command);
            yield new ExitCommand(command);
        }
        case "mark" -> {
            String taskNumber = requireValidTaskNumber(rawArgs, "mark <task number>");
            yield new MarkCommand(command, taskNumber);
        }
        case "unmark" -> {
            String taskNumber = requireValidTaskNumber(rawArgs, "unmark <task number>");
            yield new UnmarkCommand(command, taskNumber);
        }
        case "delete" -> {
            String taskNumber = requireValidTaskNumber(rawArgs, "delete <task number>");
            yield new DeleteCommand(command, taskNumber);
        }
        case "find" -> {
            String query = requireSingleArg(rawArgs, "find <query>");
            yield new FindCommand(command, query);
        }
        case "todo" -> {
            String desc = requireSingleArg(rawArgs, "todo <desc>");
            yield new AddCommand(command, desc);
        }
        case "deadline" -> {
            String errMessage = "deadline <desc> /by <yyyy-mm-dd>";
            String[] args = splitMultipleArgs(rawArgs, errMessage, " /by ");
            requireValidDateFormat(args[1], errMessage);
            yield new AddCommand(command, args);
        }
        case "event" -> {
            String[] args = splitMultipleArgs(rawArgs, "event <desc> /from /to", " /from ", " /to ");
            yield new AddCommand(command, args);
        }
        case "period" -> {
            String[] args = splitMultipleArgs(rawArgs, "period <desc> /between /and", " /between ", " /and ");
            yield new AddCommand(command, args);
        }

        default -> throw new ChaniException("OOPS!!! I'm sorry, but I don't know what that command means :-(");
        };

    }

    /**
     * Extracts the command name (in all inputs).
     * @param input The input array to check.
     * @return The command and optional args.
     */
    private static String[] parseInput(String input) {
        String trimmed = input.trim();
        return trimmed.split(" ", 2);
    }

    /**
     * Splits and validates the args string.
     * @param args The string of args to check.
     * @param delimiters to split by.
     * @param errorMessage The message returned as "Use [errorMessage] instead".
     * @throws ChaniException if the input array has less than 2 elements.
     */
    private static String[] splitMultipleArgs(String args, String errorMessage, String... delimiters)
            throws ChaniException {
        if (args.isEmpty()) {
            throw new ChaniException("Invalid Command: Use " + errorMessage + " instead");
        }
        ArrayList<String> result = new ArrayList<>();
        String remaining = args;
        for (String delimiter: delimiters) {
            String[] tokens = splitOnce(remaining, delimiter, errorMessage);
            result.add(tokens[0]);
            remaining = tokens[1];
        }
        result.add(remaining);
        return result.toArray(new String[0]);
    }

    /**
     * helper method used by {@link #splitMultipleArgs(String, String, String...)}.
     * @param input The argument value to check.
     * @param delimiter to split by.
     * @param errorMessage The message returned as "Use [errorMessage] instead".
     * @return array of split strings
     * @throws ChaniException if the input array has less than 2 elements.
     */
    private static String[] splitOnce(String input, String delimiter, String errorMessage) throws ChaniException {
        int numOfExpectedTokens = 2;

        String[] tokens = input.split(delimiter, numOfExpectedTokens);
        if (tokens.length < numOfExpectedTokens) {
            throw new ChaniException("Invalid Command. Use " + errorMessage + " instead");
        }
        return tokens;
    }

    /**
     * Ensures there is at least a single argument.
     * @param args The argument value to check.
     * @param errorMessage The message returned as "Use [errorMessage] instead".
     * @return the single argument
     * @throws ChaniException if the input array has less than 2 elements.
     */
    private static String requireSingleArg(String args, String errorMessage) throws ChaniException {
        if (args.isEmpty()) {
            throw new ChaniException("Invalid Command: Use " + errorMessage + " instead");
        }
        return args;
    }

    /**
     * Ensures there are no arguments
     * @param args The argument value to check.
     * @param commandName The command name.
     * @throws ChaniException if the input array has less than 2 elements.
     */
    private static void requireNoArgs(String args, String commandName) throws ChaniException {
        if (!args.trim().isEmpty()) {
            throw new ChaniException("The command '" + commandName + "' does not take arguments.");
        }
    }

    /**
     * Checks that the task number is a number and within range.
     * @param args The argument to check.
     * @throws ChaniException if the task number is not an integer.
     */
    private static String requireValidTaskNumber(String args, String errMessage) throws ChaniException {
        try {
            Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new ChaniException("Invalid Command. Use " + errMessage + " instead");
        }
        return args;
    }

    /**
     * Checks that the date is valid.
     * @param date The argument to check.
     * @throws ChaniException if the task number is not an integer.
     */
    private static String requireValidDateFormat(String date, String errMessage) throws ChaniException {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new ChaniException("Invalid Command. Use " + errMessage + " instead");
        }
        return date;
    }

}
