package moon.parser.usercommand;

import moon.commands.DeleteCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

/**
 * Parser for the {@link Command#DELETE} command.
 * <p>
 * Expected format:
 * <pre>
 *   delete {task number}
 * </pre>
 * Example:
 * <pre>
 *   delete 2
 * </pre>
 */
public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    /**
     * Parses a user input string into a {@link DeleteCommand}.
     *
     * @param input the raw user input
     * @return a {@link DeleteCommand} targeting the specified task index
     * @throws InvalidIndexException if the index is missing, not an integer,
     *                               or cannot be parsed
     */
    @Override
    public DeleteCommand parse(String input) throws InvalidIndexException {
        try {
            // splits the input string then check for the second element for the list index
            int taskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.DELETE,
                    "Meow! I don't think you put in an integer?");
        }
    }
}
