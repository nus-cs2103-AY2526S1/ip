package pingu.command;

import pingu.PinguException;
import pingu.Storage;
import pingu.Task;
import pingu.TaskList;
import pingu.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PinguException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());

        return "Noted. I've removed this task:\n  " + removedTask + "\n" + "Now you have " + tasks.size()
                + " tasks in the list.";
    }
}
