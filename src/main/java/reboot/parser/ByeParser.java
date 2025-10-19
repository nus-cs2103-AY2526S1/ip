package reboot.parser;

import reboot.command.Command;
import reboot.command.ExitCommand;

/**
 * Represents a parser that will make sense of user bye input.
 */
public class ByeParser {

    /**
     * Returns an ExitCommand for the chatbot to complete.
     */
    public static Command parse() {
        return new ExitCommand();
    }
}
