package reboot.parser;

import reboot.command.Command;
import reboot.command.ClearCommand;

/**
 * Represents a parser that will make sense of user clear input.
 */
public class ClearParser {

    /**
     * Returns an ClearCommand for the chatbot to complete.
     */
    public static Command parse() {
        return new ClearCommand();
    }
}
