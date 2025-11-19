package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.ListCommand;

/**
 * This class is responsible for handling the "list" command input.
 */
public class ListParser {

    /**
     * Parses a "list" command input and returns a corresponding {@code ListCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return a {@code ListCommand} to display the list of tasks
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        if (inputs.length > 1) {
            throw new MochiBotException.InvalidCommandException();
        }
        return new ListCommand();
    }
}
