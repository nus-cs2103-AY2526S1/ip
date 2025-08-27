package bytebot.command;

import bytebot.ui.Ui;
import bytebot.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}


