package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;
import karl.exception.KarlException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Parses task index from the input.
     *
     * @param input user command string
     * @throws KarlException on invalid format or missing index
     */
    public UnmarkCommand(String input) throws KarlException {
        assert input != null;
        this.index = parseIndex(input, "unmark");
    }

    /**
     * Executes marking the task as not done,
     * saves the updated task list, and displays confirmation.
     *
     * @param tasks TaskList object
     * @param ui Ui object for interaction
     * @param storage Storage to persist data
     * @throws KarlException on invalid index or IO error
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("The task number " + (index + 1) + " is out of range.");
        }
        tasks.unmark(index);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkedTask(tasks.get(index));
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
     * Parses the index from input string.
     *
     * @param input full command string
     * @param cmd command name for error messages
     * @return zero-based index of task
     * @throws KarlException if no or invalid index specified
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

