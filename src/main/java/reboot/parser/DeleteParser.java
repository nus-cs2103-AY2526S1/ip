package reboot.parser;

import static reboot.parser.Parser.isNotInteger;
import static reboot.parser.Parser.isOneElement;

import reboot.RebootException;
import reboot.command.Command;
import reboot.command.DeleteCommand;

/**
 * Represents a parser that will make sense of user delete input.
 */
public class DeleteParser {

    /**
     * Returns a DeleteCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {
        if (isOneElement(input)) {
            throw new RebootException("Proper usage: delete {task index}");
        }

        if (isNotInteger(input[1])) {
            throw new RebootException("Proper usage: delete {task index}");
        }

        assert input[1] != null : "Index should not be null";

        if (Integer.parseInt(input[1]) < 1) {
            throw new RebootException("Ensure that task index is more than 0");
        }

        return new DeleteCommand(Integer.parseInt(input[1]));
    }
}