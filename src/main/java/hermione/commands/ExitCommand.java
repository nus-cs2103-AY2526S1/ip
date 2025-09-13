package hermione.commands;

import hermione.storage.TaskStorage;
import hermione.ui.console.ConsoleUi;
import javafx.application.Platform;

/**
 * Represents a command to exit the Hermione application.
 */
public class ExitCommand extends Command {
    public ExitCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to exit the application.
     * This method will close the application and return a goodbye message.
     *
     * @return A goodbye message indicating the application is closing.
     */
    @Override
    public String execute() {
        ConsoleUi.exit();
        Platform.exit();
        return "Bye. Hope to see you soon!";
    }
}
