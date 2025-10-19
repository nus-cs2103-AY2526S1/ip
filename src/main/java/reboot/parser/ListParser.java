package reboot.parser;

import reboot.command.Command;
import reboot.command.ListCommand;

/**
 * Represents a parser that will make sense of user list input.
 */
public class ListParser {

    /**
     * Returns a ListCommand for the chatbot to complete.
     */
    public static Command parse() {
        return new ListCommand();
    }
}