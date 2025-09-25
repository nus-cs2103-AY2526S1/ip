package heimerdinger.command;

import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.TaskList;

/**
 * Exits the application gracefully.
 *
 * <p><b>Usage:</b> {@code bye}</p>
 *
 * <p>This command displays a farewell message and signals to the
 * main loop that the program should terminate.</p>
 */
public class ExitCommand extends Command {
    /**
     * Shows a goodbye message via the {@link Ui}.
     *
     * <p>No changes are made to the {@link TaskList} or {@link Storage}.</p>
     *
     * @param tasks   the task list (unused in this command)
     * @param ui      the UI sink used to display the farewell message; must not be {@code null}
     * @param storage the persistence layer (unused in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command should terminate the program.
     *
     * @return always {@code true}
     */
    public boolean isExit() {
        return true;
    }
}
