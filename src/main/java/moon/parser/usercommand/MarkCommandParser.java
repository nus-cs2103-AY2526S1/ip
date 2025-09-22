package moon.parser.usercommand;

import moon.commands.MarkCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

/**
 * Parser for the {@link Command#MARK} command.
 * <p>
 * Expected format:
 * <pre>
 *   mark {task number}
 * </pre>
 * Example:
 * <pre>
 *   mark 3
 * </pre>
 */
public class MarkCommandParser implements CommandParser<MarkCommand> {

    /**
     * Parses a user input string into a {@link MarkCommand}.
     *
     * @param input the raw user input
     * @return a {@link MarkCommand} targeting the specified task index
     * @throws InvalidIndexException if the index is missing, not an integer,
     *                               or cannot be parsed
     */
    @Override
    public MarkCommand parse(String input) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.MARK,
                    "Meow! I don't think you put in an integer?");
        }
    }
}
