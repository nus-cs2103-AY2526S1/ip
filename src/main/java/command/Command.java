package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;

/**
 * Generic command interface to abstract and promise ability to execute
 */
public interface Command {
    public abstract String[] execute(Storage storage, NoteBook notebook) throws DukeException;
}
