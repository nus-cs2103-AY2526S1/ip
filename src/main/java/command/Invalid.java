package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Represents an Invalid command, for when invalid format is used
 */
public class Invalid implements Command {
    /**
     * Executes the command, by reprompting for new command
     * @param storage
     * @throws DukeException
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        Ui.display("Invalid command. Please retype your command.");
        return new String[]{"Invalid command. Please retype your command."};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Invalid;
    }
}
