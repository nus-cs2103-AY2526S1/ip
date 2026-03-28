package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;
import karl.exception.KarlException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Parses the task index from input string.
     *
     * @param input complete user command string
     * @throws KarlException on missing or invalid index
     */
    public MarkCommand(String input) throws KarlException {
        assert input != null;
        this.index = parseIndex(input, "mark");
    }

    /**
     * Executes marking the given task as done,
     * saves the task list, and displays confirmation.
     *
     * @param tasks TaskList object
     * @param ui Ui to interact with user
     * @param storage Storage to persist changes
     * @throws KarlException when index out of range or IO error
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("The task number " + (index + 1) + " is out of range.");
        }
        tasks.mark(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedTask(tasks.get(index));
    }

    /**
     * Indicates this command does not terminate the program.
     *
     * @return false as this command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the task index from user input string.
     *
     * @param input command string
     * @param cmd command name for error messages
     * @return zero-based task index
     * @throws KarlException on invalid or missing index
     */
    private int parseIndex(String input, String cmd) throws KarlException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) throw new KarlException("Please specify the task number to " + cmd + ".");
        try {
            int idx = Integer.parseInt(tokens[1]) - 1;
            if (idx < 0) throw new KarlException("Task number must be positive.");
            return idx;
        } catch (NumberFormatException e) {
            throw new KarlException("Invalid number format.");
        }
    }
}
