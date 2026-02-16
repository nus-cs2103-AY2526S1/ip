package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that marks a task in the task list as done.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Creates a new {@code MarkCommand}.
     *
     * @param index the index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the task list as done.
     * Also saves the updated task list to storage and
     * returns a confirmation message.
     *
     * @param tasks the task list containing the task to mark.
     * @param ui the user interface used to generate messages (not used in this command).
     * @param storage the storage handler that saves the updated task list.
     * @return the confirmation message after successfully marking the task.
     * @throws MomoException if the specified index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MomoException {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        if (index < 0 || index >= tasks.size()) {
            String errorDetail = "The task number provided is invalid!";
            String errorFix = "Fix: Retry \"mark <task number>\" with a valid task number!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        tasks.markTask(index);

        assert tasks.getTask(index).isDone() : "Task should be marked as done after execution";

        storage.save(tasks);
        return "Nice! I've marked this task as done:\n  " + tasks.getTask(index).toString();
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as marking a task does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
