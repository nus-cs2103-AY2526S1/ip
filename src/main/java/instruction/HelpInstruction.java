package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to display available commands.
 * This instruction informs users of commands in the Shrek application.
 */
public class HelpInstruction extends Instruction {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
