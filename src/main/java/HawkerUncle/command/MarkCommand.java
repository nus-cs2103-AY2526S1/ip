package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

/**
 * Represents the "Mark" command that marks a task as completed.
 */
public class MarkCommand implements Command {
    private final int idx;

    /**
     * Initializes the MarkCommand
     * @param idx The index of the task to be marked as done in the task list.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the "Mark" command by marking the specified task as done.
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and laoded.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException("Task number is invalid.");
        tasks.get(idx).setDone(true);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            return Ui.showError("Task number is invalid");
        }

        return Ui.showTaskMarked(tasks.get(idx));
    }

    /**
     * Checks if the command is an exit command.
     * @return false, since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
