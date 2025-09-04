package evansbot.command;

import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;
/**
 * Represents a command to Exit the script.
 * When executed, this command exits the script.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user.
     * @param storage Storage used to save the updated task list.
     * @return String of Exit command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.sayBye();
    }

    /**
     * Tells the script that it has exited.
     * @return true when exited.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
