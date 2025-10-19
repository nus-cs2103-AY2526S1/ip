package reboot.parser;

import static reboot.parser.Parser.isOneElement;

import reboot.RebootException;
import reboot.command.Command;
import reboot.command.FindCommand;

/**
 * Represents a parser that will make sense of user find input.
 */
public class FindParser {

    /**
     * Returns a FindCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {
        if (isOneElement(input)) {
            throw new RebootException("Proper usage: find {keyword}");
        }

        assert input[1] != null : "Keyword should not be null";

        return new FindCommand(input[1]);
    }
}