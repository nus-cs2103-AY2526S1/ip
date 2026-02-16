package guibot.command;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.WrongInputFormatException;

/**
 * Represents a command to find all tasks containing a given string.
 */
public class FindCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "find <string to search for>";
    private String string;
    private String output = "Here are the matching tasks in your list:%s";

    /**
     * Creates a FindCommand.
     */
    private FindCommand(String string) {
        assert string != null : "Cannot make a FindCommand with a null string";
        this.string = string;
    }

    /**
     * Static factory method to construct a FindCommand from an input string.
     *
     * @param input The input string to construct the FindCommand from.
     * @return A FindCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static FindCommand of(String input) throws WrongInputFormatException {
        try {
            String[] details = Parser.getDetails(input, " ");
            return new FindCommand(details[1]);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "Cannot find from a null tasklist";
        TaskList tasksContainingString = tasks.find(string);
        return String.format(output, tasksContainingString.toString());
    }
}
