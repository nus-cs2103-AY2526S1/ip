package john.core.command;

import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that signals the application to exit.
 * <p>
 * Responsibilities:
 * - Produce a farewell message for the user.
 * - Indicate to the command loop that the program should terminate.
 * <p>
 * Side effects:
 * - None. No mutation of tasks or storage.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command by returning an exit-type result with a farewell message.
     *
     * @param t  the task list (unused)
     * @param s  the storage backend (unused)
     * @param ui the user interface (unused)
     * @return a CommandResult that contains the goodbye message and signals exit
     */
    @Override
    public CommandResult execute(TaskList t, Storage s, Ui ui) {
        return CommandResult.exit("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command causes the application to terminate.
     *
     * @return true, always
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
