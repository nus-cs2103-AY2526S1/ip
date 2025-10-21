package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Represents an Exit command, that closes the program
 */
public class Exit implements Command {
    /**
     * Execute the command, by displaying message and saving to file
     * @param storage   storage current being used
     * @throws DukeException    Handles error during execution
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        Ui.display("That was fun! Hope to see you again!");
        storage.readToFile();
        return new String[]{"That was fun! Hope to see you again!"};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Exit;
    }
}
