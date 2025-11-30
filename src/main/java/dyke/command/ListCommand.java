package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

/**
 * Houses methods for {@code ListCommand}
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        String msg = library.printList();
        ui.printMessage(msg);
        return msg;
    }
}
