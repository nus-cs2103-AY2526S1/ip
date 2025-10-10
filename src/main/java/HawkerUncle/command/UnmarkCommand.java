package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

/**
 * Represents the "Unmark" command that marks a task as not completed.
 */
public class UnmarkCommand implements Command {
    private final int idx;

    /**
     * Initializes the UnmarkCommand
     * @param idx The index of the task to be unmarked in the task list.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the "Unmark" command by marking the task as not completed.
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and laoded.
     */
    @Override public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException("Task number is invalid.");
        tasks.get(idx).setDone(false);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            return Ui.showError("Task is not saved");
        }

        return Ui.showTaskUnmarked(tasks.get(idx));
    }

    /**
     * Checks if the command is an exit command
     * @return false, since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
