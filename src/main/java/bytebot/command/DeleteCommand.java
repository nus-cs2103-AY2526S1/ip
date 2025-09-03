package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Task;
import bytebot.ui.Ui;

/**
 * Deletes a task by index
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a delete command.
     *
     * @param index Index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Removes the specified task and confirms.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        Task removed = storage.deleteTask(index);
        ui.showDeleted(removed, storage.getSize());
    }
}


