package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Represents a Greet command
 */
public class Greet implements Command {
    /**
     * Execute the command, by displaying message
     * @param storage
     * @throws DukeException
     */
    @Override
    public String[] execute(Storage storage, NoteBook notebook) throws DukeException {
        Ui.display("Hi! I'm DukeGpt! Nice to meet you!");
        return new String[]{"Hi! I'm DukeGpt! Nice to meet you!"};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Greet;
    }
}
