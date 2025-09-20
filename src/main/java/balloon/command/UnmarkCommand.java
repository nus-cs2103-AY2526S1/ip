package balloon.command;

import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that marks a task in the task list as not done, by specifying its
 * task number.
 * <p>
 * This command supports undo, which marks the task as done.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;
    private Task unmarkedTask;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) throws TaskNumberException {
        unmarkedTask = tasks.unmarkTask(taskNumber - 1);
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws TaskNumberException {
        tasks.markTask(taskNumber - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "OK, I've marked this task as not done yet:\n\t" + unmarkedTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
