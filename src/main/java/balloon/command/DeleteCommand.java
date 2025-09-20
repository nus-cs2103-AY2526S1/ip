package balloon.command;

import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that deletes a Task from the task list, by specifying its task number.
 * <p>
 * This command supports undo, which restores the deleted task to its
 * original position in the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private Task deletedTask;
    private int numberOfTasks;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) throws TaskNumberException {
        deletedTask = tasks.deleteTask(taskNumber - 1);
        numberOfTasks = tasks.getSize();
    }

    @Override
    public void undo(TaskList tasks, Storage storage) {
        tasks.addTaskAtIndex(taskNumber - 1, deletedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return "Noted. I've removed this task:\n\t" + deletedTask + "\n"
                + "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
