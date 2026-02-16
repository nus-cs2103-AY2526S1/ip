package guibot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import guibot.command.AddCommand;
import guibot.command.ArchiveCommand;
import guibot.command.Command;
import guibot.command.DeleteCommand;
import guibot.command.FindCommand;
import guibot.command.ListCommand;
import guibot.command.LoadCommand;
import guibot.command.MarkCommand;
import guibot.command.UnmarkCommand;
import guibot.exception.DataFileCorruptedException;
import guibot.exception.GuibotException;
import guibot.exception.UnknownRequestException;
import guibot.exception.WrongDateTimeFormatException;
import guibot.exception.WrongInputFormatException;
import guibot.task.Deadline;
import guibot.task.Event;
import guibot.task.Task;
import guibot.task.TaskType;
import guibot.task.Todo;

/**
 * Handles string input.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");
    /**
     * Converts input into a command.
     *
     * @param input The input in String format.
     * @throws GuibotException If input is missing arguments.
     */
    public static Command parse(String input) throws GuibotException, IOException {
        String commandWord = input.split(" ", 2)[0];

        return switch (commandWord) {
        case "list" -> ListCommand.of(input);
        case "find" -> FindCommand.of(input);
        case "mark" -> MarkCommand.of(input);
        case "unmark" -> UnmarkCommand.of(input);
        case "delete" -> DeleteCommand.of(input);
        case "todo" -> AddCommand.of(input, TaskType.TODO);
        case "deadline" -> AddCommand.of(input, TaskType.DEADLINE);
        case "event" -> AddCommand.of(input, TaskType.EVENT);
        case "archive" -> ArchiveCommand.of(input);
        case "load" -> LoadCommand.of(input);
        default -> throw new UnknownRequestException();
        };
    }

    /**
     * Splits an input into the relevant portions according to the specified splitters.
     *
     * @param input The string input to be split.
     * @param splitters The strings to split the input according to.
     * @return An array of strings containing the details of the command.
     * @throws WrongInputFormatException If the input is not formatted as expected.
     */
    public static String[] getDetails(String input, String... splitters) throws WrongInputFormatException {
        String[] details = new String[splitters.length + 1];

        for (int i = 0; i < splitters.length; i++) {
            String[] splitInput = input.split(splitters[i], 2);
            if (splitInput.length < 2) {
                throw new WrongInputFormatException();
            }
            details[i] = splitInput[0];
            input = splitInput[1];
        }
        details[splitters.length] = input;

        return details;
    }

    /**
     * Converts a storage string into a task.
     *
     * @param string The storage string.
     * @throws DataFileCorruptedException If the string is not in the expected format.
     */
    public static Task getTaskFromString(String string) throws DataFileCorruptedException {
        String[] taskInfo = string.split("//");
        Task t;

        if (taskInfo.length == 3) {
            String[] details = taskInfo[2].split("/");

            try {
                t = switch (taskInfo[0]) {
                case "t" -> Todo.of(details);
                case "d" -> Deadline.of(details);
                case "e" -> Event.of(details);
                default -> throw new DataFileCorruptedException();
                };
            } catch (WrongDateTimeFormatException e) {
                throw new DataFileCorruptedException();
            }
        } else {
            throw new DataFileCorruptedException();
        }

        switch (taskInfo[1]) {
        case "true" -> t.mark();
        case "false" -> { }
        default -> throw new DataFileCorruptedException();
        };

        return t;
    }

    /**
     * Converts an input string into an integer.
     *
     * @param input The input string to convert.
     * @throws WrongInputFormatException If the string is not an integer.
     */
    public static int getIndexFromString(String input) throws WrongInputFormatException {
        if (input.matches("[0-9]*")) {
            return Integer.parseInt(input);
        } else {
            throw new WrongInputFormatException();
        }
    }

    /**
     * Converts an input string into a LocalDateTime.
     *
     * @param input The input string.
     * @return The LocalDateTime object.
     * @throws WrongDateTimeFormatException If input is in the wrong format.
     */
    public static LocalDateTime getDateTimeFromString(String input) throws WrongDateTimeFormatException {
        try {
            return LocalDateTime.parse(input, INPUT_DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    /**
     * Converts a LocalDateTime into an input string/storage string.
     *
     * @param dateTime The LocalDateTime object.
     * @return The dateTime formatted into input string format (also used for storage).
     */
    public static String getInputStringFromDateTime(LocalDateTime dateTime) {
        return dateTime.format(INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Converts a LocalDateTime into an output string.
     *
     * @param dateTime The LocalDateTime object.
     * @return The dateTime formatted into an output string format.
     */
    public static String getOutputStringFromDateTime(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATE_TIME_FORMAT);
    }
}
