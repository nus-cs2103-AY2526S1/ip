package command;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Command to add notes to a notebook
 */
public class NotesAdd implements Command {
    private final String noteContent;
    private NoteBook noteBook;

    /**
     * Constructor
     * @param s note content
     */
    public NotesAdd(String s) {
        this.noteContent = s;
    }
    @Override
    public String[] execute(Storage storage, NoteBook noteBook) throws DukeException {
        noteBook.addNote(noteContent);
        Ui.display("Got it! Added new note : ",
                noteBook.get(noteBook.getSize() - 1).toString());
        return new String[]{"Got it! Added new note : ",
                noteBook.get(noteBook.getSize() - 1).toString()};
    }
}
