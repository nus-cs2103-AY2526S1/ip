package jerome.command;

import jerome.TaskList;
import jerome.storage.Storage;
import jerome.ui.Ui;

/**
 * Command to exit the application.
 * This command triggers the goodbye message on the UI and indicates
 * that the program should exit.
 */
public class ByeCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The UI component for displaying the goodbye message.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodbyeText();
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true, indicating the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
