package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Command to delete a task from the task list by index.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Parses and constructs DeleteCommand from user input.
     *
     * @param input raw command input string
     * @throws KarlException if index is missing or invalid
     */
    public DeleteCommand(String input) throws KarlException {
        assert input != null;
        this.index = parseIndex(input, "delete");
    }

    /**
     * Executes the deletion of the task at the provided index,
     * saves the updated list, and shows confirmation.
     *
     * @param tasks the TaskList object
     * @param ui the Ui for interaction
     * @param storage the Storage to save data
     * @throws KarlException for out-of-range index or IO failures
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("The task number " + (index + 1) + " is out of range.");
        }
        Task removed = tasks.delete(index);
        storage.saveTasks(tasks.getTasks());
        ui.showRemovedTask(removed, tasks.size());
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
     * @param input full user input
     * @param cmd command name (for error messages)
     * @return zero-based index of task to operate on
     * @throws KarlException if no index or invalid format given
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
