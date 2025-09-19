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
        assert index >= 0 : "Index must be positive or 0";
        this.index = index;
    }

    /**
     * Unmarks the task and confirms.
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        storage.unmarkTask(index);
        return ui.showUnmarked(storage.getTask(index));
    }
}


