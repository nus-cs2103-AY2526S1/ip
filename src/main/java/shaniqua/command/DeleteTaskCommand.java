package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.taskcore.TaskListException;
import shaniqua.ui.Ui;

public class DeleteTaskCommand extends ModifyCommand {

    /**
     * Constructs a DeleteTaskCommand for the task at the specified index.
     *
     * @param idx the index of the task to be deleted (0-based)
     */
    public DeleteTaskCommand(int idx) {
        super(idx);
    }
    /**
     * Executes the command by removing the task at the specified index from the task list.
     *
     * @param tasks the task list to remove the task from
     * @param ui the user interface for interaction (unused in this command)
     * @param storage the storage system (unused in this command)
     * @throws CommandFailException if the task cannot be removed (e.g., invalid index)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.remove(idx);
            ui.taskDeleted(tasks.getLength());
        } catch (TaskListException e) {
            ui.error(e);
        }
    }
}
