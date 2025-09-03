package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an unmark command.
     *
     * @param index Index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task and confirms.
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        storage.unmarkTask(index);
        return ui.showUnmarked(storage.getTask(index));
    }
}


