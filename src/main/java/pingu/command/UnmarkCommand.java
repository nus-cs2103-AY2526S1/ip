package pingu.command;

import pingu.PinguException;
import pingu.Storage;
import pingu.Task;
import pingu.TaskList;
import pingu.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PinguException {
        Task task = tasks.unmarkTask(taskIndex);
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}
