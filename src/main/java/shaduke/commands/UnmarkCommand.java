package shaduke.commands;

import shaduke.ShadukeException;
import shaduke.Storage;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

import java.io.IOException;

/**
 * Represents the marking of a task as not done yet.
 */
public class UnmarkCommand extends Command {
    private Integer index;

    /**
     * Constructs the command to mark a task as not done yet.
     *
     * @param index the index of the task to unmark.
     */
    public UnmarkCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toUnmark = tasks.get(index - 1);
        toUnmark.unmark();
        ui.showUnmarked(toUnmark);
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
