package bytebot.command;

import bytebot.ByteException;
import bytebot.ui.Ui;
import bytebot.storage.Storage;

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
     * Marks the task and confirms.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        storage.markTask(index);
        ui.showMarked(storage.getTask(index));
    }
}


