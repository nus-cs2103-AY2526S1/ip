package shaniqua.command;

import shaniqua.ShaniquaException;
import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class UnmarkCommand extends ModifyCommand {
    /**
     * Constructs an UnmarkCommand for the task at the specified index.
     *
     * @param idx the index of the task to be unmarked (0-based)
     */
    public UnmarkCommand(int idx) {
        super(idx);
    }
    /**
     * Executes the command by unmarking the task at the specified index.
     *
     * @param tasks the task list containing the task to be unmarked
     * @param ui the user interface for interaction (unused in this command)
     * @param storage the storage system (unused in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(idx);
            ui.taskUnmarked(tasks.getTask(idx).toString());
        } catch (ShaniquaException e) {
            ui.error(e);
        }
    }
}
