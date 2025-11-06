package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.TaskList;

/**
 * Represents a command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI used to display messages to the user.
     * @param storage The storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        ui.showTasks(tasks.getTasks());
    }

    /**
     * Returns {@code false} as this command does not terminate the program.
     *
     * @return {@code false}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

