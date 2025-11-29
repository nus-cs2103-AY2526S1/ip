package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to delete a task from the task list.
 * Supports undoing the deletion.
 */
public class DeleteCommand extends Command {
    private int index;
    private Task deleted;

    /**
     * Constructs a DeleteCommand for the task at the specified index.
     *
     * @param index the zero-based index of the task to delete; must be non-negative
     */
    public DeleteCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    /**
     * Executes the delete command.
     * Removes the task at the given index from the task list and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been deleted
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task t = tasks.getTask(index);
        deleted = t;
        tasks.deleteTask(index);
        storage.save(tasks);
        return ui.deleteTask(t, tasks.size());
    }

    /**
     * Indicates whether this command supports undo.
     *
     * @return true, since deleting a task can be undone
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * Undoes the delete command.
     * Re-inserts the previously deleted task at its original index and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list to restore the deleted task to
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the deletion has been undone
     */
    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.insertTask(index, deleted);
        storage.save(tasks);
        return ui.undoDeleteTask(deleted);
    }
}
