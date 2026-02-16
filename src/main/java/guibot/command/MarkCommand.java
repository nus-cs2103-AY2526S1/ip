package guibot.command;

import java.io.IOException;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.TaskNotFoundException;
import guibot.exception.WrongInputFormatException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "mark <index of task>";
    private int index;
    private String output = "Nice! I've marked this task as done:\n%s";

    /**
     * Creates a MarkCommand.
     *
     * @param index Index of task to be marked as done.
     */
    private MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Static factory method to construct a MarkCommand from an input string.
     *
     * @param input The input string to construct the MarkCommand from.
     * @return A MarkCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static MarkCommand of(String input) throws WrongInputFormatException {
        try {
            String[] details = Parser.getDetails(input, " ");
            int index = Parser.getIndexFromString(details[1]);
            return new MarkCommand(index);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskNotFoundException, IOException {
        assert tasks != null : "Cannot mark a task in a null tasklist";
        String taskString = tasks.mark(index);
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        return String.format(output, taskString);
    }
}
