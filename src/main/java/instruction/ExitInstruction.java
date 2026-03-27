package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to exit the application.
 * This instruction handles the graceful termination of the Shrek application.
 */
public class ExitInstruction extends Instruction {
    /**
     * Executes the exit instruction by returning the goodbye message.
     *
     * @param tasks   the task list (unused in this instruction)
     * @param ui      the user interface for generating the goodbye message
     * @param storage the storage system (unused in this instruction)
     * @return goodbye message string
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates that this instruction should terminate the application.
     *
     * @return true always, indicating the application should exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
