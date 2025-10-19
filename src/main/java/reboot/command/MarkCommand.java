package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;
import reboot.task.Task;

/**
 * Represents a command that marks a task as completed in the tasklist.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a new MarkCommand with the specified index.
     * @param index index of the task to be mark as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as completed in the tasklist.
     * Updates the tasklist file via storage.
     * Informs the user via the gui.
     *
     * @param tasks Tasklist with the task to be mark as completed.
     * @param gui Gui outputs message.
     * @param storage Storage writes lines to update file.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {

        if (tasks.isEmpty()) {
            return gui.showError("Mark function is useless without any tasks");
        }

        if (tasks.isOutOfRange(index)) {
            return gui.showError("Please check the index provided");
        }

        tasks.updateRecurringTasks();
        Task t = tasks.get(index - 1);
        t.mark();
        storage.writeFile(tasks.getAll());
        return gui.showMessage("Marked\n" + t);
    }
}
