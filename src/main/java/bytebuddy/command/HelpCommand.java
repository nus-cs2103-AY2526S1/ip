package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that displays the help message to the user.
 * <p>
 * When executed, this command provides a list of available commands
 * and their usage instructions through the {@link Ui}.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new {@code HelpCommand}.
     * <p>
     * This command is marked as an exit command in the parent {@link Command} class,
     * allowing the program to treat it as a command that may change application flow.
     */
    public HelpCommand() {
        super(true);
    }

    /**
     * Executes the help command.
     * <p>
     * This does not modify the task list or storage. It simply returns
     * the formatted help message from the {@link Ui}.
     *
     * @param storage the storage manager (unused for this command)
     * @param tasks   the current task list (unused for this command)
     * @param ui      the user interface for displaying the help message
     * @return a string containing the help message
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showHelpMessage();
    }
}
