package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Command to delete notes to a notebook
 */
public class NotesDelete implements Command {
    private final int index;

    /**
     * Constructor
     * @param index index involved
     */
    public NotesDelete(int index) {
        this.index = index;
    }

    /**
     * Execute
     * @param storage storage
     * @return deleted note
     * @throws DukeException handles error
     */
    @Override
    public String[] execute(Storage storage, NoteBook noteBook) throws DukeException {
        if (this.index >= noteBook.getSize()) {
            throw new DukeException("Index out of bounds!");
        }
        String deletedNote = noteBook.deleteNote(index).toString();
        Ui.display("Got it! Removed " + this.index + " note : ",
                deletedNote);
        return new String[]{"Got it! Removed " + this.index + " note : ",
                            deletedNote};
    }
}
