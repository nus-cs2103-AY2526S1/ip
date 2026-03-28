package luna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import luna.tasks.Deadline;
import luna.tasks.Event;
import luna.tasks.Todo;

/**
 * Parses user input commands into executable actions.
 * Converts raw text input from the command line into structured
 * command objects that can be processed by the system.
 */
public class Parser {

    private static final String EMPTY_TASK_ERROR = " Error: description of task cannot be empty!";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Parses the given input string and returns the corresponding Command object.
     * @param input The user-input string.
     * @return A command matching the user input.
     * @throws LunaException if user-input was invalid .
     */
    public static Command parse(String input) throws DateTimeParseException, LunaException {
        assert input != null && !input.trim().isEmpty();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String detail = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new MarkCommand(Integer.parseInt(detail) - 1);

            case "unmark":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new UnmarkCommand(Integer.parseInt(detail) - 1);

            case "delete":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new DeleteCommand(Integer.parseInt(detail) - 1);

            case "todo":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new AddCommand(new Todo(detail, false));

            case "deadline":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] dParts = detail.split(" /by ");
                if (dParts.length < 2 || dParts[0].isBlank() || dParts[1].isBlank()) {
                    throw new LunaException.InvalidCommandException(
                            "Error: Deadline format should be 'deadline <description> /by <YYYY-MM-DD HH:mm>'"
                    );
                }

                // Validate datetime
                try {
                    LocalDateTime by = LocalDateTime.parse(dParts[1].trim(), FORMATTER);
                    return new AddCommand(new Deadline(dParts[0].trim(), false, dParts[1].trim()));
                } catch (DateTimeParseException e) {
                    throw new LunaException.InvalidCommandException(
                            "Error: Invalid date/time format for deadline! Use YYYY-MM-DD HH:mm"
                    );
                }


            case "event":
                if (detail.isBlank()) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] eParts = detail.split(" /from ");
                if (eParts.length < 2 || eParts[0].isBlank()) {
                    throw new LunaException.InvalidCommandException(
                            "Error: Event format should be 'event <description> /from <start> /to <end>'"
                    );
                }
                assert eParts.length > 1;
                String[] duration = eParts[1].split(" /to ");
                if (duration.length < 2 || duration[0].isBlank() || duration[1].isBlank()) {
                    throw new LunaException.InvalidCommandException(
                            "Error: Event format should include both start and end times"
                    );
                }
                assert duration.length > 1;
                try {
                    LocalDateTime start = LocalDateTime.parse(duration[0].trim(), FORMATTER);
                    LocalDateTime end = LocalDateTime.parse(duration[1].trim(), FORMATTER);
                    if (!start.isBefore(end)) {
                        throw new LunaException.InvalidCommandException(
                                "Error: Start time must be before end time!"
                        );
                    }
                    return new AddCommand(new Event(eParts[0].trim(), false, duration[0].trim(), duration[1].trim()));
                } catch (DateTimeParseException e) {
                    throw new LunaException.InvalidCommandException(
                            "Error: Invalid date/time format for event! Use YYYY-MM-DD HH:mm"
                    );
                }

            case "find":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                return new FindCommand(detail);

            case "tag":
                if (detail.equals("")) {
                    throw new LunaException.EmptyInputException(EMPTY_TASK_ERROR);
                }
                String[] tParts = detail.split(" ");
                assert tParts.length > 1;
                int index = Integer.parseInt(tParts[0]) - 1;
                String[] tags = tParts[1].split(", ");
                return new TagCommand(index, tags);

            default:
                return new InvalidCommand();
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Error during parsing: " + e.getMessage());
        }
    }
}
