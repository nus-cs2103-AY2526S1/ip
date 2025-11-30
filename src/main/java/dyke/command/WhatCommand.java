package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

/**
 * Houses methods for {@code WhatCommand}
 */
public class WhatCommand extends Command {
    public WhatCommand() {
        super(CommandType.WHAT);
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        String res = "What do you mean?\n "
                + "\tWhen you nod your head yes,\n"
                + "\t\tBut you wanna say no... \n";
        ui.printMessage(res);
        return res;
    }
}
