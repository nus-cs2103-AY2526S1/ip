package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ByeCommand extends Command {
    private String output;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        ui.showExit();
        output = ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getString() {
        return output;
    }
}
