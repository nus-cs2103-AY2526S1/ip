package cuteowl.command;

import cuteowl.note.Note;
import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class AddNoteCommand extends Command{
    private final String content;
    private String output;

    public AddNoteCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        Note note = new Note(content);
        notes.add(note);
        storage.save(tasks, notes);
        ui.showNoteAdded(note);
        output = ui.showNoteAddedGUI(note);
    }

    @Override
    public String getString() {
        return output;
    }
}
