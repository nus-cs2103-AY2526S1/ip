package evansbot.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import evansbot.exceptions.EvansBotException;
import evansbot.exceptions.InvalidCommandException;
import evansbot.exceptions.InvalidDeadlineException;
import evansbot.exceptions.InvalidEventException;
import evansbot.command.AddDeadlineCommand;
import evansbot.command.AddEventCommand;
import evansbot.command.AddTodoCommand;
import evansbot.command.Command;
import evansbot.command.DeleteCommand;
import evansbot.command.ExitCommand;
import evansbot.command.FindCommand;
import evansbot.command.GreetCommand;
import evansbot.command.ListCommand;
import evansbot.command.MarkCommand;
import evansbot.command.UnmarkCommand;
import evansbot.command.ViewScheduleCommand;



/**
 * Parses user input strings into corresponding Command objects for EvansBot.
 * Throws EvansBotException or its subclasses if the input is invalid.
 */
public class Parser {
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";
    private static final String CMD_VIEW = "view";
    private static final String CMD_GREET = "greet";
    /**
     * Parses the user's input and returns the appropriate Command object.
     *
     * @param input Raw user input string.
     * @return Command object corresponding to the user's input.
     * @throws EvansBotException If the input is invalid or cannot be parsed
     *                           into a recognized command.
     */
    public static Command parse(String input) throws EvansBotException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidCommandException("Input cannot be empty.");
        }

        String[] tokens = trimmed.split(" ", 2);
        String commandWord = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1].trim() : "";

        return switch (commandWord) {
        case CMD_BYE -> new ExitCommand();
        case CMD_LIST -> new ListCommand();
        case CMD_MARK -> parseMark(arguments);
        case CMD_UNMARK -> parseUnmark(arguments);
        case CMD_TODO -> parseTodo(arguments);
        case CMD_DEADLINE -> parseDeadline(arguments);
        case CMD_EVENT -> parseEvent(arguments);
        case CMD_DELETE -> parseDelete(arguments);
        case CMD_FIND -> parseFind(arguments);
        case CMD_VIEW -> parseView(arguments);
        case CMD_GREET -> new GreetCommand();
        default -> throw new InvalidCommandException();
        };
    }
    /**
     * Parses the "mark" command.
     *
     * @param args The index argument provided by the user.
     * @return A MarkCommand with the valid index.
     * @throws EvansBotException If the index is missing or invalid.
     */
    private static Command parseMark(String args) throws EvansBotException {
        int index = parseIndex(args, "mark");
        return new MarkCommand(index);
    }

    /**
     * Parses the "unmark" command.
     *
     * @param args The index argument provided by the user.
     * @return An  UnmarkCommand  with a valid index
     * @throws EvansBotException If the index is missing or invalid.
     */
    private static Command parseUnmark(String args) throws EvansBotException {
        int index = parseIndex(args, "unmark");
        return new UnmarkCommand(index);
    }

    /**
     * Parses the "todo" command.
     *
     * @param args The task description provided by the user.
     * @return An AddTodoCommand with the args.
     * @throws EvansBotException If the description is empty.
     */
    private static Command parseTodo(String args) throws EvansBotException {
        assert args != null : "Todo description should not be null";
        assert !args.trim().isEmpty() : "Todo description should not be blank";
        if (args.isEmpty()) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(args);
    }

    /**
     * Parses the "deadline" command.
     *
     * @param args The description and deadline, separated by "/by".
     * @return An AddDeadlineCommand object with description and by.
     * @throws EvansBotException If the format is invalid.
     */
    private static Command parseDeadline(String args) throws EvansBotException {
        String[] information = args.split(" /by ", 2);
        if (information.length < 2) {
            throw new InvalidDeadlineException();
        }
        String description = information[0].trim();
        String by = information[1].trim();
        assert !description.isEmpty() : "Deadline description should not be blank";
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the "event" command.
     *
     * @param args The description, start time, and end time, separated by "/from" and "/to".
     * @return An AddEventCommand with description, from and to.
     * @throws EvansBotException If the format is invalid.
     */
    private static Command parseEvent(String args) throws EvansBotException {
        String[] information = args.split(" /from | /to ", 3);
        if (information.length < 3) {
            throw new InvalidEventException();
        }
        String description = information[0].trim();
        String from = information[1].trim();
        String to = information[2].trim();
        assert !description.isEmpty() : "Event description should not be blank";

        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the "delete" command.
     *
     * @param args The index argument provided by the user.
     * @return A DeleteCommand with the index..
     * @throws EvansBotException If the index is missing or invalid.
     */
    private static Command parseDelete(String args) throws EvansBotException {
        int index = parseIndex(args, "delete");
        return new DeleteCommand(index);
    }

    /**
     * Parses the "find" command.
     *
     * @param args The keyword provided by the user.
     * @return A FindCommand with args.
     * @throws EvansBotException If the keyword is empty.
     */
    private static Command parseFind(String args) throws EvansBotException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please provide a keyword to search for.");
        }
        return new FindCommand(args);
    }
    /**
     * Parses the "view" command.
     *
     * @param args The date provided by the user.
     * @return A ViewScheduleCommand with date.
     * @throws EvansBotException If the keyword is empty.
     */
    private static Command parseView(String args) throws EvansBotException {
        try {
            LocalDate date = LocalDate.parse(args.trim());
            return new ViewScheduleCommand(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please enter date in YYYY-MM-DD format.");
        }
    }
    /**
     * Utility method for parsing an index-based argument.
     *
     * @param arg The string representing an index.
     * @param commandName The name of the command for clearer error messages.
     * @return The parsed index as an integer.
     * @throws EvansBotException If the argument cannot be parsed as a number.
     */
    private static int parseIndex(String arg, String commandName) throws EvansBotException {
        try {
            return Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(
                    "The argument for '" + commandName + "' must be a number."
            );
        }
    }
}
