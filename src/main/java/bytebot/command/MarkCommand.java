package bytebot.command;

import bytebot.ByteException;
import bytebot.ui.Ui;
import bytebot.storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        storage.markTask(index);
        ui.showMarked(storage.getTask(index));
    }
}


