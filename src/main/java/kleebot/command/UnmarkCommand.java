package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private final String[] input;

    /**
     * Constructs a new {@code UnmarkCommand}.
     *
     * @param input The parsed user input containing the index of the task to unmark.
     */
    public UnmarkCommand(String[] input) {
        this.input = input;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The UI for displaying messages.
     * @param storage The storage for saving task list updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkItem(input);
        ui.updateUnmark(tasks.getTask(Integer.parseInt(input[1]) - 1));
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        tasks.unmarkItem(input);
        storage.saveTasksToLocal(tasks.getTasks());
        return ui.formatUnmarkTask(tasks.getTask(Integer.parseInt(input[1]) - 1));
    }
}
