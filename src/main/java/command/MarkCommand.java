package command;

import task.Task;
import task.TaskList;
import exceptions.AlfredException;
import ui.Ui;

public class MarkCommand extends Command {
    private final int pos;
    private final String cmd;

    public MarkCommand(int pos, String cmd) {
        this.pos = pos;
        this.cmd = cmd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws AlfredException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        assert pos >= 0 : "Position must be non-negative";
        assert cmd != null && (cmd.equals("mark") || cmd.equals("unmark")) : "Invalid command type";

        if (tasks.size() == 0) {
            throw new AlfredException("The task list is empty, Master Bruce.");
        }

        if (pos >= tasks.size()) {
            throw new AlfredException("Task " + (pos + 1) + " does not exist. There are only "
                    + tasks.size() + " tasks in the list.");
        }

        Task task = tasks.get(pos);
        String message;
        if (cmd.equals("mark")) {
            boolean marked = task.markDone();
            message = marked
                ? "Task has been marked as done, Master Bruce.\n"
                : "Task has already been marked as done, Master Bruce.\n";
        } else {
            boolean marked = task.markUndone();
            message = marked
                ? "Task has been marked as undone, Master Bruce.\n"
                : "Task has already been marked as undone, Master Bruce.\n";
        }
        message += task.toString();
        return message;
    }
}