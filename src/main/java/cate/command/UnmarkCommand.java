package cate.command;

import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to unmark a task as incomplete in the task list.
 * Supports undoing the action to re-mark the task as complete.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand for the task at the specified index.
     *
     * @param index the zero-based index of the task to unmark; must be non-negative
     */
    public UnmarkCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    /**
     * Executes the unmark command.
     * Marks the task at the given index as incomplete and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list containing the task to unmark
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been unmarked
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmarkTask(index);
        storage.save(tasks);
        return ui.unmarkTask(tasks.getTask(index));
    }

    /**
     * Indicates whether this command supports undo.
     *
     * @return true, since unmarking a task can be undone
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * Undoes the unmark command.
     * Re-marks the previously unmarked task and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list containing the task to mark
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been re-marked
     */
    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.markTask(index);
        storage.save(tasks);
        return ui.markTask(tasks.getTask(index));
    }
}
