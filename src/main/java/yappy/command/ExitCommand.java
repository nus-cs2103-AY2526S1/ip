package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.ui.Ui;

/**
 * Represents a command to exit Yappy cli.
 *
 */
public class ExitCommand extends Command {


    /**
     * Saves the current tasks into file and exits program.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTasks());
            return "See you again!";
        } catch (YappyException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns true because this is the ExitCommand class
     *
     * @return Whether this command is the exit command or not.
     */
    @Override
    public Boolean isExit() {
        return true;
    }
}
