package mochibot.parser;

import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.Command;
import mochibot.command.FindCommand;

/**
 * This class is responsible for handling the "find" command input.
 */
public class FindParser {

    /**
     * Parses the "find" command input and returns a corresponding {@code FindCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return a {@code FindCommand} containing the search keyword
     * @throws MochiBotException.MissingFindKeywordException if no keyword is provided
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        String keyword = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        if (keyword.isEmpty()) {
            throw new MochiBotException.MissingFindKeywordException();
        }
        return new FindCommand(keyword);
    }
}
