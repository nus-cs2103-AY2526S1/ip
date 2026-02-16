package alice;

import alice.command.*;
import alice.exceptions.*;

/**
 * Represents an object that parses the commands of users,
 * and returns an appropriate command
 */
public class Parser {

    /**
     * Returns an appropriate command using the user's input
     *
     * @param fullCommand full input of the user
     * @return Command
     * @throws AliceException If an unknown command is given
     */
    public static Command parse(String fullCommand) throws AliceException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new MissingParameterException("Command cannot be empty!");
        }

        // Normalize: remove extra spaces, make comparisons case-insensitive
        String normalized = fullCommand.trim().replaceAll("\\s+", " ");
        String lower = normalized.toLowerCase();

        try {
            if (lower.equals("list")) {
                return new ListCommand();

            } else if (lower.startsWith("mark")) {
                return new MarkCommand(normalized, true);

            } else if (lower.startsWith("unmark")) {
                return new MarkCommand(normalized, false);

            } else if (lower.startsWith("todo")) {
                if (normalized.equalsIgnoreCase("todo")) {
                    throw new EmptyDescriptionException("todo");
                }
                return new AddTodoCommand(normalized);

            } else if (lower.startsWith("deadline")) {
                if (!normalized.contains("/by")) {
                    throw new MissingParameterException(
                            "Deadline format should be: deadline <description> /by <time>");
                }
                return new AddDeadlineCommand(normalized);

            } else if (lower.startsWith("event")) {
                if (!normalized.contains("/from") || !normalized.contains("/to")) {
                    throw new MissingParameterException(
                            "Event format should be: event <description> /from <start> /to <end>");
                }
                return new AddEventCommand(normalized);

            } else if (lower.startsWith("delete")) {
                String[] parts = normalized.split(" ");
                if (parts.length < 2) {
                    throw new MissingParameterException("Delete command requires a task number.");
                }
                try {
                    Integer.parseInt(parts[1]); // check valid number
                } catch (NumberFormatException e) {
                    throw new InvalidParameterException("Delete command requires a valid task number.");
                }
                return new DeleteCommand(normalized);

            } else if (lower.equals("bye")) {
                return new ExitCommand();

            } else {
                throw new UnknownCommandException();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingParameterException("Command is missing required parts: " + normalized);
        }
    }
}
