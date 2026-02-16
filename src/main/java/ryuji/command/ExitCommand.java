package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command that exits the application.
 * <p>When executed, this command shows a farewell message to the user and gracefully exits the application.</p>
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand} with the given command keyword.
     * The command keyword is typically "bye".
     *
     * @param command the command keyword (usually "bye")
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Executes the exit command by displaying a farewell message to the user.
     * No changes are made to the task list or storage. This method provides a graceful exit
     * and shows the user a farewell message.
     *
     * @param tasks   the current {@code TaskList} (not used by this command)
     * @param ui      the {@code Ui} component responsible for user interaction
     * @param storage the {@code Storage} component responsible for persistent data (not used by this command)
     * @return a farewell message displayed to the user
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFarewell();
    }
}
