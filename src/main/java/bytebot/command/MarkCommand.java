package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a mark command.
     *
     * @param index Index of the task to mark
     */
    public MarkCommand(int index) {
        assert index >= 0 : "Index must be positive or 0";
        this.index = index;
    }

    /**
     * Marks the task and show confirmation
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        storage.markTask(index);
        return ui.showTaskMarkedNotification(storage.getTask(index));
    }
}


