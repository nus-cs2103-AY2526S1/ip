package robert.command;

import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}