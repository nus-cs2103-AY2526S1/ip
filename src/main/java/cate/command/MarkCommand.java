package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to mark a task as completed in the task list.
 * Supports undoing the action to unmark the task.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param index the zero-based index of the task to mark; must be non-negative
     */
    public MarkCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    /**
     * Executes the mark command.
     * Marks the task at the given index as completed and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list containing the task to mark
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been marked as completed
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.markTask(index);
        storage.save(tasks);
        return ui.markTask(tasks.getTask(index));
    }

    /**
     * Indicates whether this command supports undo.
     *
     * @return true, since marking a task can be undone
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * Undoes the mark command.
     * Unmarks the previously marked task and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list containing the task to unmark
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been unmarked
     */
    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmarkTask(index);
        storage.save(tasks);
        return ui.unmarkTask(tasks.getTask(index));
    }
}
