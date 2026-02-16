package jinbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jinbot.command.ByeCommand;
import jinbot.command.Command;
import jinbot.command.DeadlineCommand;
import jinbot.command.DeleteCommand;
import jinbot.command.EventCommand;
import jinbot.command.FindCommand;
import jinbot.command.ListCommand;
import jinbot.command.MarkCommand;
import jinbot.command.PriorityCommand;
import jinbot.command.TodoCommand;
import jinbot.command.UnmarkCommand;

/**
 * Parses user input into executable Command objects.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    /**
     * Parses the given user input string and returns the corresponding Command.
     *
     * @param input The raw user input string.
     * @return The Command representing the user’s instruction.
     * @throws JinBotException If the input is empty, malformed, or contains an invalid command.
     */
    public static Command parse(String input) throws JinBotException {
        String trimmedInput = input.trim();
        LocalDate today = LocalDate.now();

        if (trimmedInput.isEmpty()) {
            throw new JinBotException("Error! Empty input!");
        }

        String[] parts = trimmedInput.split(" ", 2);
        assert parts.length >= 1 : "Common word should be present";
        String commandWord = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return parseBye(args);
        case "list":
            return parseList(args);
        case "mark":
            return new MarkCommand(requireArgs(args, "Provide a task number to mark."));
        case "unmark":
            return new UnmarkCommand(requireArgs(args, "Provide a task number to unmark."));
        case "todo":
            return parseTodo(args);
        case "deadline":
            return parseDeadline(args, today);
        case "event":
            return parseEvent(args, today);
        case "delete":
            return new DeleteCommand(requireArgs(args, "Provide a task number to delete."));
        case "find":
            return new FindCommand(requireArgs(args, "Provide a task number to find."));
        case "priority":
            return parsePriority(args);
        default:
            throw new JinBotException("Error! I don't understand that command word: " + commandWord);
        }
    }

    private static Command parseBye(String args) throws JinBotException {
        if (!args.isEmpty()) {
            throw new JinBotException("Error! Only write 'bye' to exit.");
        }
        return new ByeCommand();
    }

    private static Command parseList(String args) throws JinBotException {
        if (!args.isEmpty()) {
            throw new JinBotException("Error! Only write 'list'.");
        }
        return new ListCommand();
    }

    private static Command parseTodo(String args) throws JinBotException {
        if (args.isEmpty()) {
            throw new JinBotException("Error! Todo description cannot be empty.");
        }
        return new TodoCommand(args);
    }

    private static Command parseDeadline(String args, LocalDate today) throws JinBotException {
        String[] parts = args.split(DEADLINE_SEPARATOR, 2);
        if (parts.length < 2) {
            throw new JinBotException("Error! Needs description and /by time.");
        }

        String description = parts[0].trim();
        LocalDate by = parseDate(parts[1].trim());
        if (by.isBefore(today)) {
            throw new JinBotException("Error! Deadline already passed.");
        }
        return new DeadlineCommand(description, by);
    }

    private static Command parseEvent(String args, LocalDate today) throws JinBotException {
        if (args.isEmpty()) {
            throw new JinBotException("Error! Description of an event cannot be empty.");
        }

        String[] fromParts = args.split(EVENT_FROM_SEPARATOR, 2);
        if (fromParts.length < 2) {
            throw new JinBotException("Error! Needs /from time.");
        }

        String[] toParts = fromParts[1].split(EVENT_TO_SEPARATOR, 2);
        if (toParts.length < 2) {
            throw new JinBotException("Error! Needs both /from and /to times.");
        }

        LocalDate from = parseDate(toParts[0].trim());
        LocalDate to = parseDate(toParts[1].trim());

        if (from.isBefore(today)) {
            throw new JinBotException("Error! Event start has passed.");
        }
        if (to.isBefore(from)) {
            throw new JinBotException("Error! Event end must be after start.");
        }

        return new EventCommand(fromParts[0].trim(), from, to);
    }

    private static LocalDate parseDate(String dateStr) throws JinBotException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(dateStr, FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new JinBotException("Error! Invalid date format. Use 'DD/MM/YYYY' or 'YYYY-MM-DD'.");
            }
        }
    }

    private static Command parsePriority(String args) throws JinBotException {
        String[] parts = args.split(" ");
        if (parts.length != 2) {
            throw new JinBotException("Error! Usage: priority <taskIndex> <LOW|MEDIUM|HIGH>");
        }
        return new PriorityCommand(parts[0], parts[1]);
    }

    private static String requireArgs(String args, String errorMessage) throws JinBotException {
        if (args.isEmpty()) {
            throw new JinBotException("Error! " + errorMessage);
        }
        return args;
    }
}
