package balloon.command;

import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that marks a task in the task list as done, by specifying its
 * task number.
 * <p>
 * This command supports undo, which marks the task as not done.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private Task markedTask;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) throws TaskNumberException {
        markedTask = tasks.markTask(taskNumber - 1);
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws TaskNumberException {
        tasks.unmarkTask(taskNumber - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Nice! I've marked this task as done:\n\t" + markedTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
