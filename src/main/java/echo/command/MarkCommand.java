package echo.command;

import echo.Echo;
import echo.task.Task;

/**
 * Represents a command to mark tasks. A <code>MarkCommand</code> object
 * is a subtype of <code>Command</code> and it stores int index of task to be marked.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(Echo echo, int index) {
        super(echo);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = echo.getTasklist().getTask(this.index);
        task.markAsDone();
        return echo.getUi().showMarkedTask(task);
    }
}
