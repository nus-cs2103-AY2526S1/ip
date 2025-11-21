package david.command;

import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Exits the execution and prints farewell message.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Overrides isExit() from the parent class.
     *
     * @return true because it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
