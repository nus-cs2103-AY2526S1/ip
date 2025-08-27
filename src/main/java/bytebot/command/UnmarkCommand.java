package bytebot.command;

import bytebot.ByteException;
import bytebot.ui.Ui;
import bytebot.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        storage.unmarkTask(index);
        ui.showUnmarked(storage.getTask(index));
    }
}


