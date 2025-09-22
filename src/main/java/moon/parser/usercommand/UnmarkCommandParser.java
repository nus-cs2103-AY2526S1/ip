package moon.parser.usercommand;

import moon.commands.UnmarkCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

/**
 * Parser for the {@link Command#UNMARK} command.
 * <p>
 * Expected format:
 * <pre>
 *   unmark {task number}
 * </pre>
 * Example:
 * <pre>
 *   unmark 3
 * </pre>
 */
public class UnmarkCommandParser implements CommandParser<UnmarkCommand> {

    /**
     * Parses a user input string into an {@link UnmarkCommand}.
     *
     * @param input the raw user input
     * @return an {@link UnmarkCommand} targeting the specified task index
     * @throws InvalidIndexException if the index is missing, not an integer,
     *                               or cannot be parsed
     */
    @Override
    public UnmarkCommand parse(String input) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.UNMARK,
                    "Meow! I don't think you put in an integer?");
        }
    }
}
