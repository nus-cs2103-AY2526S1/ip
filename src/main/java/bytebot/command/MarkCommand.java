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
        this.index = index;
    }

    /**
     * Marks the task and show confirmation
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        storage.markTask(index);
        return ui.showMarked(storage.getTask(index));
    }
}


