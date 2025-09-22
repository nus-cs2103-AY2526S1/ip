package moon.parser.usercommand;

import moon.commands.ListCommand;

/**
 * Parser for the {@code list} command.
 * <p>
 * Expected format:
 * <pre>
 *   list
 * </pre>
 * Produces a {@link ListCommand} with no arguments.
 */
public class ListCommandParser implements CommandParser<ListCommand> {

    /**
     * Parses a user input string into a {@link ListCommand}.
     *
     * @param input the raw user input (ignored since {@code list} has no arguments)
     * @return a new {@link ListCommand}
     */
    @Override
    public ListCommand parse(String input) {
        return new ListCommand();
    }
}
