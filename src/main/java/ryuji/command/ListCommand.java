package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to display the full list of tasks to the user.
 * <p>The {@code ListCommand} is used to show all tasks currently stored in the {@code TaskList}.</p>
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} with the specified command keyword.
     * The command keyword is typically "list".
     *
     * @param command the command keyword (usually "list")
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the list command by displaying all tasks in the current {@code TaskList}.
     * This method uses the {@code Ui} to present the task list to the user.
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} used to interact with the user and display the task list
     * @param storage the {@code Storage} handler (not used in this command)
     * @return a string representation of the entire task list to be displayed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayTaskList(tasks);
    }
}
