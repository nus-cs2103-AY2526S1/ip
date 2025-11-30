package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

/**
 * Houses methods for {@code ByeCommand}
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        String msg = "Bye, Catch you Later!";
        ui.printMessage(msg);

        // Save current tasks into dyke.ui.Dyke.txt
        if (!library.isEmpty()) {
            String store = storage.saveTasks(library);
            ui.printMessageNoBar(store);
            msg = msg + "\n" + store;
        }
        return msg;
    }
}
