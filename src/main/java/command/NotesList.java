package command;

import java.util.stream.IntStream;

import exception.DukeException;
import notebook.NoteBook;
import storage.Storage;
import ui.Ui;

/**
 * Command to list notes in the notebook
 */
public class NotesList implements Command {
    /**
     * Execute command
     * @param storage storage
     * @return String version of notebook
     * @throws DukeException handles errors
     */
    @Override
    public String[] execute(Storage storage, NoteBook noteBook) throws DukeException {
        String[] output = new String[noteBook.getSize() + 1];
        IntStream.range(0, noteBook.getSize()).forEach(i -> output[i + 1] = ((i + 1) + ". "
                + noteBook.get(i)));
        output[0] = "Here are your notes! There are a total of " + noteBook.getSize() + " notes.";
        Ui.display(output);
        return output;
    }
}
