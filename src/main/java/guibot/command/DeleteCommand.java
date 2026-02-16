package guibot.command;

import java.io.IOException;

import guibot.Parser;
import guibot.Storage;
import guibot.TaskList;
import guibot.exception.TaskNotFoundException;
import guibot.exception.WrongInputFormatException;

/**
 * Command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private static final String EXPECTED_INPUT_FORMAT = "delete <index of task>";
    private int index;
    private String output = "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.";

    /**
     * Creates a DeleteCommand.
     *
     * @param index Index of task to be deleted.
     */
    private DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Static factory method to construct a DeleteCommand from an input string.
     *
     * @param input The input string to construct the DeleteCommand from.
     * @return A DeleteCommand constructed from the string.
     * @throws WrongInputFormatException If the string is not in the correct format.
     */
    public static DeleteCommand of(String input) throws WrongInputFormatException {
        try {
            String[] details = Parser.getDetails(input, " ");
            int index = Parser.getIndexFromString(details[1]);
            return new DeleteCommand(index);
        } catch (WrongInputFormatException e) {
            throw new WrongInputFormatException(EXPECTED_INPUT_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskNotFoundException, IOException {
        assert tasks != null : "Cannot delete from a null tasklist";
        String taskString = tasks.delete(index);
        assert storage != null : "Cannot save to a null storage";
        storage.saveTasks(tasks);
        return String.format(output, taskString, tasks.size());
    }
}
