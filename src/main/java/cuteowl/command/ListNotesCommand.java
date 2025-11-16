package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ListNotesCommand extends Command {
    private String output;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        ui.showNotesList(notes);
        output = ui.showNoteListGUI(notes);
    }

    @Override
    public String getString() {
        return output;
    }
}
