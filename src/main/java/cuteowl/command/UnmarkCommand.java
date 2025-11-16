package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.exception.CuteOwlException;
import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;
    private String output;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) throws CuteOwlException {
        if (index > tasks.size()) {
            throw new CuteOwlException("Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.unmark();
        assert !task.getIsDone() : "Task should be unmarked after calling unmark()";
        storage.save(tasks, notes);
        ui.showTaskUnmarked(task);
        output = ui.showTaskUnmarkGUI(task);
    }

    @Override
    public String getString() {
        return output;
    }
}
