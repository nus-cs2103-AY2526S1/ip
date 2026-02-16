package guibot.command;

import java.io.IOException;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.TaskNotFoundException;
import guibot.exception.WrongInputFormatException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "unmark <index of task>";
    private int index;
    private String output = "OK, I've marked this task as not done yet:\n%s";

    /**
     * Creates an UnmarkCommand.
     *
     * @param index Index of task to be marked as not done.
     */
    private UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Static factory method to construct an UnmarkCommand from an input string.
     *
     * @param input The input string to construct the UnmarkCommand from.
     * @return An UnmarkCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static UnmarkCommand of(String input) throws WrongInputFormatException {
        try {
            String[] details = Parser.getDetails(input, " ");
            int index = Parser.getIndexFromString(details[1]);
            return new UnmarkCommand(index);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskNotFoundException, IOException {
        assert tasks != null : "Cannot unmark a task in a null tasklist";
        String taskString = tasks.unmark(index);
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        return String.format(output, taskString);
    }
}
