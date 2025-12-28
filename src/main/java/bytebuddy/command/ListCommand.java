package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that lists all tasks in the current {@link TaskList}.
 * <p>
 * When executed, this command retrieves all tasks and formats them
 * into a message via the {@link Ui}.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand}.
     * <p>
     * This command does not cause the application to exit, so {@code isExit} is set to {@code false}.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command.
     * <p>
     * Returns a formatted string containing all tasks in the {@link TaskList}.
     *
     * @param storage the storage manager (unused for this command)
     * @param tasks   the current task list
     * @param ui      the user interface for generating the task list message
     * @return a string containing all tasks in the list
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getTaskListMessage(tasks);
    }
}
