package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Shows all tasks in task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        ui.showAllTasks(tasks);
    }
}
