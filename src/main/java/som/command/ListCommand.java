package som.command;

import som.SomException;
import som.TaskList;
import som.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * <p>When executed, this command displays all tasks with their index and status.</p>
 *
 * @author Darien Tan
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        return ui.showList(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
