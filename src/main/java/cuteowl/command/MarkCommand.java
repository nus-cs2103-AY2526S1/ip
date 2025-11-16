package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.exception.CuteOwlException;
import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    private String output;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) throws CuteOwlException {
        if (index > tasks.size()) {
            throw new CuteOwlException("Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.mark();
        assert task.getIsDone() : "Task should be marked done after calling mark()";
        storage.save(tasks, notes);
        ui.showTaskMarked(task);
        output = ui.showTaskMarkedGUI(task);
    }

    @Override
    public String getString() {
        return output;
    }
}
