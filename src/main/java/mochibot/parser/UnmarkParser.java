package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.UnmarkCommand;

/**
 * This class is responsible for handling the "unmark" command input.
 */
public class UnmarkParser {

    /**
     * Parses the "unmark" command input and returns a corresponding {@code UnmarkCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return a {@code UnmarkCommand} for the task at the specified index
     * @throws MochiBotException.InvalidTaskIndexException if the task index is not a valid integer
     * @throws MochiBotException.MissingTaskIndexException if the task index is missing from the input
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        try {
            int taskIndex = Integer.parseUnsignedInt(inputs[1]);
            return new UnmarkCommand(taskIndex - 1);
        } catch (NumberFormatException e) {
            throw new MochiBotException.InvalidTaskIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiBotException.MissingTaskIndexException();
        }
    }
}
