package tsundere.command;

import tsundere.TsundereException;
import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Deletes a task from the task list and storage, and prints a message to the user.
 */
public class DeleteCommand extends AbstractCommand {
    private final int id;
    private Task task;

    /**
     * Creates a new command to delete an existing task.
     * @param id Index of task in the task list to be deleted. Must be valid.
     */
    public DeleteCommand(int id) {
        super(false, true);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        try {
            Task task = tasks.remove(id);
            this.task = task;
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.DELETE_SUCCESS, task);
            log.add(this);
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        try {
            tasks.undoDelete(task, id);
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.UNDO, this);
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand deleteCommand) {
            return this.id == deleteCommand.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return DeleteCommand.class.hashCode();
    }

    @Override
    public String toString() {
        return "Delete Command: " + task;
    }
}
