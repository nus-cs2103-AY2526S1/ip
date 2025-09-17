package evansbot.command;

import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;
/**
 * Represents a command to Exit the script.
 * When executed, this command exits the script.
 */
public class GreetCommand extends Command {
    /**
     * Executes the command to greet users.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user.
     * @param storage Storage used to save the updated task list.
     * @return String of Greet command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.greet();
    }
}
