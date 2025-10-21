package Command;

import JohnException.JohnException;
import Task.TaskList;
import UI.Ui;

/**
 * Command to sort task list.
 */
public class SortCommand extends Command {
    /**
     * Executes command by sorting task list.
     *
     * @param tasks Current task list.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        tasks.sortByDate();
        ui.showList(tasks);
    }
}
