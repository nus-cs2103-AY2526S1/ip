package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that exits the application.
 * <p>
 * When executed, this command shows a farewell message
 * through the {@link Ui} and signals the program to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface used to show the farewell message
     * @param storage the storage (not used in this command)
     * @return a simple "Bye" message for GUI display
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        return "Bye";
    }

    /**
     * Indicates that this command will terminate the program.
     *
     * @return true, since this command exits the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
