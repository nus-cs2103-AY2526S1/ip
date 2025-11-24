package shaduke.commands;

import shaduke.ShadukeException;
import shaduke.Storage;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

import java.io.IOException;

/**
 * Represents the marking of a task as done.
 */
public class MarkCommand extends Command {
    private Integer index;

    /**
     * Constructs a command to mark a task as done.
     *
     * @param index the index of the task to mark.
     */
    public MarkCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toMark = tasks.get(index - 1);
        toMark.mark();
        ui.showMarked(toMark);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new ShadukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
