package som.command;

import som.TaskList;
import som.Ui;

/**
 * Represents a command to sort tasks in the task list by date.
 * <p>When executed, this command sorts deadlines and events chronologically by their due dates or
 * start times. Todos are placed at the end of the list as they have no time component. The sorted
 * list is displayed to the user with a confirmation message.</p>
 *
 * @author Darien Tan
 */
public class SortCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        tasks.sortTasks();
        return ui.showSortedMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
