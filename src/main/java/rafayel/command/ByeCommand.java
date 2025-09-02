
package rafayel.command;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;
import rafayel.ui.Ui;

/**
 * Bye command that exits the Chatbot.
 */
public class ByeCommand extends Command {

    private final Ui ui;

    /**
     * Constructs a Bye Command
     */
    public ByeCommand(Ui ui) {
        super(Parser.CommandType.BYE);
        this.ui = ui;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        return ui.showExit();
    }
}