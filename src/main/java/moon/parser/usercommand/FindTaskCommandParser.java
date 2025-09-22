package moon.parser.usercommand;

import moon.commands.FindTaskCommand;

/**
 * Parser for the {@code find} command.
 * <p>
 * Expected format:
 * <pre>
 *   find {keyword}
 * </pre>
 * Example:
 * <pre>
 *   find book
 * </pre>
 */
public class FindTaskCommandParser implements CommandParser<FindTaskCommand> {

    /**
     * Parses a user input string into a {@link FindTaskCommand}.
     *
     * @param input the raw user input
     * @return a {@link FindTaskCommand} containing the search keyword
     */
    @Override
    public FindTaskCommand parse(String input) {
        String keyword = input.split("\\s+", 2)[1];
        return new FindTaskCommand(keyword);
    }
}
