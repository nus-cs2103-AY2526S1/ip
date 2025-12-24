package echo.command;

import echo.Echo;
import echo.task.Task;

/**
 * Represents a command to unmark tasks. A <code>UnmarkCommand</code> object is a subtype of
 * <code>Command</code> and stores int index of task to be unmarked.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(Echo echo, int index) {
        super(echo);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = echo.getTasklist().getTask(this.index);
        task.markAsUndone();
        return echo.getUi().showUnmarkedTask(task);
    }
}
