package balloon.logic;

import balloon.command.Command;
import balloon.command.Command.CommandType;
import balloon.command.DeadlineCommand;
import balloon.command.DeleteCommand;
import balloon.command.EventCommand;
import balloon.command.ExitCommand;
import balloon.command.FindCommand;
import balloon.command.ListCommand;
import balloon.command.MarkCommand;
import balloon.command.TodoCommand;
import balloon.command.UndoCommand;
import balloon.command.UnmarkCommand;
import balloon.exception.MissingInformationException;
import balloon.exception.NoCommandException;
import balloon.exception.StringConversionException;
import balloon.exception.UnknownCommandException;
import balloon.task.Deadline;
import balloon.task.Event;
import balloon.task.Todo;

/**
 * Parses user input strings into corresponding {@link Command} objects.
 * This class is responsible for interpreting commands like "todo",
 * "deadline", "event", "mark", "unmark", "delete", "find", "list", "undo", and "bye".
 */
public class Parser {

    /**
     * Converts a trimmed user input string into a {@link Command} object.
     * <p>
     * The parser supports commands that may require additional information,
     * e.g., task descriptions or task numbers, and throws exceptions if the
     * input is missing information or is invalid.
     *
     * @param input the trimmed string entered by the user
     * @return the {@link Command} corresponding to the user's input
     * @throws NoCommandException if the input is blank
     * @throws MissingInformationException if required details for the command are missing
     * @throws UnknownCommandException if the input does not match any known command
     * @throws StringConversionException if a command expecting an integer argument
     *         is followed by a non-integer string
     */
    public static Command parseUserInput(String input) throws
            NoCommandException, MissingInformationException,
            UnknownCommandException, StringConversionException {
        if (input.isBlank()) {
            throw new NoCommandException();
        }

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.equals("undo")) {
            return new UndoCommand();
        }

        // ALl subsequent commands should take > 1 word

        if (input.equals("todo")) {
            throw new MissingInformationException(CommandType.TODO);
        }
        if (input.equals("deadline")) {
            throw new MissingInformationException(CommandType.DEADLINE);
        }
        if (input.equals("event")) {
            throw new MissingInformationException(CommandType.EVENT);
        }
        if (input.equals("mark")) {
            throw new MissingInformationException(CommandType.MARK);
        }
        if (input.equals("unmark")) {
            throw new MissingInformationException(CommandType.UNMARK);
        }
        if (input.equals("delete")) {
            throw new MissingInformationException(CommandType.DELETE);
        }
        if (input.equals("find")) {
            throw new MissingInformationException(CommandType.FIND);
        }

        if (input.startsWith("todo ")) {
            String taskDescription = input.substring(5);
            return new TodoCommand(new Todo(taskDescription));
            // Note that we do not need to check if taskDescription isBlank
        }

        if (input.startsWith("deadline ")) {
            String rest = input.substring(9);
            String[] parts = rest.split(" /by ", 2); // max 2 parts split
            if (parts.length == 2) {
                String description = parts[0];
                String by = parts[1];
                if (description.isBlank() || by.isBlank()) {
                    throw new MissingInformationException(CommandType.DEADLINE);
                } else {
                    return new DeadlineCommand(new Deadline(description, by));
                }
            } else {
                throw new MissingInformationException(CommandType.DEADLINE);
            }
        }

        if (input.startsWith("event ")) {
            String rest = input.substring(6);

            String[] parts1 = rest.split(" /from ", 2);
            if (parts1.length < 2 || parts1[0].isBlank() || parts1[1].isBlank()) {
                throw new MissingInformationException(CommandType.EVENT);
            }
            String description = parts1[0];

            String[] parts2 = parts1[1].split(" /to ", 2);
            if (parts2.length < 2 || parts2[0].isBlank() || parts2[1].isBlank()) {
                throw new MissingInformationException(CommandType.EVENT);
            }
            String from = parts2[0];
            String to = parts2[1];

            return new EventCommand(new Event(description, from, to));
        }

        if (input.startsWith("mark ")) {
            try {
                String taskNumberString = input.substring(5);
                int taskNumber = Integer.parseInt(taskNumberString);
                return new MarkCommand(taskNumber); // -1 to account for 0-based indexing
            } catch (NumberFormatException e) {
                throw new StringConversionException(CommandType.MARK);
            }
        }

        if (input.startsWith("unmark ")) {
            try {
                String taskNumberString = input.substring(7);
                int taskNumber = Integer.parseInt(taskNumberString);
                return new UnmarkCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new StringConversionException(CommandType.UNMARK);
            }
        }

        if (input.startsWith("delete ")) {
            try {
                String taskNumberString = input.substring(7);
                int taskNumber = Integer.parseInt(taskNumberString);
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new StringConversionException(CommandType.DELETE);
            }
        }

        if (input.startsWith("find ")) {
            String keyword = input.substring(5);
            return new FindCommand(keyword);
        }

        // fallthrough means unknown command
        throw new UnknownCommandException();
    }
}
