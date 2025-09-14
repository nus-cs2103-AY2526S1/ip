package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Command to print the user input back to the user
 */
public class EchoCommand extends AbstractCommand {
    private final String text;

    /**
     * Prints the user input back to the user.
     * @param text Text to be printed to the user.
     */
    public EchoCommand(String text) {
        super(false, false);
        this.text = text;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        ui.displayMessage(text);
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        assert false : "Not undoable";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EchoCommand echoCommand) {
            return this.text.equals(echoCommand.text);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.text.hashCode();
    }
}
