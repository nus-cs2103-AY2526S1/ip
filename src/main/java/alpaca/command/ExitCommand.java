package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import javafx.application.Platform;

/**
 * Command to exit the Alpaca application.
 * Displays a goodbye message and terminates the GUI if running.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Shows a goodbye message via the UI and closes the JavaFX application if applicable.
     *
     * @param tasks   The task list (not used in this command, can be null).
     * @param ui      The UI handler for displaying messages; must not be null.
     * @param storage The storage handler (not used in this command, can be null).
     * @return A goodbye message for the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "Ui cannot be null";

        ui.showGoodbye();

        // Exit the GUI thread if JavaFX is running
        Platform.exit();

        return "Goodbye!";
    }

    /**
     * Signals that this command terminates the program.
     *
     * @return true, since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
