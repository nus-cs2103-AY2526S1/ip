package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Creates a new {@code DeleteCommand}.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index from the task list.
     * Also saves the updated task list to storage and
     * returns a confirmation message.
     *
     * @param tasks the task list from which the task is deleted.
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler that saves the updated task list.
     * @return the confirmation message after successfully deleting the task.
     * @throws MomoException if the specified index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"delete <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        Task deletedTask = tasks.deleteTask(index);

        assert tasks.size() >= 0 : "Task list size should be non-negative after deletion";

        storage.save(tasks);
        return ui.getDeleteTaskMessage(deletedTask, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as deleting a task does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
