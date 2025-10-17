package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ListCommand extends Command {
    private String output;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        ui.showTaskList(tasks);
        output = ui.showTaskListGUI(tasks);
    }

    @Override
    public String getString() {
        return output;
    }
}
