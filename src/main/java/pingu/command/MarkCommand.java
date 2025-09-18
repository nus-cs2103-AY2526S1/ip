package pingu.command;

import pingu.PinguException;
import pingu.Storage;
import pingu.Task;
import pingu.TaskList;
import pingu.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PinguException {
        Task task = tasks.markTask(taskIndex);

        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n  " + task;
    }
}
