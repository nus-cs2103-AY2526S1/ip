package yappy.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yappy.command.AddCommand;
import yappy.command.Command;
import yappy.command.DeleteCommand;
import yappy.command.ExitCommand;
import yappy.command.FindCommand;
import yappy.command.ListCommand;
import yappy.command.MarkCommand;
import yappy.command.PostponeCommand;
import yappy.command.RescheduleCommand;
import yappy.command.UnmarkCommand;
import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.ToDo;

/**
 * The <code>Parser</code> class is responsible for parsing raw user input
 * strings and converting them into <code>Command</code> objects.
 * If the input does not match any known command format,
 * a {@link YappyException} is thrown.</p>
 *
 * <p>This class also validates the correctness of user input
 * (e.g., checking that an index is provided where required, or that
 * dates follow the expected format).</p>
 */
public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String COMMANDS_HELP =
            """
            Sorry I do not understand what you're yapping about.
            \t
            Available commands:
            - bye
            - list
            - find <query1,query2,...>
            - mark <index>
            - unmark <index>
            - delete <index>
            - todo <description>
            - deadline <description> /by <date>
            - event <description> /from <date> /to <date>
            - reschedule <index> /from <date> /to <date>
            - postpone <index> /by <date>
            Note: dates must use the format yyyy-MM-dd HH:mm""";
    /**
     * Parses the given raw input string and returns the corresponding <code>Command</code>
     * object that can be executed by the application.
     *
     * <p>Date/time inputs must follow the format <code>yyyy-MM-dd HH:mm</code>.</p>
     *
     * @param command The raw user input string.
     * @return The <code>Command</code> corresponding to the parsed input.
     * @throws YappyException If the input is invalid or does not match a known command.
     */
    public static Command parse(String command) throws YappyException {
        if (command == null || command.trim().isEmpty()) {
            throw new YappyException(COMMANDS_HELP);
        }

        String trimmed = command.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return parseFindCommand(args);
        case "mark":
            return parseMarkCommand(args);
        case "unmark":
            return parseUnmarkCommand(args);
        case "delete":
            return parseDeleteCommand(args);
        case "todo":
            return parseTodoCommand(args);
        case "event":
            return parseEventCommand(args);
        case "deadline":
            return parseDeadlineCommand(args);
        case "reschedule":
            return parseRescheduleCommand(args);
        case "postpone":
            return parsePostponeCommand(args);
        default:
            throw new YappyException(COMMANDS_HELP);
        }
    }

    /**
     * Returns a {@link FindCommand} built from the provided argument string.
     *
     * <p>Splits the argument by commas and trims each query token.</p>
     *
     * @param args The comma separated query string.
     * @return A {@link FindCommand} containing the parsed queries.
     * @throws YappyException If no queries are provided.
     */
    private static Command parseFindCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the query string!");
        }
        String[] queries = java.util.Arrays.stream(args.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        if (queries.length == 0) {
            throw new YappyException("Please specify at least one query!");
        }
        return new FindCommand(queries);
    }

    /**
     * Returns a {@link MarkCommand} for the specified index token.
     *
     * @param args The token or argument string containing the index.
     * @return A {@link MarkCommand} targeting the parsed index.
     * @throws YappyException If the index is missing or invalid.
     */
    private static Command parseMarkCommand(String args) throws YappyException {
        int idx = parseIndex(args, "mark");
        return new MarkCommand(idx);
    }

    /**
     * Returns an {@link UnmarkCommand} for the specified index token.
     *
     * @param args The token or argument string containing the index.
     * @return An {@link UnmarkCommand} targeting the parsed index.
     * @throws YappyException If the index is missing or invalid.
     */
    private static Command parseUnmarkCommand(String args) throws YappyException {
        int idx = parseIndex(args, "unmark");
        return new UnmarkCommand(idx);
    }

    /**
     * Returns a {@link DeleteCommand} for the specified index token.
     *
     * @param args The token or argument string containing the index.
     * @return A {@link DeleteCommand} targeting the parsed index.
     * @throws YappyException If the index is missing or invalid.
     */
    private static Command parseDeleteCommand(String args) throws YappyException {
        int idx = parseIndex(args, "delete");
        return new DeleteCommand(idx);
    }

    /**
     * Returns an {@link AddCommand} that adds a {@link ToDo} created from the arguments.
     *
     * <p>Keeps everything before an optional "/from" token as the todo description.</p>
     *
     * @param args The arguments containing the todo description.
     * @return An {@link AddCommand} wrapping the created {@link ToDo}.
     * @throws YappyException If the description is missing or empty.
     */
    private static Command parseTodoCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the todo task!");
        }
        // keep everything before a possible "/from" token
        String description = args.split("/from", 2)[0].trim();
        if (description.isEmpty()) {
            throw new YappyException("Todo description cannot be empty!");
        }
        return new AddCommand(new ToDo(description, false));
    }

    /**
     * Returns an {@link AddCommand} that adds an {@link Event} parsed from the arguments.
     *
     * <p>Arguments must contain '/from' and '/to' tokens followed by date/time values.</p>
     *
     * @param args The arguments containing the event description and dates.
     * @return An {@link AddCommand} wrapping the created {@link Event}.
     * @throws YappyException If description or date tokens are missing or invalid.
     */
    private static Command parseEventCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the event task and start and end dates!");
        }
        String[] fromSplit = args.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new YappyException("Event must include '/from' and '/to' with dates!");
        }
        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new YappyException("Event must include '/to' with end date!");
        }
        LocalDateTime from = parseDate(toSplit[0].trim(), "event start");
        LocalDateTime to = parseDate(toSplit[1].trim(), "event end");
        if (description.isEmpty()) {
            throw new YappyException("Event description cannot be empty!");
        }
        return new AddCommand(new Event(description, false, from, to));
    }

    /**
     * Returns an {@link AddCommand} that adds a {@link Deadline} parsed from the arguments.
     *
     * <p>Arguments must include the '/by' token followed by a date/time value.</p>
     *
     * @param args The arguments containing the deadline description and date.
     * @return An {@link AddCommand} wrapping the created {@link Deadline}.
     * @throws YappyException If description or '/by' date is missing or invalid.
     */
    private static Command parseDeadlineCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the deadline task and deadline!");
        }
        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new YappyException("Deadline must include '/by' with a date!");
        }
        String description = parts[0].trim();
        LocalDateTime by = parseDate(parts[1].trim(), "deadline");
        if (description.isEmpty()) {
            throw new YappyException("Deadline description cannot be empty!");
        }
        return new AddCommand(new Deadline(description, false, by));
    }

    /**
     * Returns a {@link PostponeCommand} that postpones the task at the specified index.
     *
     * <p>Accepts either a trailing '/by' token or a plain date/time string after the index.</p>
     *
     * @param args The arguments containing the index and the postpone date.
     * @return A {@link PostponeCommand} with the parsed index and date.
     * @throws YappyException If index or date is missing or invalid.
     */
    private static Command parsePostponeCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the index of the task to be postponed!");
        }
        String[] parts = args.split("\\s+", 2);
        int idx = parseIndex(parts[0], "postpone");
        if (parts.length < 2) {
            throw new YappyException("Please specify the '/by' date for postponing!");
        }
        String byPart = parts[1];
        if (!byPart.contains("/by")) {
            // allow both formats: "postpone 2 /by 2024-01-01 10:00" or "postpone 2 2024-01-01 10:00"
            // if "/by" present, extract after it; otherwise treat the remainder as date
            byPart = byPart;
        }
        String byStr = byPart.contains("/by") ? byPart.split("/by", 2)[1].trim() : byPart.trim();
        LocalDateTime by = parseDate(byStr, "postpone");
        return new PostponeCommand(idx, by);
    }

    /**
     * Returns a {@link RescheduleCommand} that reschedules the task at the specified index.
     *
     * <p>Arguments must include '/from' and '/to' tokens with date/time values after the index.</p>
     *
     * @param args The arguments containing the index and the from/to dates.
     * @return A {@link RescheduleCommand} with the parsed index and date range.
     * @throws YappyException If index or date tokens are missing or invalid.
     */
    private static Command parseRescheduleCommand(String args) throws YappyException {
        if (args.isEmpty()) {
            throw new YappyException("Please specify the index of the task to be rescheduled!");
        }
        String[] parts = args.split("\\s+", 2);
        int idx = parseIndex(parts[0], "reschedule");
        if (parts.length < 2) {
            throw new YappyException("Please specify the '/from' and '/to' dates!");
        }
        String fromTo = parts[1];
        String[] fromSplit = fromTo.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new YappyException("Reschedule must include '/from' and '/to' with dates!");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new YappyException("Reschedule must include '/to' with end date!");
        }
        LocalDateTime from = parseDate(toSplit[0].trim(), "reschedule from");
        LocalDateTime to = parseDate(toSplit[1].trim(), "reschedule to");
        return new RescheduleCommand(idx, from, to);
    }

    /**
     * Parses the provided token or arguments and returns a zero-based index.
     *
     * <p>Accepts a string containing the index as the first token and converts it to zero-based form.</p>
     *
     * @param tokenOrArgs The token or argument string containing the index.
     * @param cmdName The name of the command for clearer error messages.
     * @return The parsed zero-based index.
     * @throws YappyException If the index is missing, non-numeric, or non-positive.
     */
    private static int parseIndex(String tokenOrArgs, String cmdName) throws YappyException {
        if (tokenOrArgs == null || tokenOrArgs.trim().isEmpty()) {
            throw new YappyException("Please specify the index for '" + cmdName + "'!");
        }
        String token = tokenOrArgs.trim().split("\\s+")[0];
        try {
            int idx = Integer.parseInt(token) - 1;
            if (idx < 0) {
                throw new YappyException("Index must be a positive integer!");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new YappyException("Please input an integer index!");
        }
    }

    /**
     * Parses the provided date/time string using the expected formatter and returns a {@link LocalDateTime}.
     *
     * <p>Expects the format yyyy-MM-dd HH:mm and provides a clear error message on failure.</p>
     *
     * @param dateString The date/time string to parse.
     * @param label A short label used in error messages to indicate which date failed to parse.
     * @return The parsed {@link LocalDateTime}.
     * @throws YappyException If the date string is missing or cannot be parsed.
     */
    private static LocalDateTime parseDate(String dateString, String label) throws YappyException {
        if (dateString == null || dateString.isEmpty()) {
            throw new YappyException("Please specify a date/time for " + label + " using format yyyy-MM-dd HH:mm");
        }
        try {
            return LocalDateTime.parse(dateString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new YappyException("Invalid date/time for " + label + ".\nUse format yyyy-MM-dd HH:mm");
        }
    }
}
