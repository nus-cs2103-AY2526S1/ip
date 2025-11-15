package shaniqua.command;

import shaniqua.ShaniquaException;
import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class MarkCommand extends ModifyCommand {
    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param idx the index of the task to be marked as completed (0-based)
     */
    public MarkCommand(int idx) {
        super(idx);
    }

    /**
     * Executes the command by marking the task at the specified index as completed.
     *
     * @param tasks the task list containing the task to be marked
     * @param ui the user interface for interaction (unused in this command)
     * @param storage the storage system (unused in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(idx);
            ui.taskMarked(tasks.getTask(idx).toString());
        } catch (ShaniquaException e) {
            ui.error(e);
        }
    }
}
