package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents an invalid or unrecognized command entered by the user.
 * <p>
 * When executed, this command displays and returns an error message
 * indicating that the input was invalid. It does not terminate the program.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command by displaying an error message
     * to the console and returning it for GUI display.
     *
     * @param tasks   the task list (not used in this command)
     * @param ui      the user interface (not used in this command)
     * @param storage the storage (not used in this command)
     * @return an error message string: "Invalid Command"
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Invalid Command");
        return "Invalid Command";
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since invalid input does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
