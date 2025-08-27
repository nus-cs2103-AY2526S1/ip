package bytebot.command;

import bytebot.ByteException;
import bytebot.ui.Ui;
import bytebot.storage.Storage;
import bytebot.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        Task removed = storage.deleteTask(index);
        ui.showDeleted(removed, storage.getSize());
    }
}


