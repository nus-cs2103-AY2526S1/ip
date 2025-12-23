package scribbles.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import scribbles.command.AddDeadlineCommand;
import scribbles.command.AddEventCommand;
import scribbles.command.AddToDoCommand;
import scribbles.command.Command;
import scribbles.command.DeleteCommand;
import scribbles.command.ExitCommand;
import scribbles.command.FindCommand;
import scribbles.command.HelpCommand;
import scribbles.command.ListCommand;
import scribbles.command.MarkCommand;
import scribbles.command.UnknownCommand;
import scribbles.command.UnmarkCommand;
import scribbles.exception.InvalidDateTimeException;
import scribbles.exception.InvalidParamException;
import scribbles.exception.MissingArgumentException;
import scribbles.exception.MissingDescriptionException;
import scribbles.exception.ScribblesException;
import scribbles.exception.WrongParamOrderException;

/**
 * Provides a handle to parse user input commands.
 */
public class Parser {

    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Parses user input into appropriate commands that
     * are to be executed.
     *
     * @param userCommand User input command to parse.
     * @return Crafted command to be executed anytime.
     * @throws ScribblesException When parsing error occurs.
     */
    public static Command parseCommand(String userCommand) throws ScribblesException {
        String[] tokens = userCommand.trim().split(" ", 2);
        String command = tokens[0].toLowerCase();
        String args = tokens.length > 1 ? tokens[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMark(args);
        case "unmark":
            return parseUnmark(args);
        case "delete":
            return parseDelete(args);
        case "todo":
            return parseToDo(args);
        case "deadline":
            return parseDeadline(args);
        case "event":
            return parseEvent(args);
        case "find":
            return parseFind(args);
        case "help":
            return new HelpCommand();
        default:
            return new UnknownCommand(command);
        }
    }

    // Helper parse methods for index-based commands
    private static Command parseMark(String args) throws ScribblesException {
        int index = Parser.parseIndex(args);
        return new MarkCommand(index);
    }

    private static Command parseUnmark(String args) throws ScribblesException {
        int index = Parser.parseIndex(args);
        return new UnmarkCommand(index);
    }

    private static Command parseDelete(String args) throws ScribblesException {
        int index = Parser.parseIndex(args);
        return new DeleteCommand(index);
    }

    // Shared helper method for mark, unmark, and delete commands
    private static int parseIndex(String args) throws ScribblesException {
        if (args.isEmpty()) {
            throw new MissingArgumentException();
        }

        try {
            int index = Integer.parseInt(args.trim());
            if (index <= 0) {
                throw new ScribblesException("[!] Index must be a positive number!!");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new ScribblesException("[!] Argument needs to be an integer :/");
        }
    }

    // Helper parse methods for task-based commands
    private static Command parseToDo(String args) throws ScribblesException {
        if (args.isEmpty()) {
            throw new MissingDescriptionException();
        }
        return new AddToDoCommand(args);
    }

    private static Command parseDeadline(String args) throws ScribblesException {
        if (args.startsWith("/by")) {
            throw new MissingDescriptionException();
        }

        if (!args.contains(" /by ")) {
            throw new InvalidParamException("/by");
        }

        String[] descAndBy = args.split(" /by ", 2);
        boolean isMissingDescription = descAndBy.length < 2;
        boolean isEmptyDescription = descAndBy[0].trim().isEmpty();
        if (isMissingDescription || isEmptyDescription) {
            throw new MissingDescriptionException();
        }

        String desc = descAndBy[0];
        LocalDateTime by = Parser.parseDateTime(descAndBy[1]);
        return new AddDeadlineCommand(desc, by);
    }

    private static Command parseEvent(String args) throws ScribblesException {
        if (args.startsWith("/from")) {
            throw new MissingDescriptionException();
        }

        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new InvalidParamException("/from", "/to");
        }

        if (args.indexOf(" /from ") > args.indexOf(" /to ")) {
            throw new WrongParamOrderException("/from", "/to");
        }

        String[] descFromTo = args.split(" /from ", 2);
        if (descFromTo[0].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        String desc = descFromTo[0];

        String[] fromTo = descFromTo[1].split(" /to ", 2);
        if (fromTo.length < 2) {
            throw new MissingArgumentException();
        }

        LocalDateTime from = Parser.parseDateTime(fromTo[0]);
        LocalDateTime to = Parser.parseDateTime(fromTo[1]);

        return new AddEventCommand(desc, from, to);
    }

    private static Command parseFind(String args) throws ScribblesException {
        if (args.isEmpty()) {
            throw new MissingDescriptionException();
        }
        return new FindCommand(args);
    }

    /**
     * Parses string dateTime into LocalDateTime format.
     *
     * @param dateTime dateTime in String format.
     * @return dateTime in LocalDateTime format.
     * @throws ScribblesException When dateTime format is wrong.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ScribblesException {
        try {
            return LocalDateTime.parse(dateTime, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
