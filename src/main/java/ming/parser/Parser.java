package ming.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ming.command.Command;
import ming.command.DeadlineCommand;
import ming.command.DeleteCommand;
import ming.command.EventCommand;
import ming.command.ExitCommand;
import ming.command.FindCommand;
import ming.command.ListCommand;
import ming.command.MarkCommand;
import ming.command.TodoCommand;
import ming.command.UnmarkCommand;
import ming.exception.MingException;

/**
 * Parses user input into the corresponding commands for execution.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";
    private static final String TAGS_FLAG = "--tags";
    private static final int DEADLINE_PARTS = 2;
    private static final int EVENT_PARTS = 3;

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object representing the user input.
     * @throws MingException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws MingException {
        Scanner scanner = new Scanner(input);

        String command = scanner.hasNext() ? scanner.next() : "";
        String remainder = scanner.hasNextLine() ? scanner.nextLine() : "";

        List<String> tags = new ArrayList<>();
        int i;
        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            i = parseIndex(remainder);
            return new MarkCommand(i);

        case "unmark":
            i = parseIndex(remainder);
            return new UnmarkCommand(i);

        case "delete":
            i = parseIndex(remainder);
            return new DeleteCommand(i);

        case "find":
            tags = parseTags(remainder, TAGS_FLAG);

            if (!tags.isEmpty()) {
                return new FindCommand(tags.get(0), "tag");
            } else {
                remainder = stripTags(remainder);
                checkTaskEmpty(remainder);
                return new FindCommand(remainder, "name");
            }

        case "todo":
            tags = parseTags(remainder, TAGS_FLAG);
            remainder = stripTags(remainder);

            checkTaskEmpty(remainder);
            return new TodoCommand(remainder, tags);

        case "deadline":
            tags = parseTags(remainder, TAGS_FLAG);
            remainder = stripTags(remainder);

            checkTaskEmpty(remainder);
            String[] deadlineParts = parseDeadline(remainder);
            return new DeadlineCommand(deadlineParts[0], parseDateTime(deadlineParts[1]), tags);

        case "event":
            tags = parseTags(remainder, TAGS_FLAG);
            remainder = stripTags(remainder);

            checkTaskEmpty(remainder);
            String[] eventParts = parseEvent(remainder);
            return new EventCommand(eventParts[0],
                    parseDateTime(eventParts[1]),
                    parseDateTime(eventParts[2]),
                    tags);

        default:
            throw new MingException("Unknown command: " + command);
        }
    }

    private static int parseIndex(String arg) throws MingException {
        try {
            return Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MingException("Invalid index: " + arg);
        }
    }

    private static LocalDateTime parseDateTime(String input) throws MingException {
        try {
            return LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MingException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    private static void checkTaskEmpty(String remainder) throws MingException {
        if (remainder.isEmpty()) {
            throw new MingException("Please provide a task");
        }
    }

    private static List<String> parseTags(String remainder, String flag) throws MingException {
        List<String> tags = new ArrayList<>();
        int flagIndex = remainder.indexOf(flag);
        if (flagIndex == -1) {
            return tags;
        }

        String tagPart = remainder.substring(flagIndex + flag.length()).trim();
        // remove double quotes if present
        if (tagPart.startsWith("\"") && tagPart.endsWith("\"")) {
            tagPart = tagPart.substring(1, tagPart.length() - 1);
        }

        for (String tag : tagPart.split(",")) {
            if (!tag.trim().isEmpty()) {
                tags.add(tag.trim());
            }
        }

        if (tags.isEmpty()) {
            throw new MingException("Please provide valid tag(s)");
        }

        return tags;
    }

    private static String stripTags(String remainder) throws MingException {
        int flagIndex = remainder.indexOf(TAGS_FLAG);
        if (flagIndex == -1) {
            return remainder.trim();
        }
        return remainder.substring(0, flagIndex).trim();
    }

    private static String[] parseDeadline(String remainder) throws MingException {
        String[] deadlineParts = remainder.split(DEADLINE_SEPARATOR, DEADLINE_PARTS);
        if (deadlineParts.length < DEADLINE_PARTS || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            throw new MingException("Usage: deadline <description> /by <due date>");
        }
        return deadlineParts;
    }

    private static String[] parseEvent(String remainder) throws MingException {
        String[] eventParts = remainder.split(EVENT_FROM_SEPARATOR + "|" + EVENT_TO_SEPARATOR, EVENT_PARTS);
        if (eventParts.length < 3 || eventParts[0].isEmpty()
                || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
            throw new MingException("Usage: event <description> /from <start time> /to <end time>");
        }
        return eventParts;
    }
}
