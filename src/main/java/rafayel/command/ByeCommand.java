
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;
import rafayel.ui.Ui;

/**
 * Represents a command that terminates the chatbot program.
 */
public class ByeCommand extends Command {

    /** UI instance used to display the exit message. */
    private final Ui ui;

    /**
     * Constructs a Bye Command.
     * 
     * Initialises the command type as BYE and 
     * prepares a Ui instance for rendering the exit message.
     */
    public ByeCommand() {
        super(CommandHandle.CommandType.BYE);
        this.ui = new Ui();
    }

    /**
     * Executes the bye command.
     *
     * @param tasks current list of tasks (not used in this command)
     * @param storage storage handler (not used in this command)
     * @return a farewell message string to be displayed to the user
     * @throws RafayelException if an error occurs while executing the command
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        return ui.showExit();
    }
}