package sunoo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sunoo.command.AddCommand;
import sunoo.command.AliasCommand;
import sunoo.command.ByeCommand;
import sunoo.command.Command;
import sunoo.command.DeleteCommand;
import sunoo.command.EnhypenCommand;
import sunoo.command.FindCommand;
import sunoo.command.HelpCommand;
import sunoo.command.IncorrectCommand;
import sunoo.command.ListCommand;
import sunoo.command.MarkCommand;
import sunoo.command.UnmarkCommand;
import sunoo.exception.SunooException;
import sunoo.task.Deadline;
import sunoo.task.Event;
import sunoo.task.ToDo;

/**
 * Parses user's input.
 */
public class Parser {

    /** Error messages */
    private static final String ERROR_FIND_KEYWORD = "ENGENE, give me a keyword so I can find tasks!";
    private static final String ERROR_INDEX_REQUIRED = "ENGENE, I need a number index to mark/unmark/delete tasks!";
    private static final String ERROR_TODO_EMPTY = "Sorry ENGENE, you don't have a todo description!";
    private static final String ERROR_DEADLINE_EMPTY = "Sorry ENGENE, your deadline task is empty!";
    private static final String ERROR_DEADLINE_FORMAT = """
        ENGENE, there seems to be a problem!
        1. Remember to include the " /by " keyword between your task description and deadline!
        2. Your description and deadline cannot be empty!""";
    private static final String ERROR_EVENT_EMPTY = "Sorry ENGENE, your event task is empty!";
    private static final String ERROR_EVENT_FORMAT = """
        ENGENE, there seems to be a problem!
        1. Remember to include the " /from " keyword between your event description and start time!
        2. Remember to include the " /to " keyword between your event start time and event end time!
        3. Your description, event start time and event end time cannot be empty!""";
    private static final String ERROR_DATETIME_FORMAT = """
            ENGENE, I need a date time format of "yyyy-MM-dd HH:mm"!
            For example: 2025-09-25 18:00""";
    private static final String ERROR_ENHYPEN_EMPTY = "Give me an ENHYPEN title, ENGENE!";

    /**
     * Parses the user's input into a command to be executed.
     *
     * @param userInput User's input.
     * @return Command that corresponds to user's input.
     */
    public static Command parse(String userInput) {
        userInput = userInput.trim();
        String[] parts = userInput.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        return switch (command) {
        case "bye", "end", "exit", "stop", "close" -> new ByeCommand();
        case "list", "l", "ls", "show", "display" -> new ListCommand();
        case "help" -> new HelpCommand();
        case "alias" -> new AliasCommand();
        case "find", "f", "fd", "search", "lookup" -> parseFindInput(parts);
        case "mark", "m" -> parseIndexedInput(parts, "mark");
        case "unmark", "um" -> parseIndexedInput(parts, "unmark");
        case "delete", "remove" -> parseIndexedInput(parts, "delete");
        case "todo", "t" -> parseToDoInput(parts);
        case "deadline", "d" -> parseDeadlineInput(parts);
        case "event", "e" -> parseEventInput(parts);
        case "enhypen" -> parseEnhypenInput(parts);
        default -> new IncorrectCommand("Sorry! Ddeonu doesn't know what you mean ToT");
        };
    }

    /**
     * Parses commands that require an integer index, such as "mark", "unmark", or "delete".
     *
     * @param parts       User input where {@code parts[1]} is expected to be the index.
     * @param commandType The type of index-based command: "mark", "unmark", "delete".
     * @return Corresponding {@link Command} with the index applied.
     * @throws SunooException if the index is missing or not a valid number.
     */
    private static Command parseIndexedInput(String[] parts, String commandType) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(parts[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new SunooException(ERROR_INDEX_REQUIRED);
        }
        return switch(commandType) {
        case "mark" -> new MarkCommand(taskIndex);
        case "unmark" -> new UnmarkCommand(taskIndex);
        case "delete" -> new DeleteCommand(taskIndex);
        default -> null;
        };
    }

    /**
     * Checks whether the second entry of the string array exists and returns it.
     * The second entry contains information for the commands, e.g. FindCommand, AddCommand.
     *
     * @param parts Array of strings.
     * @param errorMessage Error message to be used when throwing a SunooException.
     * @return String that is of the second entry of the string array,
     * @throws SunooException if there is no second entry of the string array.
     */
    private static String getCommandStringArguments(String[] parts, String errorMessage) {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new SunooException(errorMessage);
        }
        return parts[1];
    }

    private static FindCommand parseFindInput(String[] parts) {
        String keyword = getCommandStringArguments(parts, ERROR_FIND_KEYWORD);
        return new FindCommand(keyword);
    }

    private static AddCommand parseToDoInput(String[] parts) {
        String todoDescription = getCommandStringArguments(parts, ERROR_TODO_EMPTY);
        return new AddCommand(new ToDo(false, todoDescription));
    }

    private static EnhypenCommand parseEnhypenInput(String[] parts) {
        String titleTrack = getCommandStringArguments(parts, ERROR_ENHYPEN_EMPTY);
        return new EnhypenCommand(titleTrack);
    }

    /**
     * Splits the string given to parts at the keyword given.
     *
     * @param input String to be split.
     * @param keyword Keyword to split the string at.
     * @param errorMessage Error message to be used when throwing a SunooException.
     * @return String array containing the split results.
     * @throws SunooException if splitting is unsuccessful.
     */
    private static String[] splitByKeyword(String input, String keyword, String errorMessage) {
        String regex = "\\s+" + keyword + "\\s+";
        String[] split = input.split(regex);
        if (split.length < 2) {
            throw new SunooException(errorMessage);
        }
        return split;
    }

    private static AddCommand parseDeadlineInput(String[] parts) {
        String arg = getCommandStringArguments(parts, ERROR_DEADLINE_EMPTY);
        String[] split = splitByKeyword(arg, "/by", ERROR_DEADLINE_FORMAT);
        String description = split[0];
        LocalDateTime deadline = parseLocalDateTime(split[1]);
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new SunooException("ENGENE, this deadline has passed!");
        }
        return new AddCommand(new Deadline(false, description, deadline));
    }

    private static AddCommand parseEventInput(String[] parts) {
        String arg = getCommandStringArguments(parts, ERROR_EVENT_EMPTY);
        String[] fromSplit = splitByKeyword(arg, "/from", ERROR_EVENT_FORMAT);
        String description = fromSplit[0];
        String[] toSplit = splitByKeyword(fromSplit[1], "/to", ERROR_EVENT_FORMAT);

        LocalDateTime start = parseLocalDateTime(toSplit[0]);
        LocalDateTime end = parseLocalDateTime(toSplit[1]);

        if (end.isBefore(LocalDateTime.now())) {
            throw new SunooException("ENGENE, this event has ended!");
        } else if (!end.isAfter(start)) {
            throw new SunooException("ENGENE, an event needs to start before it ends!");
        }

        return new AddCommand(new Event(false, description, start, end));
    }

    /**
     * Parses a string and returns a LocalDateTime object.
     *
     * @param input Input to be parsed.
     * @return LocalDateTime object that represents date time.
     */
    private static LocalDateTime parseLocalDateTime(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new SunooException(ERROR_DATETIME_FORMAT);
        }
    }
}
