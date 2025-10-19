package reboot.parser;

import static reboot.parser.Parser.isNotInteger;
import static reboot.parser.Parser.isOneElement;

import reboot.RebootException;
import reboot.command.Command;
import reboot.command.UnmarkCommand;

/**
 * Represents a parser that will make sense of user unmark input.
 */
public class UnmarkParser {

    /**
     * Returns an UnmarkCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {
        if (isOneElement(input)) {
            throw new RebootException("Proper usage: unmark {task index}");
        }

        if (isNotInteger(input[1])) {
            throw new RebootException("Proper usage: unmark {task index}");
        }

        assert input[1] != null : "Index should not be null";

        if (Integer.parseInt(input[1]) < 1) {
            throw new RebootException("Ensure that task index is more than 0");
        }

        return new UnmarkCommand(Integer.parseInt(input[1]));
    }
}