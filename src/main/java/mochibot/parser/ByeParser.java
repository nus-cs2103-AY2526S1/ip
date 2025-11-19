package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.ExitCommand;

/**
 * This class is responsible for handling the "bye" command input.
 */
public class ByeParser {

    /**
     * Parses a "bye" command input and returns a corresponding {@code ExitCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return an {@code ExitCommand} that terminates the application
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        if (inputs.length > 1) {
            throw new MochiBotException.InvalidCommandException();
        }
        return new ExitCommand();
    }
}
