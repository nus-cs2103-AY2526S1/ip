package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to add a task to the task list.
 * Supports undoing the addition.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task the task to be added; must not be null
     */
    public AddCommand(Task task) {
        assert task != null;
        this.task = task;
    }

    /**
     * Executes the add command.
     * Adds the task to the task list and saves it to storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list to add the task to
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task has been added
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        storage.saveTask(task);
        return ui.addTask(task);
    }

    /**
     * Indicates whether this command supports undo.
     *
     * @return true, since adding a task can be undone
     */
    @Override
    public boolean canUndo() {
        return true;
    }

    /**
     * Undoes the add command.
     * Removes the previously added task from the task list and updates storage.
     *
     * @param storage the storage handler used to persist tasks
     * @param tasks   the task list to remove the task from
     * @param ui      the UI handler used to generate feedback messages
     * @return a message confirming the task addition has been undone
     */
    @Override
    public String undo(Storage storage, TaskList tasks, Ui ui) {
        tasks.deleteTask(tasks.getIndexOfTask(task));
        storage.save(tasks);
        return ui.undoAddTask(task);
    }
}
