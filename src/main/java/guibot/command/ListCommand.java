package guibot.command;

import guibot.Storage;
import guibot.TaskList;
import guibot.exception.WrongInputFormatException;

/**
 * Command to print tasks in the list.
 */
public class ListCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "list";
    private String output = "Here are the tasks in your list:%s";

    /**
     * Static factory method to construct a ListCommand from an input string.
     *
     * @param input The input string to construct the ListCommand from.
     * @return A ListCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static ListCommand of(String input) throws WrongInputFormatException {
        if (input.equals("list")) {
            return new ListCommand();
        } else {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "Cannot list a null tasklist";
        return String.format(output, tasks.toString());
    }
}
