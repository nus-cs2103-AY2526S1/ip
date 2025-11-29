package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Base type for all executable commands.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException On command-specific failures.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether this command should terminate the application.
     *
     * @return {@code true} to request exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
