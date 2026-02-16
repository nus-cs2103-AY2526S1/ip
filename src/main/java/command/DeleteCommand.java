package command;
import task.Task;
import task.TaskList;
import ui.Ui;
import exceptions.AlfredException;

public class DeleteCommand extends Command {
    private final int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AlfredException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        assert pos >= 0 : "Position must be non-negative";

        if (tasks.size() == 0) {
            throw new AlfredException("The task list is empty, Master Bruce.");
        }

        if (pos >= tasks.size()) {
            throw new AlfredException("Task " + (pos + 1) + " does not exist. There are only "
                    + tasks.size() + " tasks in the list.");
        }

        Task deleteTask = tasks.get(pos);
        tasks.delete(pos);
        String message = "Noted, Master Bruce. This task has been successfully deleted:\n" + deleteTask + "\n";
        message += "There are " + tasks.size() + " tasks left.";
        return message;
    }
}
