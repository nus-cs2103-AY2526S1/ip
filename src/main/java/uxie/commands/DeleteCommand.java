package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Command for deleting a task.
 *
 * @author junyan-k
 */
public class DeleteCommand extends Command {

    /** Index of task to delete in list. 0-indexed. */
    private final int taskIndex;

    /**
     * Generates DeleteCommand that deletes task matching taskIndex.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     * Deletes task matching index from TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String desc = tasks.deleteTask(taskIndex);
            storage.deleteTask(taskIndex);
            ui.uxieAppendln(String.format("Good to be realistic. Task %s (%s) has been deleted.",
                    taskIndex + 1, desc));
        } catch (UxieIllegalOpException | UxieIOException e) {
            ui.appendException(e);
        }
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
